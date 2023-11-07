package com.backbase.network

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

class HttpClientFactoryImpl : HttpClientFactory {

    override fun createRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converter: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converter)
            .baseUrl(baseUrl)
            .build()
    }
}
