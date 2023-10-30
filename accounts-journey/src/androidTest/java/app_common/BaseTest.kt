package app_common

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

abstract class BaseTest {

    private val targetContext: Context
        get() = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun startKoinBeforeTest() {
        stopKoin()
        startKoin {
            androidContext(targetContext.applicationContext)
            setUp()
        }
    }

    open fun setUp() = Unit
}
