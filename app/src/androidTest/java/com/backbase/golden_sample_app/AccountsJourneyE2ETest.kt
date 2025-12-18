package com.backbase.golden_sample_app

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.backbase.accounts_journey.R
import com.backbase.android.test_data.hasItems
import com.backbase.android.test_data.shouldBeDisplayed
import com.backbase.android.test_data.shouldMatchText
import com.backbase.android.test_data.typeTextInInput
import com.backbase.android.test_data.waitForRecyclerViewToHaveItems
import com.backbase.android.test_data.waitForView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AccountsJourneyE2ETest : BaseE2ETest() {

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
        searchInput.typeTextInInput("Sara")

        accountList.check(matches(hasDescendant(withText("Sara's Current Account 1"))))
    }

    @Test
    fun emptySearchResultIsDisplayed() {
        searchInput.typeTextInInput("Search query without result")

        noAccountImg.shouldBeDisplayed()
        accountsSearchResultTxt.shouldMatchText(R.string.no_accounts)
    }

    @Test
    fun accountDetailShouldBeDisplayed() {
        accountList.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )
        accountDetailHeader.waitForView()

        accountDetailHeader.shouldBeDisplayed()
    }
}
