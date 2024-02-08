package screens.components

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import app_common.shouldBeDisplayed
import app_common.shouldContainText
import app_common.tapOnView
import com.backbase.accounts_journey.R
import com.backbase.android.retail.journey.test.view.withIndex
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matcher

/**
 * Class that provides references to the ViewInteraction objects for each view in the list items
 * Requires that the item has a unique name.
 */
class AccountComponent(accountName: String) {

    private val accountNameMatcher: Matcher<View> =
        allOf(withId(R.id.account_name), withText(accountName))

    private val accountNameTxt: ViewInteraction = onView(accountNameMatcher)

    private val accountBalanceTxt: ViewInteraction =
        onView(
            allOf(
                withId(R.id.account_balance),
                hasSibling(accountNameMatcher)
            )
        )

    private val accountList: Matcher<View?> = allOf(
        isDescendantOfA(withId(R.id.accountlist)),
        instanceOf(ConstraintLayout::class.java)
    )

    fun accountNameIsDisplayed() {
        accountNameTxt.shouldBeDisplayed()
    }

    fun accountBalanceIsDisplayed(balance: String) {
        accountBalanceTxt.shouldContainText(balance)
    }

    fun selectAccountFromListWithIndex(index: Int) {
        onView(withIndex(accountList, index = index)).tapOnView()
    }
}
