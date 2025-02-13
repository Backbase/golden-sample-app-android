package com.backbase.app_common.sdk

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.utils.net.NetworkConnectorBuilder
import com.google.gson.internal.LinkedTreeMap

fun Application.initializeBackbase(
    backbaseConfigAssetPath: String = "backbase/config.json",
    isConfigEncrypted: Boolean = false
) {
    Backbase.initialize(applicationContext, backbaseConfigAssetPath, isConfigEncrypted)
    setupHttpHeaders()
}

@Suppress("TooGenericExceptionCaught", "SwallowedException")
private fun setupHttpHeaders() {
    val headers = Backbase.requireInstance().configuration.custom["default-http-headers"]
    val hashMap: HashMap<String, String> = HashMap()
    try {
        val map = headers as LinkedTreeMap<*, *>
        for ((k, v) in map) {
            val stringK = k as String
            val stringV = v as String
            hashMap[stringK] = stringV
        }
    } catch (e: Exception) {
        BBLogger.error("Network", "Failed to cast header values")
    }
    NetworkConnectorBuilder.Configurations.appendHeaders(hashMap)
}
