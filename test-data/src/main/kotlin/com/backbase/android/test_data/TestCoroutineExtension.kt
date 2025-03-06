package com.backbase.android.test_data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
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
    var testDispatcherProvider: TestDispatcher
}

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
            coroutineTest.testDispatcherProvider = dispatcher
        }
    }

    override fun beforeAll(context: ExtensionContext?) {
        Dispatchers.setMain(dispatcher)
    }

    override fun afterAll(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}
