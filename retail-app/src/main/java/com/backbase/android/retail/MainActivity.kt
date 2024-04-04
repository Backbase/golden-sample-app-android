package com.backbase.android.retail

import android.graphics.Color
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.backbase.android.design.theme.PreviewTheme
import com.backbase.android.retail.authenticaton.authenticationActions
import com.backbase.android.retail.workspaceselector.workspaceSelectorActions

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        val useComposeNavigation = false
        if (useComposeNavigation) {
            setupComposeNavigation()
        } else {
            setupFragmentNavigation()
        }
    }

    private fun setupFragmentNavigation() {
        setContentView(R.layout.main_activity)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        authenticationActions {
            onSuccess = {
                navController.navigate(R.id.action_authenticationJourneyFragment_to_workspaceSelector)
            }
        }
        workspaceSelectorActions {
            onSuccess = {
                navController.navigate(R.id.action_workspaces_selector_to_bottomMenuScreen)
            }
        }


        navController.setGraph(R.navigation.navigation_main)
    }

    private fun setupComposeNavigation() {
        setContent {
            PreviewTheme {
                MainNavHost()
            }
        }
    }
}
