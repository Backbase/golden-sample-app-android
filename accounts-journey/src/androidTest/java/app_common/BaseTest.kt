package app_common

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.backbase.accounts_journey.presentation.accountlist.ui.AccountListFragment
import com.backbase.android.retail.journey.test.launchScreen
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

open class BaseTest {

    private val targetContext: Context
        get() = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun startKoinBeforeTest() {
        stopKoin()
        startKoin {
            androidContext(targetContext.applicationContext)
            setUp()
        }
        provideAccountsJourneyDependencies()
        launchScreen<AccountListFragment>()
    }

    open fun setUp() = Unit
}
