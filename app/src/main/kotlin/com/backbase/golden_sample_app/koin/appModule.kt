package com.backbase.golden_sample_app.koin

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.identity.journey.authentication.AuthenticationDeregistrationListener
import com.backbase.android.identity.journey.authentication.AuthenticationRouter
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.android.plugins.storage.persistent.EncryptedStorage
import com.backbase.golden_sample_app.router.AppRouter
import com.backbase.golden_sample_app.router.AppRouting
import com.backbase.golden_sample_app.router.AuthenticationDeregistrationListenerImpl
import com.backbase.golden_sample_app.router.AuthenticationRouterImpl
import com.backbase.golden_sample_app.user.UserRepository
import com.backbase.golden_sample_app.user.UserRepositoryImpl
import org.koin.dsl.module

internal fun appModule(context: Context) = module {
    // Single instance AppNavigator
    single<AppRouting> { AppRouter() }

    factory<StorageComponent> { get<Backbase>().getRegisteredPlugin(EncryptedStorage::class.java)!!.storageComponent }

    factory<UserRepository> { UserRepositoryImpl(get()) }

    factory<AuthenticationRouter> {
        AuthenticationRouterImpl(get(), get(), get())
    }

    factory<AuthenticationDeregistrationListener> {
        AuthenticationDeregistrationListenerImpl(get(), get())
    }

}
