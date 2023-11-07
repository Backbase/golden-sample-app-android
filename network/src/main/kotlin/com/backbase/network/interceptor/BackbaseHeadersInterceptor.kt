package com.backbase.network.interceptor

import com.backbase.android.Backbase
import com.backbase.network.UserAgentUtils
import okhttp3.Interceptor
import okhttp3.Response

class BackbaseHeadersInterceptor(private val backbase: Backbase) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val tokens = backbase.authClient.authTokens
        val authorization = checkNotNull(tokens?.values?.first())
        val cookie = checkNotNull(backbase.bbCookieStorageManager?.cookieForServerURL)
        val userAgent = UserAgentUtils.generate()
        val acceptLanguage = backbase.acceptedLanguage

        val builder = chain.request().newBuilder()
        builder
            .addHeader("Authorization", authorization)
            .addHeader("Cookie", cookie)
            .addHeader("User-Agent", userAgent)
            .addHeader("Accept-Language", acceptLanguage)
        return chain.proceed(builder.build())
    }
}
