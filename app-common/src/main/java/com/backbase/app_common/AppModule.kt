package com.backbase.app_common

import com.backbase.android.Backbase
import com.backbase.android.clients.auth.AuthClient
import com.backbase.android.clients.common.MoshiResponseBodyParser
import com.backbase.android.clients.common.ResponseBodyParser
import com.backbase.android.clients.common.base64Adapter
import com.backbase.android.clients.common.bigDecimalAdapter
import com.backbase.android.clients.common.dateAdapter
import com.backbase.android.clients.common.dateTimeAdapter
import com.backbase.android.dbs.DBSDataProvider
import com.backbase.android.retail.journey.SessionEmitter
import com.backbase.app_common.auth.CompositeSessionListener
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.StringQualifier
import org.koin.core.scope.Scope
import org.koin.dsl.module

/**
 * Dependency setup on app level.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */

/**
 *  The Koin [Qualifier] used to denote the API root for the application.
 * This qualifier helps in identifying and retrieving the API root string from the Koin dependency graph.
 */
@get:JvmName("getCommonApiRootQualifier")
val COMMON_API_ROOT_QUALIFIER: Qualifier = StringQualifier("Application API root")

/**
 * Retrieves the API root string from the Koin scope.
 * This function uses the `COMMON_API_ROOT_QUALIFIER` to fetch the API root string
 * that was previously defined in the Koin module.
 *
 * @return The API root string for the application.
 */
fun Scope.apiRoot() = get<String>(COMMON_API_ROOT_QUALIFIER)

/**
 *  The Koin [Qualifier] used to denote the [CoroutineScope] that works in the main thread.
 */
@get:JvmName("getCommonMainCoroutineScopeQualifier")
val COMMON_MAIN_COROUTINE_SCOPE_QUALIFIER: Qualifier =
    StringQualifier("COMMON_MAIN_COROUTINE_SCOPE")

/**
 * Provides the Koin module for the core application dependencies.
 *
 * This module defines essential components and configurations used throughout the app,
 * including:
 * - [Backbase] instance as a singleton.
 * - API root string qualified with `COMMON_API_ROOT_QUALIFIER`.
 * - [DBSDataProvider] for data access.
 * - [AuthClient] for authentication.
 * - [SessionEmitter] for session tracking.
 * - A configured [Moshi] instance for JSON serialization/deserialization, including a workaround for non-standard date formats.
 * - [ResponseBodyParser] for handling API responses.
 */
fun appModule() = module {
    factory(qualifier = COMMON_MAIN_COROUTINE_SCOPE_QUALIFIER) {
        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    }

    single {
        Backbase.requireInstance()
    }

    single(qualifier = COMMON_API_ROOT_QUALIFIER) {
        Backbase.requireInstance().configuration.experienceConfiguration.apiRoot
    }

    single<DBSDataProvider> {
        CustomNetworkDBSDataProvider(
            tracker = get()
        )
    }

    factory<AuthClient> {
        Backbase.requireInstance().authClient
    }

    single<SessionEmitter> {
        CompositeSessionListener
    }

    single {
        Moshi.Builder()
            .add(bigDecimalAdapter)
            .add(dateAdapter)
            .add(dateTimeAdapter)
            .add(base64Adapter)
            .build()
    }

    factory<ResponseBodyParser> { MoshiResponseBodyParser(get()) }
}
