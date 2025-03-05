package com.backbase.accounts_journey

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestInstancePostProcessor

@ExperimentalCoroutinesApi
@ExtendWith(TestCoroutineExtension::class)
interface CoroutineTest {
    var testScope: TestScope
}

/**
 * JUnit 5 Extension for automatically creating a [UnconfinedTestDispatcher],
 * then a [TestCoroutineScope] with the same CoroutineContext.
 *
 * [TestCoroutineScope.cleanupTestCoroutines] is called in afterEach
 * instead of afterAll in case Lifecycle.PER_CLASS is selected,
 * and will cause an exception if any coroutines are leaked.
 *
 * Usage of an extension in a Kotlin JUnit 5 test:
 *
 * class MyTest : CoroutineTest {
 *
 *   override lateinit var testScope: TestScope
 *   override lateinit var testDispatcherProvider: DispatcherProvider
 * }
 *
 */
@ExperimentalCoroutinesApi
class TestCoroutineExtension :
    TestInstancePostProcessor,
    BeforeAllCallback,
    AfterAllCallback {

    private val dispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(dispatcher)

    override fun postProcessTestInstance(testInstance: Any?, context: ExtensionContext?) {
        (testInstance as? CoroutineTest)?.let { coroutineTest ->
            coroutineTest.testScope = testScope
        }
    }

    override fun beforeAll(context: ExtensionContext?) {
        Dispatchers.setMain(dispatcher)
    }

    override fun afterAll(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}
