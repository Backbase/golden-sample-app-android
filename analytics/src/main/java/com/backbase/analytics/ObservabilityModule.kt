package com.backbase.analytics

import android.content.Context
import com.backbase.android.observability.TrackerProvider
import com.backbase.android.sdk.enableBackbaseOpenTelemetryConnector
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

fun observabilityModule(scope: CoroutineScope, context: Context) = module {
    val tracker = TrackerProvider.create()
    single { tracker }

    tracker.enableBackbaseOpenTelemetryConnector(
        apiKey = "c28c97f4-cef3-4357-b94d-1ae76f684ea5",
        scope = scope,
        context = context
    )
}
