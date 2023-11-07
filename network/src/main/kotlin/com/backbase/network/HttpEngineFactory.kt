package com.backbase.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager

interface HttpEngineFactory {

    fun createOkHttpClient(
        trustManagers: Array<TrustManager>,
        sslSocketFactory: SSLSocketFactory,
        vararg interceptors: Interceptor
    ): OkHttpClient
}
