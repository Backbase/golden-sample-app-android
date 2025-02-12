package testCases

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import app_common.BaseTest
import app_common.provideAccountsJourneyDependencies
import app_common.shouldBeDisplayed
import com.backbase.accounts_journey.R
import com.backbase.accounts_journey.presentation.accountlist.ui.AccountListFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import screens.accountListScreen

@OptIn(ExperimentalCoroutinesApi::class)
class AccountsListTests : BaseTest() {

    @Before
    fun setupConfigurationAndLaunchScreen() {
        provideAccountsJourneyDependencies()

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInContainer(themeResId = R.style.AppTheme) {
            AccountListFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        navController.setGraph(R.navigation.account_journey_nav_graph)
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }
    }

    @Test
    fun accountIsDisplayedInTheList() {
        accountListScreen {
            headerWithTextIsDisplayed(R.string.accounts_screen_title)
            currentAccountsTitle.shouldBeDisplayed()

            accountWithName(ACCOUNT_NAME) {
                accountNameIsDisplayed()
                accountBalanceIsDisplayed(ACCOUNT_BALANCE)
            }
        }
    }

    @Test
    fun emptySearchResultIsDisplayed() {
        accountListScreen {
            typeSearchQuery("Search query without result")
            emptySearchResultViewIsDisplayed()
        }
    }

    companion object {
        const val ACCOUNT_NAME = "Alpha"
        const val ACCOUNT_BALANCE = "45.89"
    }
}
