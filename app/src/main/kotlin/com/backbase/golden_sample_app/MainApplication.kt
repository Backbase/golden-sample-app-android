package com.backbase.golden_sample_app

import android.app.Application
import android.util.Log
import com.backbase.analytics.event.ApiEvent
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.fido.FidoUafFacetUtils
import com.backbase.android.identity.journey.authentication.initAuthenticationJourney
import com.backbase.android.identity.journey.authentication.stopAuthenticationJourney
import com.backbase.android.observability.Tracker
import com.backbase.android.observability.event.ScreenViewEvent
import com.backbase.android.observability.event.UserActionEvent
import com.backbase.app_common.COMMON_MAIN_COROUTINE_SCOPE_QUALIFIER
import com.backbase.app_common.auth.CompositeSessionListener
import com.backbase.app_common.sdk.initializeAuthClient
import com.backbase.app_common.sdk.initializeBackbase
import com.backbase.app_common.sdk.startKoinIfNotStarted
import com.backbase.golden_sample_app.common.TAG
import com.backbase.golden_sample_app.journey.accounts.injectAccountsJourney
import com.backbase.golden_sample_app.journey.workspaces.injectWorkspacesJourney
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

/**
 * Setup the necessary dependencies and configurations.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
class MainApplication : Application() {

    private val tracker: Tracker by inject()

    private val scope: CoroutineScope by inject(qualifier = COMMON_MAIN_COROUTINE_SCOPE_QUALIFIER)

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            BBLogger.setLogLevel(BBLogger.LogLevel.DEBUG)
            BBLogger.debug(TAG, "Facet ID: <${FidoUafFacetUtils.getFacetID(this)}>")
        }
        startKoinIfNotStarted()
        initializeBackbase()
        initializeAuthClient(CompositeSessionListener)

        setupDependencies()

        initAuthenticationJourney()
        setupAnalytics()
    }

    private fun setupDependencies() {
        loadKoinModules(
            getDependenciesDeclaration()
        )
        injectWorkspacesJourney()
        injectAccountsJourney()
    }

    private fun setupAnalytics() {
        tracker.subscribe(this, ScreenViewEvent::class, scope) { event ->
            // In this code block we can start receiving events from the analytics framework.
            // After that, you can start forwarding it to the Analytics tools of your choice.
            // Here we receive the Screen View Events.

            // Samples (Actual method call could be different, please refer to the actual Analytics tools documentation)
            // Firebase.logEvent(${event.name})
            // Adjust.screen(${event.name})
            Log.d("Tracker", "Tracked screen view: ${event.name}")
        }
        tracker.subscribe(this, UserActionEvent::class, scope) { event ->
            // Same can be done with the User Action Events.
            Log.d("Tracker", "Tracked user action: ${event.name}")
        }
        tracker.subscribe(this, ApiEvent::class, scope) { event ->
            // Our custom event to track API Events.
            Log.d("Tracker", event.uri)

//            firebaseAnalytics.logEvent("api_event") {
//                param("uri", event.uri)
//                param("request_method", event.requestMethod)
//                param("body", event.body)
//            }
        }
    }

    override fun onTerminate() {
        stopAuthenticationJourney()
        super.onTerminate()
    }
}
