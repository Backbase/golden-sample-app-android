package com.backbase.golden_sample_app.presentation.bottom

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import com.backbase.android.design.header.TabHeaderFragment
import com.backbase.android.design.header.TabListConfiguration
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.presentation.header.TabListConfigurationProvider

/**
 * Injects the `TabHeaderFragment` instances as destination into the NavGraph.
 */
fun NavController.addBottomBarNavigationDestinations(
    tabListConfig: TabListConfigurationProvider
) = graph.addDestinations(
    createDestination(
        id = R.id.dashboard,
        tabList = tabListConfig.dashboardTabList()
    ),
    createDestination(
        id = R.id.move_money,
        tabList = tabListConfig.moveMoneyTabList()
    )
)

/**
 * Creates a [FragmentNavigator.Destination] that navigates to an instance of the [TabHeaderFragment].
 *
 * @param id the id of the destination.
 * @param tabList contains the arguments that [TabHeaderFragment] requires. These are injected into
 * the fragment as the default value for the `arguments`.
 */
private fun NavController.createDestination(
    @IdRes id: Int,
    tabList: TabListConfiguration,
): FragmentNavigator.Destination {
    val destinationBuilder = FragmentNavigatorDestinationBuilder(
        id = id,
        navigator = navigatorProvider.getNavigator(FragmentNavigator::class.java),
        fragmentClass = TabHeaderFragment::class
    )

    destinationBuilder.argument(name = TabHeaderFragment.TabListConfigurationKey) {
        defaultValue = tabList
    }

    return destinationBuilder.build()
}
