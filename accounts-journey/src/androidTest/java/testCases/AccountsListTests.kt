package testCases

import app_common.BaseTest
import app_common.provideAccountsJourneyDependencies
import app_common.shouldBeDisplayed
import com.backbase.accounts_journey.presentation.ui.AccountListFragment
import org.junit.Test
import com.backbase.android.retail.journey.test.launchScreen
import org.junit.Before
import screens.accountListScreen
import com.backbase.accounts_journey.R

class AccountsListTests: BaseTest() {

    @Before
    fun setupConfigurationAndLaunchScreen() {
        provideAccountsJourneyDependencies()
        launchScreen<AccountListFragment>()
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
