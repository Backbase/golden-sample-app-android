package com.backbase.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class HttpEngineFactoryImpl : HttpEngineFactory {

    override fun createOkHttpClient(
        trustManagers: Array<TrustManager>,
        sslSocketFactory: SSLSocketFactory,
        vararg interceptors: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                interceptors.forEach {
                    addInterceptor(it)
                }
            }
            .sslSocketFactory(
                sslSocketFactory,
                trustManagers[0] as X509TrustManager
            ).build()
    }
}
