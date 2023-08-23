package com.backbase.golden_sample_app.koin

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.android.plugins.storage.persistent.EncryptedStorage
import com.backbase.android.secure.storage.SecureStorage
import com.backbase.android.secure.storage.SecureStorageFactory
import com.backbase.android.secure.storage.SecureStorageInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.dsl.module

/**
 * Dependency setup related to security.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
internal fun securityModule(
    context: Context
) = module {
    factory<StorageComponent> { get<Backbase>().getRegisteredPlugin(EncryptedStorage::class.java)!!.storageComponent }

    runBlocking(Dispatchers.IO) {
        val result: SecureStorageInfo = SecureStorageFactory.createWithMigration(context)
        val secureStorage: SecureStorage = result.storage
        single { secureStorage }
    }
}
