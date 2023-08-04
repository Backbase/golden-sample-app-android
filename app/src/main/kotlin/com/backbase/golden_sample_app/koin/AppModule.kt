package com.backbase.golden_sample_app.koin

import com.backbase.android.Backbase
import com.backbase.android.identity.journey.authentication.AuthenticationRouter
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.android.plugins.storage.persistent.EncryptedStorage
import com.backbase.golden_sample_app.common.user.User
import com.backbase.golden_sample_app.common.user.UserRepository
import com.backbase.golden_sample_app.common.user.UserRepositoryImpl
import com.backbase.golden_sample_app.router.AppRouter
import com.backbase.golden_sample_app.router.AppRouting
import com.backbase.golden_sample_app.router.AuthenticationRouterImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

internal val appModule = module {
    factory { CoroutineScope(Dispatchers.IO + SupervisorJob()) }

    single { User {} }

    single<AppRouting> { AppRouter() }

    factory<StorageComponent> { get<Backbase>().getRegisteredPlugin(EncryptedStorage::class.java)!!.storageComponent }

    factory<UserRepository> { UserRepositoryImpl(get()) }

    factory<AuthenticationRouter> {
        AuthenticationRouterImpl(get(), get(), get())
    }
}
