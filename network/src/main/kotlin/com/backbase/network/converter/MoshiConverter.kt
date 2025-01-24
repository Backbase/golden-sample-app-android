package com.backbase.network.converter

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import io.ktor.http.ContentType
import io.ktor.http.content.OutgoingContent
import io.ktor.http.content.TextContent
import io.ktor.http.withCharsetIfNeeded
import io.ktor.serialization.Configuration
import io.ktor.serialization.ContentConverter
import io.ktor.serialization.JsonConvertException
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.reifiedType
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.charsets.Charset
import io.ktor.utils.io.jvm.javaio.toInputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.buffer
import okio.source

class MoshiConverter(private val moshi: Moshi = Moshi.Builder().build()) : ContentConverter {
    override suspend fun deserialize(
        charset: Charset,
        typeInfo: TypeInfo,
        content: ByteReadChannel
    ): Any? {
        try {
            return withContext(Dispatchers.IO) {
                val source = content.toInputStream().source().buffer()
                moshi.adapter<Any?>(typeInfo.reifiedType).fromJson(source)
            }
        } catch (e: JsonDataException) {
            throw JsonConvertException("Unable to convert JSON", e)
        } catch (e: JsonEncodingException) {
            throw JsonConvertException("Invalid JSON", e)
        }
    }

    override suspend fun serialize(
        contentType: ContentType,
        charset: Charset,
        typeInfo: TypeInfo,
        value: Any?
    ): OutgoingContent {
        return TextContent(
            moshi.adapter<Any?>(typeInfo.reifiedType).toJson(value),
            contentType.withCharsetIfNeeded(charset)
        )
    }
}

/**
 * Registers the supplied Moshi instance as a content converter for `application/json`
 * data.
 */
fun Configuration.moshi(moshi: Moshi = Moshi.Builder().build()) {
    register(ContentType.Application.Json, MoshiConverter(moshi))
}

/**
 * Creates a new Moshi instance and registers it as a content converter for
 * `application/json` data.  The supplied block is used to configure the builder.
 */
fun Configuration.moshi(block: Moshi.Builder.() -> Unit) {
    moshi(Moshi.Builder().apply(block).build())
}
