package com.backbase.golden_sample_app.presentation.header

import androidx.fragment.app.Fragment
import com.backbase.android.design.header.DestinationByIdConfiguration
import com.backbase.android.design.header.NavigationConfiguration
import com.backbase.android.design.header.TabConfiguration
import com.backbase.android.design.header.TabListConfiguration
import com.backbase.deferredresources.DeferredText
import com.backbase.golden_sample_app.R

/**
 * Provides the [TabListConfiguration] for the tabs displayed in the TabLayout inside
 * the TabHeaderFragment.
 */
class TabListConfigurationProvider {

    fun dashboardTabList() = TabListConfiguration {
        +accountsTab()
        +emptyTabTwo()
        +emptyTabThree()
    }

    private fun accountsTab() = TabConfiguration {
        text = DeferredText.Resource(R.string.top_bar_tab_accounts)
        navigation = NavigationConfiguration {
            navGraphId = R.navigation.navigation_main
            destination = DestinationByIdConfiguration { id = com.backbase.accounts_journey.R.id.account_journey_nav_graph }
        }
    }

    private fun emptyTabTwo() = TabConfiguration {
        text = DeferredText.Resource(R.string.top_bar_tab_two)
        navigation = NavigationConfiguration {
            navGraphId = R.navigation.navigation_main
            destination = DestinationByIdConfiguration { id = R.id.upcoming_fragment }
        }
    }

    private fun emptyTabThree() = TabConfiguration {
        text = DeferredText.Resource(R.string.top_bar_tab_three)
        navigation = NavigationConfiguration {
            navGraphId = R.navigation.navigation_main
            destination = DestinationByIdConfiguration { id = R.id.upcoming_fragment }
        }
    }
}

class UpComingJourneyFragment : Fragment(R.layout.fragment_upcoming_journey)
