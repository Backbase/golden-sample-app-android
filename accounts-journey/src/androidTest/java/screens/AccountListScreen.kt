package screens

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import app_common.BaseScreen
import app_common.pullToRefresh
import app_common.scenario.FragmentJourneyScenario
import app_common.scenario.launchInJourneyContainer
import app_common.shouldBeDisplayed
import app_common.shouldMatchText
import app_common.typeTextInInput
import com.backbase.accounts_journey.R
import com.backbase.accounts_journey.presentation.accountlist.ui.AccountListFragment
import screens.components.AccountComponent

fun accountListScreen(func: AccountListScreen.() -> Unit): AccountListScreen {
    return AccountListScreen().apply(func)
}
class AccountListScreen : BaseScreen() {

    val currentAccountsTitle: ViewInteraction = onView(withId(R.id.account_header))
    val searchInput: ViewInteraction = onView(withId(R.id.searchTextInput))
    val noAccountImg: ViewInteraction = onView(withId(R.id.no_account_image))
    val accountsSearchResultTxt: ViewInteraction = onView(withId(R.id.accounts_result_text))
    val accountListSwipeContainer: ViewInteraction = onView(withId(R.id.accountlist_swipe_container))

    fun launch(
        navController: NavController? = null,
        forceRTL: Boolean = false
    ): FragmentJourneyScenario<AccountListFragment> {
        val defaultNavController = TestNavHostController(ApplicationProvider.getApplicationContext()).apply {
            setViewModelStore(ViewModelStore())
            setGraph(R.navigation.account_journey_nav_graph)
            setCurrentDestination(R.id.accountListFragment)
        }

        val testNavController = navController ?: defaultNavController

        return launchInJourneyContainer(
            forceRTL = forceRTL
        ) {
            AccountListFragment().also { screen ->
                screen.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        Navigation.setViewNavController(screen.requireView(), testNavController)
                    }
                }
            }
        }.also {
            it.onFragment { _ ->
                // If you need to perform any additional configuration, you can do it here
            }
        }
    }

    fun accountWithName(accountName: String, func: AccountComponent.() -> Unit): AccountComponent {
        return AccountComponent(accountName).apply(func)
    }
    fun typeSearchQuery(query: String) {
        searchInput.typeTextInInput(query)
    }

    fun emptySearchResultViewIsDisplayed() {
        noAccountImg.shouldBeDisplayed()
        accountsSearchResultTxt.shouldMatchText(R.string.no_accounts)
    }

    fun pullToRefresh() {
        accountListSwipeContainer.pullToRefresh()
    }

    fun errorViewIsDisplayed(@StringRes error: Int) {
        noAccountImg.shouldBeDisplayed()
        accountsSearchResultTxt.shouldMatchText(error)
    }
}
