package com.backbase.analytics

import com.backbase.android.observability.TrackerProvider
import org.koin.dsl.module

val observabilityModule = module {
    single { TrackerProvider.create() }
}
