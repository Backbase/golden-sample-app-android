package com.backbase.golden_sample_app.security

import android.content.Context
import com.backbase.android.sdk.security.ObservabilityBreachNotifier
import com.backbase.android.sdk.security.SecurityConfiguration
import com.backbase.android.sdk.security.SecurityVerificationFactory
import com.backbase.android.sdk.security.debug.DebugDetector
import com.backbase.android.sdk.security.emulator.EmulatorDetector
import com.backbase.android.sdk.security.root.RootDetector
import com.backbase.app_common.COMMON_MAIN_COROUTINE_SCOPE_QUALIFIER
import org.koin.dsl.module

internal fun securityModule(
    context: Context,
) = module {
    factory<ObservabilityBreachNotifier> {
        ObservabilityBreachNotifier(
            tracker = get(),
            scope = get(qualifier = COMMON_MAIN_COROUTINE_SCOPE_QUALIFIER)
        )
    }

    factory<SecurityConfiguration> {
        SecurityConfiguration(get()) {
            generalDetectors = mutableListOf()
            rootDetectors = mutableListOf(RootDetector(context))
            debugDetectors = mutableListOf(DebugDetector(context))
            emulatorDetector = mutableListOf(EmulatorDetector(context))
        }
    }

    single { SecurityVerificationFactory.create(get()) }
}
