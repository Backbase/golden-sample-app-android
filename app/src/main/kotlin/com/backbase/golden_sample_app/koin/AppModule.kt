package com.backbase.golden_sample_app.koin

import android.content.Context
import com.backbase.android.dbs.dataproviders.NetworkDBSDataProvider
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
internal fun appModule(context: Context) = module {
    factory { CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate) }
    single { NetworkDBSDataProvider(context) }
    // Navigation dependencies
    single<AppRouting> { AppRouter() }
}
