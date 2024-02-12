package testCases

import app_common.BaseTest
import app_common.provideAccountsJourneyDependencies
import com.backbase.accounts_journey.presentation.accountlist.ui.AccountListFragment
import com.backbase.android.observability.Tracker
import com.backbase.android.observability.TrackerProvider
import com.backbase.android.observability.event.ScreenViewEvent
import com.backbase.android.retail.journey.test.launchScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AccountsListObservabilityTests : BaseTest() {

    private val tracker: Tracker = TrackerProvider.create(Dispatchers.Main.immediate)
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @Before
    fun setupConfigurationAndLaunchScreen() {
        provideAccountsJourneyDependencies(tracker = tracker)
    }

    @Test
    fun accountScreenIsTracked() {
        lateinit var screenViewEvent: ScreenViewEvent
        val callback: (ScreenViewEvent) -> Unit = { event ->
            screenViewEvent = event
        }
        setupAnalytics(callback)
        launchScreen<AccountListFragment>()

        assertEquals(screenViewEvent.name, "accounts")
    }

    private fun setupAnalytics(callback: (ScreenViewEvent) -> Unit) {
        tracker.subscribe(this, ScreenViewEvent::class, scope) { event ->
            callback(event)
        }
    }
}
