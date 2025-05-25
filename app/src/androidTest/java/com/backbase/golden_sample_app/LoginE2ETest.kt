package com.backbase.golden_sample_app

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.backbase.android.test_data.shouldBeDisplayed
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginE2ETest : BaseE2ETest() {

    @Test
    fun displayCreatePasscode() {
        login()
        keyboard1Button.shouldBeDisplayed()
        keyboard2Button.shouldBeDisplayed()
        keyboard3Button.shouldBeDisplayed()
        keyboard4Button.shouldBeDisplayed()
        keyboard5Button.shouldBeDisplayed()
        keyboard6Button.shouldBeDisplayed()
        keyboard7Button.shouldBeDisplayed()
        keyboard8Button.shouldBeDisplayed()
        keyboard9Button.shouldBeDisplayed()
        keyboard0Button.shouldBeDisplayed()
        passcodeDescription.shouldBeDisplayed()
    }

    @Test
    fun displayConfirmPasscode() {
        login()
        enterPasscode()
        passcodeDescription.shouldBeDisplayed()
    }

    @Test
    fun displayConfirmButton() {
        login()
        enterPasscode()
        enterPasscode()
        confirmButton.shouldBeDisplayed()
    }
}
