package screens.components

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.backbase.accounts_journey.R
import com.backbase.android.test_data.shouldBeDisplayed
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class AccountHeaderComponent(accountHeaderName: String) {

    private val accountHeaderNameMatcher: Matcher<View> =
        allOf(withId(R.id.account_header), withText(accountHeaderName))

    private val accountHeaderText: ViewInteraction = onView(accountHeaderNameMatcher)

    fun accountHeaderIsDisplayed() {
        accountHeaderText.shouldBeDisplayed()
    }
}
