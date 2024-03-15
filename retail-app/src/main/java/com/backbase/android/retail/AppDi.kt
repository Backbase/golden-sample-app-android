package com.backbase.android.retail

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.dbs.dataproviders.NetworkDBSDataProvider
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.android.plugins.storage.persistent.EncryptedStorage
import com.backbase.android.retail.authorization.authenticationModule
import com.backbase.android.secure.storage.SecureStorageFactory
import com.backbase.android.secure.storage.SecureStorageInfo
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
            authenticationModule(),
            module {
                factory<StorageComponent> {
                    get<Backbase>().getRegisteredPlugin(EncryptedStorage::class.java)!!.storageComponent
                }

                single {
                    val result: SecureStorageInfo = runBlocking {
                        SecureStorageFactory.createWithMigration(get())
                    }
                    result.storage
                }
            }
        )
    )
}

internal fun appModule() = module {
    factory { CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate) }
    single { NetworkDBSDataProvider(get()) }
}
