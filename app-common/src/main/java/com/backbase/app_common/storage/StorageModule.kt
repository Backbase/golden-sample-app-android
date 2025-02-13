/*
 * Created by Backbase R&D B.V on 04/07/2024.
 */
package com.backbase.app_common.storage

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.android.plugins.storage.persistent.EncryptedStorage
import com.backbase.android.secure.storage.SecureStorage
import com.backbase.android.secure.storage.SecureStorageFactory
import com.backbase.android.secure.storage.SecureStorageInfo
import kotlinx.coroutines.runBlocking
import org.koin.dsl.module

/**
 * Dependency injection setup for storage-related components.
 *
 * This module provides both the legacy [StorageComponent] and the new [SecureStorage] for secure data storage.
 * It supports a transitional phase where the old [StorageComponent] is still needed but will eventually be
 * deprecated as the system migrates to [SecureStorage].
 *
 * **Note:** [StorageComponent] will be removed once the migration to [SecureStorage] is complete.
 *
 * @receiver Application context used for setting up the storage dependencies.
 * @return Koin module defining the storage-related dependencies.
 */
fun storageModule(context: Context) = module {
    // Provide the legacy StorageComponent
    single<StorageComponent> {
        (get<Backbase>().getRegisteredPlugin(EncryptedStorage::class.java) as EncryptedStorage).storageComponent
    }

    // Create and provide the new SecureStorage
    val storage = createSecureStorage(context)
    single<SecureStorage> {
        storage
    }

    // Provide a wrapper around SecureStorage for additional functionality
    single { SecureStorageWrapper(storage) }
}

/**
 * Creates a [SecureStorage] instance, potentially with data migration from the legacy storage solution.
 *
 * This method ensures that the [SecureStorage] is initialized with any necessary migration from previous
 * storage mechanisms, ensuring a smooth transition to secure storage.
 *
 * @param context The [Context] used to initialize the [SecureStorage] with migration support.
 * @return The created [SecureStorage] instance.
 */
fun createSecureStorage(context: Context): SecureStorage {
    return runBlocking {
        val result: SecureStorageInfo = SecureStorageFactory.createWithMigration(context)
        result.storage
    }
}
