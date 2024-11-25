package testCases

import androidx.test.filters.MediumTest
import app_common.BaseTest
import app_common.ScreenDirection
import app_common.prepare
import app_common.scenario.FragmentJourneyScenarioUtils.waitForFragment
import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.fake_accounts_use_case.FakeAccountsUseCase
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ScreenshotTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import resources.TEST_ACCOUNTS
import screens.accountListScreen

@ExperimentalCoroutinesApi
@RunWith(TestParameterInjector::class)
class AccountsListScreenshotTest : BaseTest(), ScreenshotTest {

    @TestParameter
    lateinit var screenDirection: ScreenDirection

    @MediumTest
    @Test
    fun givenScenarioIsLaunched_whenProvidedWithDefaultConfig_thenAccountListDefaultStateIsDisplayed(): Unit = runBlocking {
        prepare {
            loadModule(
                accountsUseCase = FakeAccountsUseCase(TEST_ACCOUNTS),
                journeyConfiguration = AccountsJourneyConfiguration {  }
            )
        }

        accountListScreen {
            val scenario = launch(forceRTL = screenDirection.value)

            compareScreenshot(
                fragment = scenario.waitForFragment(),
                name = "accounts_list_screen_${screenDirection}_screenDirection"
            )
        }
    }
}
