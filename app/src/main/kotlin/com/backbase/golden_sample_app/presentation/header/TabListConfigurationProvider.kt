package com.backbase.golden_sample_app.presentation.header

import android.content.Context
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.backbase.android.design.header.DestinationByIdConfiguration
import com.backbase.android.design.header.NavigationConfiguration
import com.backbase.android.design.header.TabConfiguration
import com.backbase.android.design.header.TabListConfiguration
import com.backbase.android.retail.journey.more.MoreJourney
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.payments.paymentsScopeId

class TabListConfigurationProvider(private val context: Context) {

    fun dashboardTabList() = TabListConfiguration {
        +accountsTab(context)
        +emptyTabTwo(context)
        +emptyTabThree(context)
    }

    fun moveMoneyTabList() = TabListConfiguration {
        +moveMoneyTab(context)
        +emptyTabTwo(context)
        +emptyTabThree(context)
    }

    private fun accountsTab(context: Context) = TabConfiguration {
        name = context.getString(R.string.top_bar_tab_accounts)
        navigation = NavigationConfiguration {
            navGraphId = R.navigation.navigation_main
            destination = DestinationByIdConfiguration { id = R.id.accountListFragment }
        }
    }

    private fun moveMoneyTab(context: Context) = TabConfiguration {
        name = context.getString(R.string.top_bar_tab_payments)
        navigation = NavigationConfiguration {
            navGraphId = R.navigation.navigation_main
            destination = DestinationByIdConfiguration {
                id = R.id.moreJourney
                args = bundleOf(MoreJourney.INSTANCE_ID to paymentsScopeId)
            }
        }
    }

    private fun emptyTabTwo(context: Context) = TabConfiguration {
        name = context.getString(R.string.top_bar_tab_two)
        navigation = NavigationConfiguration {
            navGraphId = R.navigation.navigation_main
            destination = DestinationByIdConfiguration { id = R.id.upcoming_fragment }
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
