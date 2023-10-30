package screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import app_common.BaseScreen
import app_common.shouldBeDisplayed
import app_common.shouldMatchText
import app_common.typeTextInInput
import com.backbase.accounts_journey.R
import screens.components.AccountComponent

fun accountListScreen(func: AccountListScreen.() -> Unit): AccountListScreen {
    return AccountListScreen().apply(func)
}
class AccountListScreen: BaseScreen() {

    val currentAccountsTitle: ViewInteraction = onView(withId(R.id.account_header))
    val searchInput: ViewInteraction = onView(withId(R.id.searchTextInput))
    val noAccountImg: ViewInteraction = onView(withId(R.id.no_account_image))
    val accountsSearchResultTxt: ViewInteraction = onView(withId(R.id.accounts_result_text))

    fun accountWithName(accountName: String, func: AccountComponent.() -> Unit): AccountComponent {
        return AccountComponent(accountName).apply(func)
    }
    fun typeSearchQuery(query: String){
        searchInput.typeTextInInput(query)
    }

    fun emptySearchResultViewIsDisplayed() {
        noAccountImg.shouldBeDisplayed()
        accountsSearchResultTxt.shouldMatchText(R.string.no_accounts)
    }
}
