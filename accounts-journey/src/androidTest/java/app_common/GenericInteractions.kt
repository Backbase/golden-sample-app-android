package app_common

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

fun ViewInteraction.shouldBeDisplayed(): ViewInteraction =
    check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

fun ViewInteraction.shouldMatchText(stringResource: Int): ViewInteraction = check(
    ViewAssertions.matches(
        ViewMatchers.withText(stringResource)
    )
)

fun ViewInteraction.shouldMatchText(expectedText: String): ViewInteraction = check(
    ViewAssertions.matches(
        ViewMatchers.withText(expectedText)
    )
)

fun ViewInteraction.shouldBeClickable(): ViewInteraction =
    check(ViewAssertions.matches(ViewMatchers.isClickable()))

fun ViewInteraction.shouldContainText(expectedText: String): ViewInteraction = check(
    ViewAssertions.matches(
        ViewMatchers.withSubstring(expectedText)
    )
)

fun ViewInteraction.shouldNotExist(): ViewInteraction = check(
    ViewAssertions.doesNotExist()
)

fun ViewInteraction.shouldBeEnabled(): ViewInteraction =
    check(ViewAssertions.matches(ViewMatchers.isEnabled()))

fun ViewInteraction.typeText(text: String): ViewInteraction =
    perform(ViewActions.typeText(text)).perform(ViewActions.closeSoftKeyboard())
