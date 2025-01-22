package com.backbase.golden_sample_app.presentation.header

import android.content.Context
import androidx.fragment.app.Fragment
import com.backbase.android.design.header.DestinationByIdConfiguration
import com.backbase.android.design.header.NavigationConfiguration
import com.backbase.android.design.header.TabConfiguration
import com.backbase.android.design.header.TabListConfiguration
import com.backbase.golden_sample_app.R

/**
 * Provides the [TabListConfiguration] for the tabs displayed in the TabLayout inside
 * the TabHeaderFragment.
 */
class TabListConfigurationProvider(private val context: Context) {

    fun dashboardTabList() = TabListConfiguration {
        +accountsTab(context)
        +emptyTabTwo(context)
        +emptyTabThree(context)
    }

    private fun accountsTab(context: Context) = TabConfiguration {
        name = context.getString(R.string.top_bar_tab_accounts)
        navigation = NavigationConfiguration {
            navGraphId = R.navigation.navigation_main
            destination = DestinationByIdConfiguration { id = com.backbase.accounts_journey.R.id.account_journey_nav_graph }
        }
    }

    private fun emptyTabTwo(context: Context) = TabConfiguration {
        name = context.getString(R.string.top_bar_tab_two)
        navigation = NavigationConfiguration {
            navGraphId = R.navigation.navigation_main
            destination = DestinationByIdConfiguration { id = R.id.account_fragment_2 }
        }
    }

    private fun emptyTabThree(context: Context) = TabConfiguration {
        name = context.getString(R.string.top_bar_tab_three)
        navigation = NavigationConfiguration {
            navGraphId = R.navigation.navigation_main
            destination = DestinationByIdConfiguration { id = R.id.upcoming_fragment }
        }
    }
}

class UpComingJourneyFragment : Fragment(R.layout.fragment_upcoming_journey)
