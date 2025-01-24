package com.backbase.network

import com.squareup.moshi.Moshi
import io.ktor.client.HttpClient
import okhttp3.OkHttpClient

interface HttpClientFactory {

    fun createClient(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): HttpClient
}
