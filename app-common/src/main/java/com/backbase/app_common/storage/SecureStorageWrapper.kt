/*
 * Created by Backbase R&D B.V. on 30/09/2024.
 */
package com.backbase.app_common.storage

import android.content.Context
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.secure.storage.DeleteAllResult
import com.backbase.android.secure.storage.DeleteAllWithKeyPrefixResult
import com.backbase.android.secure.storage.DeleteValueResult
import com.backbase.android.secure.storage.ReadValueResult
import com.backbase.android.secure.storage.SecureStorage
import com.backbase.android.secure.storage.SecureStorageFactory
import com.backbase.android.secure.storage.StoreValueResult
import kotlinx.coroutines.runBlocking

/**
 * Wrapper class for managing secure storage operations using [SecureStorage].
 *
 * This class provides a simplified interface to perform various secure storage operations such as
 * storing, reading, and deleting data. All operations are executed in a blocking manner using `runBlocking`
 * to ensure they are completed synchronously. The class also includes functionality for handling secure
 * storage migrations during initialization.
 *
 * @property secureStorage The underlying [SecureStorage] implementation that handles the actual
 *                         secure storage operations.
 *
 * @constructor Creates a [SecureStorageWrapper] instance with a [SecureStorage] provided.
 *              A secondary constructor initializes the [SecureStorage] by performing a migration if needed,
 *              using the provided [Context].
 */
class SecureStorageWrapper(private val secureStorage: SecureStorage) {

    /**
     * Secondary constructor that initializes the [SecureStorage] with migration support.
     *
     * @param context The [Context] used to initialize the [SecureStorage] instance.
     */
    constructor(context: Context) : this(
        secureStorage = runBlocking {
            SecureStorageFactory.createWithMigration(context).storage
        }
    )

    /**
     * Deletes all entries in the secure storage.
     *
     * @return [DeleteAllResult] indicating the result of the delete operation.
     */
    fun deleteAll(): DeleteAllResult =
        runBlocking { secureStorage.deleteAll() }

    /**
     * Deletes all entries that match the given key prefix in the secure storage.
     *
     * @param prefix The prefix of the keys to delete.
     * @return [DeleteAllWithKeyPrefixResult] indicating the result of the delete operation.
     */
    fun deleteAllWithKeyPrefix(prefix: String): DeleteAllWithKeyPrefixResult =
        runBlocking { secureStorage.deleteAllWithKeyPrefix(prefix) }

    /**
     * Deletes a specific key-value pair from the secure storage.
     *
     * @param key The key to delete from the storage.
     * @return [DeleteValueResult] indicating the result of the delete operation.
     */
    fun deleteValue(key: String): DeleteValueResult =
        runBlocking { secureStorage.deleteValue(key) }

    /**
     * Reads a value from the secure storage based on the provided key.
     *
     * @param key The key whose value should be read.
     * @return [ReadValueResult] indicating whether the value was found, not found, or an error occurred.
     */
    fun readValue(key: String): ReadValueResult =
        runBlocking { secureStorage.readValue(key) }

    /**
     * Stores a string value in the secure storage under the specified key.
     *
     * @param key The key under which the value will be stored.
     * @param value The value to store.
     * @return [StoreValueResult] indicating the result of the store operation.
     */
    fun storeValue(key: String, value: String): StoreValueResult =
        runBlocking { secureStorage.storeValue(key, value) }

    /**
     * Stores a byte array value in the secure storage under the specified key.
     *
     * @param key The key under which the value will be stored.
     * @param value The byte array value to store.
     * @return [StoreValueResult] indicating the result of the store operation.
     */
    fun storeValue(key: String, value: ByteArray): StoreValueResult =
        runBlocking { secureStorage.storeValue(key, value) }

    /**
     * Reads a value from secure storage and performs a sanity check.
     *
     * If the key is empty or not found, an error is logged. If the secure storage is corrupted,
     * all stored data is deleted and an exception is thrown.
     *
     * @param key The key of the value to retrieve.
     * @return The stored value as a [String], or `null` if the value is not found or an error occurs.
     * @throws UserRepository.CorruptedUserDataException if data corruption is detected.
     */
    fun getItemWithSanityCheck(key: String): String? {
        return runBlocking {
            var value: String? = null
            when (val result = readValue(key)) {
                is ReadValueResult.Found -> value = result.value
                ReadValueResult.EmptyKey -> BBLogger.error("UserRepositoryImpl", "key is empty")
                ReadValueResult.NotFound -> BBLogger.error("UserRepositoryImpl", "$key not found")
                is ReadValueResult.Error -> {
                    deleteAll()
                    throw UserRepository.CorruptedUserDataException()
                }
            }
            value
        }
    }
}
