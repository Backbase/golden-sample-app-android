package com.backbase.golden_sample_app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.backbase.android.identity.journey.authentication.R
import com.backbase.android.test_data.typeTextInInput
import com.backbase.golden_sample_app.presentation.MainActivity
import org.junit.Rule

open class BaseE2ETest {

    @JvmField
    @Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    internal val usernameInput: ViewInteraction =
        onView(withId(R.id.authenticationJourney_usernameScreen_usernameField))
    internal val passwordInput: ViewInteraction =
        onView(withId(R.id.authenticationJourney_usernameScreen_passwordField))
    internal val loginButton: ViewInteraction =
        onView(withId(R.id.authenticationJourney_usernameScreen_loginButton))

    internal val keyboard1Button: ViewInteraction =
        onView(withId(R.id.authenticationJourney_passcodeKeyboard_1Button))
    internal val keyboard2Button: ViewInteraction =
        onView(withId(R.id.authenticationJourney_passcodeKeyboard_2Button))
    internal val keyboard3Button: ViewInteraction =
        onView(withId(R.id.authenticationJourney_passcodeKeyboard_3Button))
    internal val keyboard4Button: ViewInteraction =
        onView(withId(R.id.authenticationJourney_passcodeKeyboard_4Button))
    internal val keyboard5Button: ViewInteraction =
        onView(withId(R.id.authenticationJourney_passcodeKeyboard_5Button))
    internal val keyboard6Button: ViewInteraction =
        onView(withId(R.id.authenticationJourney_passcodeKeyboard_6Button))
    internal val keyboard7Button: ViewInteraction =
        onView(withId(R.id.authenticationJourney_passcodeKeyboard_7Button))
    internal val keyboard8Button: ViewInteraction =
        onView(withId(R.id.authenticationJourney_passcodeKeyboard_8Button))
    internal val keyboard9Button: ViewInteraction =
        onView(withId(R.id.authenticationJourney_passcodeKeyboard_9Button))
    internal val keyboard0Button: ViewInteraction =
        onView(withId(R.id.authenticationJourney_passcodeKeyboard_0Button))

    internal val passcodeDescription: ViewInteraction =
        onView(withId(R.id.authenticationJourney_commonPasscodeScreen_descriptionView))

    internal val confirmButton: ViewInteraction =
        onView(withId(R.id.authenticationJourney_flowCompleteScreen_confirmButton))

    internal fun login() {
        usernameInput.typeTextInInput(BuildConfig.TEST_ACCOUNT_USERNAME)
        passwordInput.typeTextInInput(BuildConfig.TEST_ACCOUNT_PASSWORD)
        loginButton.perform(click())
    }

    internal fun setupPasscode() {
        enterPasscode() // Enter the passcode
        enterPasscode() // Confirm the passcode
        confirmButton.perform(click())
    }

    internal fun enterPasscode() {
        keyboard1Button.perform(click())
        keyboard4Button.perform(click())
        keyboard7Button.perform(click())
        keyboard8Button.perform(click())
        keyboard9Button.perform(click())
    }
}