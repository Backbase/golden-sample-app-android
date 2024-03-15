package com.backbase.android.retail

import android.app.Application
import com.backbase.android.clients.auth.AuthClient
import com.backbase.android.dbs.dataproviders.NetworkDBSDataProvider
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.android.plugins.storage.persistent.EncryptedStorage
import com.backbase.android.retail.authorization.authenticationAppModule
import com.backbase.android.retail.journey.NavigationEventEmitter
import com.backbase.android.retail.journey.SessionEmitter
import com.backbase.android.secure.storage.SecureStorageFactory
import com.backbase.android.secure.storage.SecureStorageInfo
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.startKoinForApplication() = startKoin {
    androidContext(this@startKoinForApplication)
    loadKoinModules(
        listOf(
            appModule(),
            authenticationAppModule()
        )
    )
}

internal fun appModule() = module {
    factory {
        BackbaseClient
    }

    single {
        NetworkDBSDataProvider(get())
    }

    factory<StorageComponent> {
        BackbaseClient.getRegisteredPlugin(EncryptedStorage::class.java)!!.storageComponent
    }

    single {
        val result: SecureStorageInfo = runBlocking {
            SecureStorageFactory.createWithMigration(get())
        }
        result.storage
    }

    factory<AuthClient> {
        BackbaseClient.authClient
    }

    single<SessionEmitter> {
        AppSessionEmitter
    }

    factory<NavigationEventEmitter> {
        DefaultNavigationEventEmitter(BackbaseClient)
    }
}
