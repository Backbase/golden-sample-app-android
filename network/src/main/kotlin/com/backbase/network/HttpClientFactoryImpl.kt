package com.backbase.network

import com.backbase.network.converter.moshi
import com.squareup.moshi.Moshi
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.bodyAsText
import okhttp3.OkHttpClient

class HttpClientFactoryImpl : HttpClientFactory {

    override fun createClient(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): HttpClient {
        val client = HttpClient(OkHttp) {
            defaultRequest {
                url(baseUrl)
            }

            install(UserAgent) {
                agent = UserAgentUtils.generate()
            }

            // Json parser
            install(ContentNegotiation) {
                moshi(moshi)
            }

            // HTTP logging
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }

            // Timeout
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }

            // Engine
            engine {
                preconfigured = okHttpClient
            }

            // Response validator
            HttpResponseValidator {
                validateResponse { response ->
                    val statusCode = response.status.value
                    when (statusCode) {
                        in 300..399 -> {
                            println("HttpResponseValidator 300..399")
                            println(response.bodyAsText())
                        }
                        in 400..499 -> {
                            println("HttpResponseValidator 400..499")
                            println(response.bodyAsText())
                        }
                        in 500..599 -> {
                            println("HttpResponseValidator 500..599")
                            println(response.bodyAsText())
                        }
                    }
                }
                handleResponseException { cause, request ->
                    println(request.url)
                    println(cause.message)
                }
            }
        }

        return client
    }
}
