package com.backbase.analytics

import com.backbase.android.observability.TrackerProvider
import org.koin.dsl.module

fun observabilityModule() = module {
    single { TrackerProvider.create() }
}
