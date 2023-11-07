package com.backbase.network

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

interface HttpClientFactory {

    fun createRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converter: Converter.Factory
    ): Retrofit
}
