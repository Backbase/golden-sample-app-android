package app_common

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.espresso.matcher.ViewMatchers.withText

fun ViewInteraction.shouldBeDisplayed(): ViewInteraction = check(matches(isDisplayed()))

fun ViewInteraction.shouldMatchText(stringResource: Int): ViewInteraction =
    check(matches(withText(stringResource)))

fun ViewInteraction.shouldMatchText(expectedText: String): ViewInteraction = check(
    matches(withText(expectedText))
)

fun ViewInteraction.shouldBeClickable(): ViewInteraction = check(matches(isClickable()))

fun ViewInteraction.shouldContainText(expectedText: String): ViewInteraction = check(
    matches(withSubstring(expectedText))
)

fun ViewInteraction.shouldNotExist(): ViewInteraction = check(doesNotExist())

fun ViewInteraction.shouldBeEnabled(): ViewInteraction = check(matches(isEnabled()))

fun ViewInteraction.typeTextInInput(text: String): ViewInteraction =
    perform(typeText(text)).perform(closeSoftKeyboard())

fun ViewInteraction.pullToRefresh(): ViewInteraction =
    perform(androidx.test.espresso.action.ViewActions.swipeDown())
