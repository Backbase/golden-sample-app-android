package com.backbase.golden_sample_app.presentation

import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.forEach
import androidx.navigation.NavController
import com.backbase.android.retail.journey.more.MoreJourney
import com.backbase.android.retail.journey.more.MoreMenuInstanceId
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.payments.paymentsScopeId
import com.backbase.golden_sample_app.router.AppRouting
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationHandler(
    private val bottomNavigation: BottomNavigationView,
    private val navController: NavController,
    private val navigator: AppRouting
) {

    private val bottomMenuIds: List<Int> = getBottomMenuItemIds()

    private fun getBottomMenuItemIds(): List<Int> = mutableListOf<Int>().apply {
        bottomNavigation.menu.forEach { menuItem ->
            add(menuItem.itemId)
        }
    }

    fun setupNavigation() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in bottomMenuIds) {
                bottomNavigation.visibility = View.VISIBLE
                ensureCorrectBottomNavItemSelected(destination.id)
            } else {
                bottomNavigation.visibility = View.GONE
            }
        }
        navigator.bind(navController)
        bottomNavigation.setOnItemSelectedListener { item ->
            if(item.itemId == R.id.move_money){
                navController.navigate(
                    item.itemId, bundleOf(
                        MoreJourney.INSTANCE_ID to paymentsScopeId
                    )
                )
            } else {
                navController.navigate(
                    item.itemId
                )
            }
            true
        }
    }

    private fun ensureCorrectBottomNavItemSelected(destinationId: Int) {
        bottomNavigation.menu.forEach { item ->
            if (item.itemId == destinationId) {
                item.isChecked = true
            }
        }
    }

}