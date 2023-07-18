package com.backbase.golden_sample_app.koin

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.identity.journey.authentication.AuthenticationDeregistrationListener
import com.backbase.android.identity.journey.authentication.AuthenticationRouter
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.android.plugins.storage.persistent.EncryptedStorage
import com.backbase.golden_sample_app.router.AuthenticationDeregistrationListenerImpl
import com.backbase.golden_sample_app.router.AuthenticationRouterImpl
import org.koin.dsl.module

internal fun appDependencies(context: Context) = module {
//    factory<AuthenticationRouter> {
//        AuthenticationRouterImpl(get())
//    }

    factory<EncryptedStorage> { get<Backbase>().getRegisteredPlugin(EncryptedStorage::class.java)!! }
    factory<StorageComponent> { get<EncryptedStorage>().storageComponent }

    factory<AuthenticationDeregistrationListener> {
        AuthenticationDeregistrationListenerImpl()
    }
}