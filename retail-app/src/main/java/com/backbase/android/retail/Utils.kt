package com.backbase.android.retail

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.device.BBDeviceAuthenticator
import com.backbase.android.listeners.ModelListener
import com.backbase.android.listeners.NavigationEventListener
import com.backbase.android.listeners.SessionListener
import com.backbase.android.model.Model
import com.backbase.android.model.ModelSource
import com.backbase.android.retail.authorization.CompositeSessionListener
import com.backbase.android.retail.journey.NavigationEventEmitter
import com.backbase.android.retail.journey.SessionEmitter
import com.backbase.android.utils.net.NetworkConnectorBuilder
import com.backbase.android.utils.net.response.Response
import com.google.gson.internal.LinkedTreeMap

val BackbaseClient
    get() = Backbase.requireInstance()

val serverUrl: String
    get() = checkNotNull(BackbaseClient.configuration.experienceConfiguration?.serverURL)

private val CompositeSessionListener: CompositeSessionListener = CompositeSessionListener()

val AppSessionEmitter: SessionEmitter get() = CompositeSessionListener
 
val AppSessionListener: SessionListener get() = CompositeSessionListener


fun setupHttpHeaders() {
    val headers = BackbaseClient.configuration.custom["default-http-headers"]
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

fun Application.initializeBackbase(
    backbaseConfigAssetPath: String = "backbase/config.json",
    encrypted: Boolean = false
) {
    Backbase.initialize(this, backbaseConfigAssetPath, encrypted)
    BackbaseClient.getModel(
        object : ModelListener<Model> {
            override fun onModelReady(model: Model) = BBLogger.debug("model", "Model loaded")

            override fun onError(response: Response) = throw IllegalArgumentException(
                "backbaseConfigAssetPath must point to a valid model. Instead, ${response.errorMessage}"
            )
        },
        ModelSource.LOCAL
    )
}


fun Application.setupAuthClient() {
    val authClient = BBIdentityAuthClient(this, "").apply {
        addAuthenticator(BBDeviceAuthenticator())
    }

    BackbaseClient.registerAuthClient(authClient)
    BackbaseClient.authClient.startSessionObserver(AppSessionListener)
}

class DefaultNavigationEventEmitter(
    private val backbase: Backbase
) : NavigationEventEmitter {
    override fun registerNavigationEventListener(listener: NavigationEventListener) =
        backbase.registerNavigationEventListener(listener)

    override fun unregisterNavigationEventListener(listener: NavigationEventListener) =
        backbase.unregisterNavigationEventListener(listener)
}
