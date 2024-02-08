package testCases

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import app_common.BaseTest
import app_common.provideAccountsJourneyDependencies
import app_common.shouldBeDisplayed
import com.backbase.accounts_journey.R
import com.backbase.accounts_journey.presentation.accountlist.ui.AccountListFragment
import com.backbase.android.retail.journey.test.launchScreen
import com.backbase.android.retail.journey.test.view.withIndex
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import screens.accountListScreen

class AccountsListTests : BaseTest() {
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

    @Test
    fun openAccountDetails() {
        accountListScreen {
            typeSearchQuery(ACCOUNT_NAME)
            accountWithName(ACCOUNT_NAME) {
                accountNameIsDisplayed()
                accountBalanceIsDisplayed(ACCOUNT_BALANCE)
                selectAccountFromListWithIndex(0)
            }
        }
    }

    companion object {
        const val ACCOUNT_NAME = "Alpha"
        const val ACCOUNT_BALANCE = "45.89"
    }
}
