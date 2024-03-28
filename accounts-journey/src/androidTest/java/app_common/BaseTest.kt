package app_common

import android.content.Context
import android.content.res.Configuration
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.util.Locale




@ExperimentalCoroutinesApi
open class BaseTest : KoinTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    open fun setup() {
        stopKoin()
        // change the local to run the tests in different local
        setLocale("en")
        Dispatchers.setMain(testDispatcher)
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
    }

     fun setLocale(language: String) {
        val context: Context = InstrumentationRegistry.getInstrumentation().getTargetContext()
        val config: Configuration = context.getResources().getConfiguration()
        config.setLocale(Locale(language))
        context.createConfigurationContext(config)
    }

}
