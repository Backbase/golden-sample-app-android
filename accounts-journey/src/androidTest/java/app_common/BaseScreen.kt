package app_common

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.backbase.accounts_journey.R
import com.backbase.android.test_data.shouldMatchText

open class BaseScreen {

    private val header: ViewInteraction = onView(withId(R.id.header))

    fun headerWithTextIsDisplayed(text: Int) {
        header.shouldMatchText(text)
    }
}
