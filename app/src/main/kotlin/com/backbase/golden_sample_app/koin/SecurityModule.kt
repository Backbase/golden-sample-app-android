package com.backbase.golden_sample_app.koin

import android.content.Context
import com.backbase.android.secure.storage.SecureStorage
import com.backbase.android.secure.storage.SecureStorageFactory
import com.backbase.android.secure.storage.SecureStorageInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.dsl.module

internal fun securityModule(
    context: Context
) = module {
    runBlocking(Dispatchers.IO) {
        val result: SecureStorageInfo = SecureStorageFactory.createWithMigration(context)
        val secureStorage: SecureStorage = result.storage
        single { secureStorage }
    }
}
