package com.backbase.android.test_data

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.espresso.matcher.ViewMatchers.withText

@Suppress("SwallowedException")
fun ViewInteraction.waitForView(timeoutMs: Long = 10_000, intervalMs: Long = 500) {
    val endTime = System.currentTimeMillis() + timeoutMs
    do {
        try {
            this.check(matches(isDisplayed()))
            return // Success
        } catch (e: NoMatchingViewException) {
            // View not found yet
        } catch (e: AssertionError) {
            // View found but not displayed yet
        }
        Thread.sleep(intervalMs)
    } while (System.currentTimeMillis() < endTime)
    throw AssertionError("View $this not displayed after $timeoutMs ms")
}

@Suppress("SwallowedException", "TooGenericExceptionCaught")
fun ViewInteraction.waitForRecyclerViewToHaveItems(
    timeoutMs: Long = 10_000,
    intervalMs: Long = 500
) {
    val endTime = System.currentTimeMillis() + timeoutMs
    do {
        try {
            this.check { view, _ ->
                val recyclerView = view as RecyclerView
                if ((recyclerView.adapter?.itemCount ?: 0) > 0) {
                    return@check
                }
                throw AssertionError("RecyclerView has no items yet")
            }
            return // Items are present
        } catch (e: Throwable) {
            Thread.sleep(intervalMs)
        }
    } while (System.currentTimeMillis() < endTime)

    throw AssertionError("RecyclerView did not get items in $timeoutMs ms")
}

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
    perform(swipeDown())

fun ViewInteraction.click(): ViewInteraction =
    perform(androidx.test.espresso.action.ViewActions.click())

fun ViewInteraction.shouldHaveDescendant(text: String): ViewInteraction =
    check(matches(hasDescendant(withText(text))))

fun ViewInteraction.clickOnRecyclerViewItem(position: Int): ViewInteraction =
    perform(
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            position,
            androidx.test.espresso.action.ViewActions.click()
        )
    )

fun ViewInteraction.hasItems(): ViewInteraction {
    ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null) throw noViewFoundException
        val recyclerView = view as RecyclerView
        val itemCount = recyclerView.adapter?.itemCount ?: 0
        assert(itemCount > 0) {
            "RecyclerView has no items. Item count = $itemCount"
        }
    }
    return this
}
