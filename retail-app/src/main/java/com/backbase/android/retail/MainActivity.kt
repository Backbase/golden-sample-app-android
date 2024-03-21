package com.backbase.android.retail

import android.graphics.Color
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.backbase.android.design.theme.PreviewTheme
import com.backbase.android.identity.journey.authentication.AuthenticationJourney

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        setContentView(R.layout.main_activity)
        val isCompose = true
        if (isCompose) {
            setupComposeNavigation()
        } else {
            setupFragmentNavigation()
        }
    }

    private fun setupFragmentNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val graph = navController.createGraph(startDestination = "authentication") {
            fragment<AuthenticationJourney>("authentication") {
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
