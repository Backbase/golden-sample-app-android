package com.backbase.golden_sample_app.koin

import com.backbase.golden_sample_app.router.AppRouter
import com.backbase.golden_sample_app.router.AppRouting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

internal val appModule = module {
    factory { CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate) }
    single<AppRouting> { AppRouter() }
}
