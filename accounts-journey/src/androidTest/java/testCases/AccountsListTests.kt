package testCases

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import app_common.BaseTest
import app_common.provideAccountsJourneyDependencies
import com.backbase.accounts_journey.R
import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.common.NoInternetException
import com.backbase.accounts_journey.common.NoResponseException
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.generator.AccountSummaryGenerator
import com.backbase.accounts_journey.presentation.accountlist.ui.AccountListFragment
import com.backbase.accounts_use_case.ErrorAccountsUseCase
import com.backbase.accounts_use_case.SuccessAccountsUseCase
import org.junit.Test
import screens.accountListScreen

class AccountsListTests : BaseTest() {

    @Test
    fun accountIsDisplayedInTheList() {
        setupScreen()

        accountListScreen {
            headerWithTextIsDisplayed(R.string.accounts_screen_title)

            accountHeaderWithName(ACCOUNT_HEADER) {
                accountHeaderIsDisplayed()
            }

            accountWithName(ACCOUNT_NAME) {
                accountNameIsDisplayed()
                accountBalanceIsDisplayed(ACCOUNT_BALANCE)
            }
        }
    }

    @Test
    fun emptySearchResultIsDisplayed() {
        setupScreen()

        accountListScreen {
            typeSearchQuery("Search query without result")
            emptySearchResultViewIsDisplayed()
        }
    }

    @Test
    fun unknownErrorIsDisplayedInTheList() {
        setupScreen(ErrorAccountsUseCase(TEST_ACCOUNTS, error = Exception("Unknown error")))

        accountListScreen {
            errorViewIsDisplayed(R.string.failed_unknown)
        }
    }

    @Test
    fun noInternetErrorIsDisplayedInTheList() {
        setupScreen(ErrorAccountsUseCase(TEST_ACCOUNTS, error = NoInternetException()))

        accountListScreen {
            errorViewIsDisplayed(R.string.no_internet_connection)
        }
    }

    @Test
    fun noResponseErrorIsDisplayedInTheList() {
        setupScreen(ErrorAccountsUseCase(TEST_ACCOUNTS, error = NoResponseException()))

        accountListScreen {
            errorViewIsDisplayed(R.string.no_response)
        }
    }

    @Test
    fun failedGetDataErrorIsDisplayed() {
        setupScreen(ErrorAccountsUseCase(TEST_ACCOUNTS, error = FailedGetDataException()))

        accountListScreen {
            errorViewIsDisplayed(R.string.failed_get_data)
        }
    }

    @Test
    fun pullToRefreshAccounts() {
        setupScreen()

        accountListScreen {
            pullToRefresh()
            accountHeaderWithName(ACCOUNT_HEADER) {
                accountHeaderIsDisplayed()
            }
        }
    }

    @Test
    fun pullToRefreshFailed() {
        setupScreen(ErrorAccountsUseCase(TEST_ACCOUNTS, amountOfRefresh = 2))

        accountListScreen {
            pullToRefresh()
            accountHeaderWithName(ACCOUNT_HEADER) {
                accountHeaderIsDisplayed()
            }
            pullToRefresh()
            errorViewIsDisplayed(R.string.failed_unknown)
        }
    }

    private fun setupScreen(accountsUseCase: AccountsUseCase = SuccessAccountsUseCase(TEST_ACCOUNTS)) {
        provideAccountsJourneyDependencies(useCase = accountsUseCase)

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

    companion object {
        const val ACCOUNT_HEADER = "Current Accounts"
        const val ACCOUNT_NAME = "Alpha"
        const val ACCOUNT_BALANCE = "45.89"

        val TEST_ACCOUNTS = AccountSummaryGenerator.randomAccountSummaryBuilder {
            currentAccounts = AccountSummaryGenerator.randomCurrentAccounts {
                this.name = ACCOUNT_HEADER
                this.products = listOf(
                    AccountSummaryGenerator.randomCurrentAccount {
                        this.displayName = ACCOUNT_NAME
                        this.availableBalance = ACCOUNT_BALANCE
                    }.build()
                )
            }.build()
        }.build()
    }
}
