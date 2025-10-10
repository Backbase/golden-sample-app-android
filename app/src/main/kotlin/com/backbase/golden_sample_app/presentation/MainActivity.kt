package com.backbase.golden_sample_app.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle.State.CREATED
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.backbase.android.design.header.AvatarConfiguration
import com.backbase.android.design.header.TabHeaderViewModel
import com.backbase.android.design.header.TopBarConfiguration
import com.backbase.app_common.AppRouting
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.databinding.ActivityMainBinding
import com.backbase.golden_sample_app.presentation.MainViewModel.ViolationState
import com.backbase.golden_sample_app.presentation.bottom.setupBottomBar
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * Single activity approach and the setup of the navigation.
 *
 * Created by Backbase R&D B.V on 17/08/2023
 */
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val navigator: AppRouting by inject()

    private val mainViewModel: MainViewModel by inject()

    /**
     * Shared VM between the activity and all the instances of TabHeaderFragment
     */
    private val tabHeaderViewModel: TabHeaderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val navController = findNavController()
        navigator.bind(navController)

        loadKoinModules(
            module {
                factory<NavController> { findNavController() }
            }
        )

        setupBottomBar(isInRootScreen = tabHeaderViewModel.uiState.map { it.isInRootScreen })

        lifecycleScope.launch { repeatOnLifecycle(STARTED) { mainViewModel.uiState.collect(::update) } }

        lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                mainViewModel.securityState.collect { securityState ->
                    when (val state = securityState.state) {
                        ViolationState.Debugged -> logIt(R.string.app_is_debugged)
                        ViolationState.Rooted -> logIt(R.string.device_is_rooted)
                        ViolationState.Tampered -> logIt(R.string.app_is_tampered)
                        ViolationState.Good -> logIt(R.string.looks_good)
                        ViolationState.Compromised -> logIt(R.string.is_violated)
                        ViolationState.AdditionalCheck -> logIt(R.string.general_detectors_violated)
                        ViolationState.Emulator -> logIt(R.string.device_is_an_emulator)
                        ViolationState.Nothing -> {}
                        is ViolationState.Error -> logIt(R.string.error, state.ex.toString())
                    }
                }
            }
        }

        // Start periodic security checks when activity is started
        lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                // Start periodic checks every 30 seconds
                mainViewModel.startPeriodicSecurityChecks()
            }
        }

        // Stop periodic checks when activity is paused/destroyed
        lifecycleScope.launch {
            repeatOnLifecycle(CREATED) {
                mainViewModel.stopPeriodicSecurityChecks()
            }
        }
    }

    /**
     * Updates the [TopBarConfiguration] in all the TabHeaderFragment. This will update
     * the toolbar content with the information passed in the configuration.
     */
    private fun update(uiState: MainViewModel.UiState) =
        tabHeaderViewModel update TopBarConfiguration {
            title = uiState.fullName
            subtitle = uiState.serviceAgreementName
            avatar = AvatarConfiguration { initials = uiState.userInitials }
        }

    internal fun MainActivity.findNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        return navHostFragment.navController
    }

    private fun logIt(msg: Int, ex: String = "") {
        Log.d(this::class.java.name, getString(msg) + ex)
    }
}
