package com.backbase.android.retail

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.client.contactmanagerclient2.api.ContactsApi
import com.backbase.android.clients.auth.AuthClient
import com.backbase.android.dbs.dataproviders.NetworkDBSDataProvider
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.android.plugins.storage.persistent.EncryptedStorage
import com.backbase.android.retail.authorization.authenticationAppModule
import com.backbase.android.retail.journey.NavigationEventEmitter
import com.backbase.android.retail.journey.SessionEmitter
import com.backbase.android.retail.journey.contacts.ContactsConfiguration
import com.backbase.android.retail.journey.contacts.ContactsUseCase
import com.backbase.android.retail.journey.contacts.contactmanager_client_2.GenContactManagerClient2ContactsUseCase
import com.backbase.android.secure.storage.SecureStorageFactory
import com.backbase.android.secure.storage.SecureStorageInfo
import com.backbase.golden_sample_app.user.UserRepositoryImpl
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


    factory<UserRepository> {
        UserRepositoryImpl(
            secureStorage = get(),
            coroutineScope = get(),
        )
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

    factory<ContactsUseCase> { GenContactManagerClient2ContactsUseCase(get()) }
    factory<ContactsConfiguration> { ContactsConfiguration {} }
    single { Backbase.requireInstance().getClient(ContactsApi::class.java) }



}
