package tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import base.BaseTest
import base.provideContactsJourneyDependencies
import com.backbase.accounts_journey.AccountsJourney
import com.backbase.accounts_journey.presentation.ui.AccountListFragment
import com.backbase.android.retail.journey.test.launchDialogFragment
import com.backbase.android.retail.journey.test.launchJourney
import org.junit.Test
import org.junit.runner.RunWith
import com.backbase.android.retail.journey.test.launchScreen

class AccountsListTests: BaseTest() {

    @Test
    fun accountIsDisplayedInList() {
        //provideContactsJourneyDependencies()
        launchScreen<AccountListFragment>()
    }
}