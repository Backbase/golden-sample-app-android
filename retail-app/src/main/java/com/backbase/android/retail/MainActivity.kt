package com.backbase.android.retail

import android.graphics.Color
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.backbase.android.business.journey.workspaces.view.WorkspaceSelectorFragment
import com.backbase.android.design.theme.PreviewTheme
import com.backbase.android.identity.journey.authentication.AuthenticationJourney
import com.backbase.android.retail.authenticaton.authenticationActions
import com.backbase.android.retail.journey.contacts.ContactsJourney
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
                navController.navigate("a1")
            }
        }
        workspaceSelectorActions {
            onSuccess = {
                navController.navigate("contacts")
            }
        }
        val graph = navController.createGraph(startDestination = "authentication") {
            fragment<AuthenticationJourney>("authentication") {
            }
            fragment<WorkspaceSelectorFragment>("workspaces") {
            }
            fragment<ContactsJourney>("contacts") {
            }
        }
        navController.setGraph(graph, null)
    }

    private fun setupComposeNavigation() {
        setContent {
            PreviewTheme {
                MainNavHost()
            }
        }
    }
}
