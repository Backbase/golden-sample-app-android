package com.backbase.android.retail

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.business.journey.workspaces.WorkspacesJourney
import com.backbase.android.business.journey.workspaces.accesscontrol_client_2.WorkspacesUseCaseImpl
import com.backbase.android.business.journey.workspaces.usecase.WorkspacesUseCase
import com.backbase.android.client.gen2.accesscontrolclient3.api.UserContextApi
import com.backbase.android.client.gen2.accesscontrolclient3.api.UsersApi
import com.backbase.android.clients.auth.AuthClient
import com.backbase.android.dbs.dataproviders.NetworkDBSDataProvider
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.android.plugins.storage.persistent.EncryptedStorage
import com.backbase.android.retail.authenticaton.authenticationAppModule
import com.backbase.android.retail.feature_filter.entitlements.EntitlementsUseCase
import com.backbase.android.retail.feature_filter.entitlements.accesscontrol_client_2.AccessControlClient2EntitlementsUseCase
import com.backbase.android.retail.journey.NavigationEventEmitter
import com.backbase.android.retail.journey.SessionEmitter
import com.backbase.android.retail.journey.contacts.ContactsConfiguration
import com.backbase.android.secure.storage.SecureStorageFactory
import com.backbase.android.secure.storage.SecureStorageInfo
import com.backbase.golden_sample_app.user.UserRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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
            WorkspacesJourney.create(),
            authenticationAppModule(),
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
            coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate),
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

    single { Backbase.requireInstance().getClient(UserContextApi::class.java) }
    factory<ContactsConfiguration> { ContactsConfiguration {} }
    single { Backbase.requireInstance().getClient(UsersApi::class.java) }


    factory<WorkspacesUseCase> { WorkspacesUseCaseImpl(get(), get()) }
    single<EntitlementsUseCase> { AccessControlClient2EntitlementsUseCase(get()) }
}
