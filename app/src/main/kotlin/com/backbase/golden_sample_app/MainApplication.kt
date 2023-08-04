package com.backbase.golden_sample_app

import android.app.Application
import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.core.networking.error.BBChainErrorResponseResolver
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.device.BBDeviceAuthenticator
import com.backbase.android.identity.fido.FidoUafFacetUtils
import com.backbase.android.identity.journey.authentication.AuthenticationConfiguration
import com.backbase.android.identity.journey.authentication.initAuthenticationJourney
import com.backbase.android.identity.journey.authentication.stopAuthenticationJourney
import com.backbase.android.listeners.ModelListener
import com.backbase.android.model.Model
import com.backbase.android.model.ModelSource
import com.backbase.android.utils.net.response.Response
import com.backbase.golden_sample_app.authentication.CompositeSessionListener
import com.backbase.golden_sample_app.authentication.createDefaultAuthErrorResponseResolvers
import com.backbase.golden_sample_app.authentication.pushAuthSessionListener
import com.backbase.golden_sample_app.common.TAG
import com.backbase.golden_sample_app.koin.appModule
import com.backbase.golden_sample_app.koin.identityAuthModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import java.net.HttpURLConnection

class MainApplication : Application() {

    private val sessionEmitter = CompositeSessionListener()
    private val authClient: BBIdentityAuthClient by lazy {
        BBIdentityAuthClient(this, "").apply {
            addAuthenticator(BBDeviceAuthenticator())
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            BBLogger.setLogLevel(BBLogger.LogLevel.DEBUG)
            BBLogger.debug(TAG, "Facet ID: <${FidoUafFacetUtils.getFacetID(this)}>")
        }
        initializeBackbase()
        setupAuthClient()
        setupDependencies()
        initAuthenticationJourney()
    }
    fun Application.initializeBackbase(
        backbaseConfigAssetPath: String = "backbase/config.json",
        encrypted: Boolean = false
    ) {
        Backbase.initialize(this@MainApplication, backbaseConfigAssetPath, encrypted)
        with(Backbase.requireInstance()) {
            // We need to keep a local model in our code so that Authenticators can be injected there at runtime.
            getModel(object : ModelListener<Model> {
                override fun onModelReady(model: Model) = BBLogger.debug(TAG, "Model loaded")

                override fun onError(response: Response) = throw IllegalArgumentException(
                    "backbaseConfigAssetPath must point to a valid model. Instead, ${response.errorMessage}"
                )
            }, ModelSource.LOCAL)
        }
    }
    override fun onTerminate() {
        stopAuthenticationJourney()
        super.onTerminate()
    }
    private fun setupAuthClient() {
        val backbase = checkNotNull(Backbase.getInstance())
        backbase.registerAuthClient(authClient)
        backbase.authClient.startSessionObserver(sessionEmitter)

        val authErrorResolvers = createDefaultAuthErrorResponseResolvers(authClient)
        if (authErrorResolvers.isEmpty()) {
            BBLogger.warning("session", "No session expiration handling available for <${authClient.javaClass.name}>")
        } else {
            backbase.registerErrorResponseResolver(
                HttpURLConnection.HTTP_UNAUTHORIZED,
                BBChainErrorResponseResolver(*authErrorResolvers.toTypedArray())
            )
        }
        sessionEmitter.registerSessionListener(pushAuthSessionListener(authClient))
    }

    private fun setupDependencies() = startKoin {
        androidContext(this@MainApplication)
        loadKoinModules(
            listOf(
                appModule(this@MainApplication),
                identityAuthModule(AuthenticationConfiguration { }, sessionEmitter)
            )
        )
    }
}
