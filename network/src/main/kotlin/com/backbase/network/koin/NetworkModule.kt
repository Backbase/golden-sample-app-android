package com.backbase.network.koin

import com.backbase.android.Backbase
import com.backbase.android.clients.common.base64Adapter
import com.backbase.android.clients.common.bigDecimalAdapter
import com.backbase.android.clients.common.dateAdapter
import com.backbase.android.clients.common.dateTimeAdapter
import com.backbase.android.configurations.inner.BBConfigurationManager
import com.backbase.android.core.security.certificates.PublicKeyPinningManager
import com.backbase.android.utils.net.NetworkConnectorBuilder
import com.backbase.network.HttpEngineFactoryImpl
import com.backbase.network.interceptor.BackbaseHeadersInterceptor
import com.squareup.moshi.Moshi
import org.koin.dsl.module

val networkModule = module {
    single {
        Moshi.Builder()
            .add(bigDecimalAdapter)
            .add(dateAdapter)
            .add(dateTimeAdapter)
            .add(base64Adapter)
            .build()
    }

    single {
        val backbase = Backbase.requireInstance()
        val certificates =
            BBConfigurationManager.getConfiguration().security.sslPinning.certificates
        HttpEngineFactoryImpl().createOkHttpClient(
            trustManagers = PublicKeyPinningManager().getTrustedManager(get(), certificates),
            sslSocketFactory = checkNotNull(NetworkConnectorBuilder.Configurations.getSocketFactory()),
            interceptors = arrayOf(BackbaseHeadersInterceptor(backbase))
        )
    }
}
