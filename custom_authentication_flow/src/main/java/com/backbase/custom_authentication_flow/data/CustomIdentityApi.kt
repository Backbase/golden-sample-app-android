package com.backbase.custom_authentication_flow.data

import android.net.Uri
import androidx.core.net.toUri
import com.backbase.android.Backbase
import com.backbase.android.clients.common.Call
import com.backbase.android.clients.common.ResponseBodyParser
import com.backbase.android.clients.common.buildRequest
import com.backbase.android.dbs.DBSClient
import com.backbase.android.dbs.DBSDataProvider
import com.backbase.android.utils.net.request.RequestMethods
import com.backbase.custom_authentication_flow.core.util.FLOW_ID_HEADER
import com.google.gson.Gson
import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

internal class CustomIdentityApi(
    // if you have moshi generated adapters you can use Moshi instead of Gson
    val gson: Gson,
    val parser: ResponseBodyParser,
    val backbase: Backbase,
    var serverUri: URI,
    var provider: DBSDataProvider,
) : DBSClient {

    override fun setBaseURI(baseUri: URI) {
        serverUri = baseUri
    }

    override fun getBaseURI() = this.serverUri

    override fun setDataProvider(provider: DBSDataProvider?) {
        this.provider = requireNotNull(provider) { "The provider must not be null!" }
    }

    override fun getDataProvider(): DBSDataProvider = provider

    inline fun <reified T> postStateful(
        actionUrl: String,
        flowId: String,
        requestBody: T
    ): Call<Any> {
        val bodyType = T::class.java
        val serializedBody: String = gson.toJson(requestBody, bodyType)
        val uri = actionUrl.toUri()

        return buildRequest(
            RequestMethods.POST,
            serverUri,
            uri.path.orEmpty(),
            uri.getQueryParamMap(),
            backbase
        ).apply {
            headers = headers?.appendHeaders(flowId)
            body = serializedBody
        }.let {
            Call(it, provider, parser, Any::class.java)
        }
    }

    fun postStateless(
        originalRequestBody: String,
        additionalRequestBody: Map<String, String>,
        flowId: String,
    ): Call<Any> {
        return buildRequest(
            RequestMethods.POST,
            serverUri,
            IDENTITY_STATELESS_PATH,
            emptyMap(),
            backbase
        ).apply {
            headers = headers?.appendHeaders(flowId, FORM_ENCODED_CONTENT)
            body = originalRequestBody.appendParams(additionalRequestBody)
        }.let {
            Call(it, provider, parser, Any::class.java)
        }
    }

    private fun String.appendParams(additionalRequestBody: Map<String, String>): String {
        return StringBuilder().apply {
            append(this@appendParams)
            additionalRequestBody.forEach { (key, value) ->
                append("&")
                    .append(key)
                    .append("=")
                    .append(URLEncoder.encode(value, StandardCharsets.UTF_8.name()))
            }
        }.toString()
    }

    private fun Uri.getQueryParamMap(): Map<String, List<String>> {
        return mutableMapOf<String, List<String>>().apply {
            queryParameterNames.forEach { name ->
                getQueryParameter(name)?.let { value ->
                    put(name, listOf(value))
                }
            }
        }
    }

    private fun Map<String, String>.appendHeaders(flowId: String, contentType: String = JSON_CONTENT): Map<String, String> {
        return toMutableMap().apply {
            put(FLOW_ID_HEADER, flowId)
            put(CONTENT_TYPE, contentType)
        }
    }

    companion object {
        private const val IDENTITY_STATELESS_PATH =
            "/auth/realms/retail/protocol/openid-connect/token"
        private const val CONTENT_TYPE = "Content-Type"
        private const val JSON_CONTENT = "application/json"
        private const val FORM_ENCODED_CONTENT = "application/x-www-form-urlencoded"
    }
}