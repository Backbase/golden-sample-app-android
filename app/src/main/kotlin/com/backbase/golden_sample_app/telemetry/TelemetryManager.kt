package com.backbase.golden_sample_app.telemetry

import com.backbase.android.core.utils.BBLogger
import com.backbase.android.observability.Tracker
import com.backbase.android.observability.event.ScreenViewEvent
import com.backbase.android.observability.event.UserActionEvent
import com.backbase.golden_sample_app.common.TAG
import kotlinx.coroutines.CoroutineScope
import java.util.UUID
import io.opentelemetry.api.common.AttributeKey
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.trace.SpanKind
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter
import io.opentelemetry.sdk.OpenTelemetrySdk
import io.opentelemetry.sdk.resources.Resource
import io.opentelemetry.sdk.trace.SdkTracerProvider
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor


/**
 * Created by Backbase R&D B.V. on 10/01/2024.
 */
class TelemetryManager(
    private val tracker: Tracker,
    private val scope: CoroutineScope,
    private val endPoint: String,
    private val appKey: String
) {

    private companion object {
        const val BACKBASE_TRACER_PROVIDER = "@backbase/observability"
        const val BACKBASE_TRACER_PROVIDER_VERSION = "1.0.0"
        const val SESSION_ID = "session_id"
        const val BB_APP_KEY = "BB-App-Key"
        const val SCREEN_VIEW = "screen-view"
        const val USER_ACTION = "user-action"
    }

    init {
        val telemetrySdk = configureTelemetrySdk()
        subscribeToEvents(telemetrySdk)
    }

    private fun subscribeToEvents(telemetrySdk: OpenTelemetrySdk) {
        val backbaseTracerProvider = telemetrySdk.tracerProvider.get(
            BACKBASE_TRACER_PROVIDER,
            BACKBASE_TRACER_PROVIDER_VERSION
        )
        tracker.subscribe(this@TelemetryManager, ScreenViewEvent::class, scope) { event ->
            BBLogger.debug(TAG, "ScreenViewEvent:${event.name}")
            backbaseTracerProvider
                .spanBuilder(SCREEN_VIEW)
                .setSpanKind(SpanKind.CLIENT)
                .startSpan()
                .setAttribute(SCREEN_VIEW, event.name)
                .end()
        }

        tracker.subscribe(this@TelemetryManager, UserActionEvent::class, scope) { event ->
            BBLogger.debug(TAG, "UserActionEvent:${event.name}")
            backbaseTracerProvider
                .spanBuilder(USER_ACTION)
                .setSpanKind(SpanKind.CLIENT)
                .startSpan()
                .setAttribute(USER_ACTION, event.name)
                .end()
        }
    }

    private fun configureTelemetrySdk(): OpenTelemetrySdk {
        return OpenTelemetrySdk.builder()
            .setTracerProvider(
                SdkTracerProvider.builder()
                    .addResource(
                        Resource.create(
                            Attributes.of(
                                AttributeKey.stringKey(SESSION_ID),
                                UUID.randomUUID().toString()
                            )
                        )
                    )
                    .addSpanProcessor(
                        SimpleSpanProcessor.create(
                            OtlpHttpSpanExporter.builder()
                                .setEndpoint(endPoint)
                                .addHeader(BB_APP_KEY, appKey)
                                .build()
                        )
                    )
                    .build()
            ).build();
    }
}
