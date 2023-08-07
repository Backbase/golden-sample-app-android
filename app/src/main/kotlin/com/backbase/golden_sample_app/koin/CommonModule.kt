package com.backbase.golden_sample_app.koin

import com.backbase.android.Backbase
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.android.plugins.storage.persistent.EncryptedStorage
import org.koin.dsl.module

val commonModule = module {
    factory<StorageComponent> { get<Backbase>().getRegisteredPlugin(EncryptedStorage::class.java)!!.storageComponent }
}
