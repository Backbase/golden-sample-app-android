package com.backbase.golden_sample_app.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.backbase.android.design.header.AvatarConfiguration
import com.backbase.android.design.header.TabHeaderViewModel
import com.backbase.android.design.header.TopBarConfiguration
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.databinding.ActivityMainBinding
import com.backbase.golden_sample_app.menu.moreMenuModule
import com.backbase.golden_sample_app.presentation.bottom.setupBottomBar
import com.backbase.golden_sample_app.presentation.header.updateStatusBarColor
import com.backbase.golden_sample_app.router.AppRouting
import com.backbase.golden_sample_app.session.sessionModule
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

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
        setDynamicThemeIfApply()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_container)
        navigator.bind(navController)

        updateStatusBarColor(isInRootScreen = tabHeaderViewModel.uiState.map { it.isInRootScreen })
        setupBottomBar(isInRootScreen = tabHeaderViewModel.uiState.map { it.isInRootScreen })

        if (savedInstanceState == null) { loadScopedDependencies() }

        lifecycleScope.launch { repeatOnLifecycle(STARTED) { mainViewModel.uiState.collect(::update) } }
    }

    /**
     * Updates the [TopBarConfiguration] in all the TabHeaderFragment. This will update
     * the toolbar content with the information passed in the configuration.
     */
    private fun update(uiState: MainViewModel.UiState) = tabHeaderViewModel update TopBarConfiguration {
        title = uiState.fullName
        subtitle = uiState.serviceAgreementName
        avatar = AvatarConfiguration { initials = uiState.userInitials }
    }

    private fun loadScopedDependencies() {
        val navController = Navigation.findNavController(this@MainActivity, R.id.nav_host_container)
        loadKoinModules(
            listOf(
                sessionModule(navController),
                moreMenuModule(navController) {
                    when (selectedTheme()) {
                        Int.MAX_VALUE -> setThemeTo(themeResId = R.style.Theme_Backbase_Premium)
                        else -> setThemeToDefault()
                    }
                },
            )
        )
    }
}
