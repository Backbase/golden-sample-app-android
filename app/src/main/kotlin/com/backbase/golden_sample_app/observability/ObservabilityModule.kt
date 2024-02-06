package com.backbase.golden_sample_app.observability

import com.backbase.android.observability.TrackerProvider
import org.koin.dsl.module

val observabilityModule = module {
    single { TrackerProvider.create() }
}
