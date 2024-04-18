package com.backbase.golden_sample_app.router

import androidx.navigation.NavController
import com.backbase.golden_sample_app.presentation.header.TabListConfigurationProvider
import com.backbase.golden_sample_app.presentation.bottom.addBottomBarNavigationDestinations

/**
 * App routing implementation
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
class AppRouter(
    private val tabListConfig: TabListConfigurationProvider,
) : AppRouting {

    private var navController: NavController? = null
        set(value) {
            // Workaround because WorkspaceSelectorRouting doesn't allow to navigate  between destinations with arguments.
            value?.addBottomBarNavigationDestinations(tabListConfig)
            field = value
        }

    override fun bind(navController: NavController) {
        this.navController = navController
    }

    override fun unbind() {
        navController = null
    }

    override fun getNavController(): NavController? = navController
}
