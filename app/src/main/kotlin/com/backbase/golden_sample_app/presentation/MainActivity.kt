package com.backbase.golden_sample_app.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.backbase.android.design.header.AvatarConfiguration
import com.backbase.android.design.header.TabHeaderViewModel
import com.backbase.android.design.header.TopBarConfiguration
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.NavigationEventEmitter
import com.backbase.android.identity.journey.authentication.use_case.impl.NavigationEvent
import com.backbase.app_common.AppRouting
import com.backbase.custom_authentication_flow.core.util.CustomNavigationEmitter
import com.backbase.custom_authentication_flow.core.util.CustomNavigationEvent
import com.backbase.custom_authentication_flow.core.util.AuthFlowCompleteRouter
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.databinding.ActivityMainBinding
import com.backbase.golden_sample_app.presentation.bottom.setupBottomBar
import kotlinx.coroutines.delay
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
    private val customNavigationEmitter: CustomNavigationEmitter by inject()

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
                single<AuthFlowCompleteRouter> {
                    object: AuthFlowCompleteRouter {
                        override fun sendEvent(navigationEvent: NavigationEvent) {
                            lifecycleScope.launch {
                                delay(1000)
                                get<NavigationEventEmitter>().sendEvent(navigationEvent)
                            }
                        }
                    }
                }
            }
        )

        setupBottomBar(isInRootScreen = tabHeaderViewModel.uiState.map { it.isInRootScreen })

        lifecycleScope.launch { repeatOnLifecycle(STARTED) { mainViewModel.uiState.collect(::update) } }

        lifecycleScope.launch { repeatOnLifecycle(STARTED) { customNavigationEmitter.navigationEvents.collect(::navigateToCustomAuthenticators) } }
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

    private fun navigateToCustomAuthenticators(event: CustomNavigationEvent) {
        when (event) {
            is CustomNavigationEvent.ToTermsAndConditions -> {
                findNavController().navigate(R.id.action_to_termsAndConditionsScreen)
            }
        }
    }
}
