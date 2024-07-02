package com.backbase.golden_sample_app.presentation.bottom

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.presentation.MainActivity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

fun MainActivity.setupBottomBar(isInRootScreen: Flow<Boolean>) {
    val navController = findNavController(R.id.nav_host_container)

    binding.bottomNavigation.setupWithNavController(navController)
    binding.bottomNavigation.setOnItemSelectedListener { item ->
        navController.navigate(item.itemId)
        true
    }

    val showBottomBar = isInRootScreen.combine(
        flow = destinationChangedListenerFlow(navController),
        transform = { isInRootScreen, hasDestinationBottomBar ->
            isInRootScreen && hasDestinationBottomBar
        }
    )

    repeatOnStarted { showBottomBar.collect { showBottomBar -> binding.bottomNavigation.isVisible = showBottomBar } }
}

private fun MainActivity.destinationChangedListenerFlow(navController: NavController) = callbackFlow {
    // The addition of R.id.tab_header_dashboard is a workaround due to WorkspaceSelectorRouting
    // doesn't allow to navigate to a screen with args, so we need to place an in between screen for adding the args to the header
    val destinationsWithBottomBar = binding.bottomNavigation.menu.children.map { it.itemId } + R.id.tab_header_dashboard
    val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
        trySend(element = destination.id in destinationsWithBottomBar)
    }

    navController.addOnDestinationChangedListener(listener)
    awaitClose { navController.removeOnDestinationChangedListener(listener) }
}

fun AppCompatActivity.repeatOnStarted(block: suspend () -> Unit) = lifecycleScope
    .launch { repeatOnLifecycle(STARTED) { block() } }
