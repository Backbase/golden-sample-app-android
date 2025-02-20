package com.backbase.golden_sample_app.koin

import com.backbase.golden_sample_app.router.AppRouter
import com.backbase.golden_sample_app.router.AppRouting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

/**
 * Dependency setup on app level.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
internal fun appModule() = module {
    factory { CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate) }
    // Navigation dependencies
    single<AppRouting> { AppRouter(tabListConfig = get()) }
}
