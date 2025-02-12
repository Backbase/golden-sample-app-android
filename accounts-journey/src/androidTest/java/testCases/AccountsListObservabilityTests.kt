package testCases

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import app_common.BaseTest
import app_common.provideAccountsJourneyDependencies
import com.backbase.accounts_journey.R
import com.backbase.accounts_journey.presentation.accountlist.ui.AccountListFragment
import com.backbase.android.observability.Tracker
import com.backbase.android.observability.TrackerProvider
import com.backbase.android.observability.event.ScreenViewEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
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
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInContainer(themeResId = R.style.AppTheme) {
            AccountListFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        navController.setGraph(R.navigation.account_journey_nav_graph)
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }

        assertEquals(screenViewEvent.name, "accounts")
    }

    private fun setupAnalytics(callback: (ScreenViewEvent) -> Unit) {
        tracker.subscribe(this, ScreenViewEvent::class, scope) { event ->
            callback(event)
        }
    }
}
