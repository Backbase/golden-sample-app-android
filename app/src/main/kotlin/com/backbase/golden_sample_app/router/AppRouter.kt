package com.backbase.golden_sample_app.router

import androidx.navigation.NavController
import com.backbase.golden_sample_app.presentation.header.TabListConfigurationProvider
import com.backbase.golden_sample_app.presentation.bottom.addBottomBarNavigationDestinations
import com.backbase.golden_sample_app.presentation.header.TopBarConfigurationProvider

/**
 * App routing implementation
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
class AppRouter(
    private val tabListConfig: TabListConfigurationProvider,
    private val topBarConfig: TopBarConfigurationProvider,
) : AppRouting {

    private var navController: NavController? = null
        set(value) {
            value?.addBottomBarNavigationDestinations(tabListConfig, topBarConfig)
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
