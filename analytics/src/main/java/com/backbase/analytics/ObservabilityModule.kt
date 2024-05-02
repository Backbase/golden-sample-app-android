package com.backbase.analytics

import com.backbase.android.observability.TrackerProvider
import com.backbase.android.sdk.enableBBOpenTelemetryConnector
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

fun observabilityModule(scope: CoroutineScope) = module {
    val tracker = TrackerProvider.create()
    single { tracker }

    tracker.enableBBOpenTelemetryConnector(apiKey = "c28c97f4-cef3-4357-b94d-1ae76f684ea5", scope = scope)
}
