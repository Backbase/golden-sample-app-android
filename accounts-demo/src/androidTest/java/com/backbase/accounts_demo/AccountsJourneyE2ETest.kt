package com.backbase.accounts_demo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.backbase.accounts_demo.presentation.MainActivity
import com.backbase.accounts_journey.R
import com.backbase.android.test_data.click
import com.backbase.android.test_data.clickOnRecyclerViewItem
import com.backbase.android.test_data.hasItems
import com.backbase.android.test_data.shouldBeDisplayed
import com.backbase.android.test_data.shouldHaveDescendant
import com.backbase.android.test_data.shouldMatchText
import com.backbase.android.test_data.typeTextInInput
import com.backbase.android.test_data.waitForRecyclerViewToHaveItems
import com.backbase.android.test_data.waitForView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.backbase.android.identity.journey.authentication.R as IdentityJourneyR

@RunWith(AndroidJUnit4::class)
class AccountsJourneyE2ETest {

    @JvmField
    @Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    private val usernameInput: ViewInteraction =
        onView(withId(IdentityJourneyR.id.authenticationJourney_usernameScreen_usernameField))
    private val passwordInput: ViewInteraction =
        onView(withId(IdentityJourneyR.id.authenticationJourney_usernameScreen_passwordField))
    private val loginButton: ViewInteraction =
        onView(withId(IdentityJourneyR.id.authenticationJourney_usernameScreen_loginButton))

    private val keyboard1Button: ViewInteraction =
        onView(withId(IdentityJourneyR.id.authenticationJourney_passcodeKeyboard_1Button))
    private val keyboard4Button: ViewInteraction =
        onView(withId(IdentityJourneyR.id.authenticationJourney_passcodeKeyboard_4Button))
    private val keyboard7Button: ViewInteraction =
        onView(withId(IdentityJourneyR.id.authenticationJourney_passcodeKeyboard_7Button))
    private val keyboard8Button: ViewInteraction =
        onView(withId(IdentityJourneyR.id.authenticationJourney_passcodeKeyboard_8Button))
    private val keyboard9Button: ViewInteraction =
        onView(withId(IdentityJourneyR.id.authenticationJourney_passcodeKeyboard_9Button))

    private val confirmButton: ViewInteraction =
        onView(withId(IdentityJourneyR.id.authenticationJourney_flowCompleteScreen_confirmButton))

    private val accountHeader: ViewInteraction = onView(withId(R.id.header))
    private val accountList: ViewInteraction = onView(withId(R.id.accountlist))
    private val searchInput: ViewInteraction = onView(withId(R.id.searchTextInput))
    private val noAccountImg: ViewInteraction = onView(withId(R.id.no_account_image))
    private val accountsSearchResultTxt: ViewInteraction = onView(withId(R.id.accounts_result_text))

    private val accountDetailHeader: ViewInteraction = onView(withId(R.id.header_account))

    @Before
    fun setUp() {
        login()
        setupPasscode()
        accountList.waitForRecyclerViewToHaveItems()
    }

    @Test
    fun accountListShouldBeDisplayed() {
        accountHeader.shouldBeDisplayed()
        accountList
            .shouldBeDisplayed()
            .hasItems()
    }

    @Test
    fun successfulSearchAccountsAreDisplayed() {
        searchInput.typeTextInInput("John")

        accountList.shouldHaveDescendant("John's Current Account 1")
    }

    @Test
    fun emptySearchResultIsDisplayed() {
        searchInput.typeTextInInput("Search query without result")

        noAccountImg.shouldBeDisplayed()
        accountsSearchResultTxt.shouldMatchText(R.string.no_accounts)
    }

    @Test
    fun accountDetailShouldBeDisplayed() {
        accountList.clickOnRecyclerViewItem(1)
        accountDetailHeader.waitForView()

        accountDetailHeader.shouldBeDisplayed()
    }

    private fun login() {
        usernameInput.typeTextInInput(BuildConfig.TEST_ACCOUNT_USERNAME)
        passwordInput.typeTextInInput(BuildConfig.TEST_ACCOUNT_PASSWORD)
        loginButton.click()
    }

    private fun setupPasscode() {
        enterPasscode() // Enter the passcode
        enterPasscode() // Confirm the passcode
        confirmButton.click()
    }

    private fun enterPasscode() {
        keyboard1Button.click()
        keyboard4Button.click()
        keyboard7Button.click()
        keyboard8Button.click()
        keyboard9Button.click()
    }
}
