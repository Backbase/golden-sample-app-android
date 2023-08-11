package com.backbase.golden_sample_app.koin

import com.backbase.golden_sample_app.router.AppRouter
import com.backbase.golden_sample_app.router.AppRouting
import org.koin.dsl.module

internal val appModule = module {
    single<AppRouting> { AppRouter() }
}
