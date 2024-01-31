package com.backbase.golden_sample_app.presentation.bottom

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import com.backbase.android.design.header.TabHeaderFragment
import com.backbase.android.design.header.TabListConfiguration
import com.backbase.android.design.header.TopBarConfiguration
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.presentation.header.TabListConfigurationProvider
import com.backbase.golden_sample_app.presentation.header.TopBarConfigurationProvider

fun NavController.addBottomBarNavigationDestinations(
    tabListConfig: TabListConfigurationProvider,
    topBarConfig: TopBarConfigurationProvider
) = graph.addDestinations(
    createDestination(
        id = R.id.dashboard,
        topBar = topBarConfig.config(),
        tabList = tabListConfig.dashboardTabList()
    ),
    createDestination(
        id = R.id.move_money,
        topBar = topBarConfig.config(),
        tabList = tabListConfig.moveMoneyTabList()
    )
)

@Suppress("DEPRECATION")
private fun NavController.createDestination(
    @IdRes id: Int,
    topBar: TopBarConfiguration,
    tabList: TabListConfiguration,
): FragmentNavigator.Destination {
    val destinationBuilder = FragmentNavigatorDestinationBuilder(
        id = id,
        navigator = navigatorProvider.getNavigator(FragmentNavigator::class.java),
        fragmentClass = TabHeaderFragment::class
    )

    destinationBuilder.argument(name = TabHeaderFragment.TopBarConfigurationKey) {
        defaultValue = topBar
    }

    destinationBuilder.argument(name = TabHeaderFragment.TabListConfigurationKey) {
        defaultValue = tabList
    }

    return destinationBuilder.build()
}
