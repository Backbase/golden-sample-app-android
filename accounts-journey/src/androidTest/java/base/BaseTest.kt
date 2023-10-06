package base

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin

abstract class BaseTest {
    /**
     * Shortcut to a context from the target application. Useful for instantiating configurations.
     */
    private val targetContext: Context
        get() = InstrumentationRegistry.getInstrumentation().targetContext

    /**
     * Start Koin before each test. Optionally implement [setUp] to load Koin modules once Koin has started.
     *
     * Stops previous instances of Koin before starting the new one. [stopKoin] is called here and intentionally *not*
     * called in an @After function to avoid race conditions with Journey fragments that unload Koin modules in their
     * onDestroy functions.
     */
    @Before
    fun startKoin() {
        stopKoin()
        org.koin.core.context.startKoin {
            androidContext(targetContext.applicationContext)
            setUp()
        }
    }

    /**
     * Hook for providing additional dependencies before tests.
     */
    open fun setUp() = Unit
}