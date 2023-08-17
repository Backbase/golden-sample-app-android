package com.backbase.golden_sample_app

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.business.journey.workspaces.WorkspacesJourney
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.device.BBDeviceAuthenticator
import com.backbase.android.identity.fido.FidoUafFacetUtils
import com.backbase.android.identity.journey.authentication.initAuthenticationJourney
import com.backbase.android.identity.journey.authentication.stopAuthenticationJourney
import com.backbase.android.listeners.ModelListener
import com.backbase.android.model.Model
import com.backbase.android.model.ModelSource
import com.backbase.android.utils.net.response.Response
import com.backbase.golden_sample_app.authentication.CompositeSessionListener
import com.backbase.golden_sample_app.common.TAG
import com.backbase.golden_sample_app.koin.appModule
import com.backbase.golden_sample_app.koin.commonModule
import com.backbase.golden_sample_app.koin.featureFilterModule
import com.backbase.golden_sample_app.koin.identityAuthModule
import com.backbase.golden_sample_app.koin.securityModule
import com.backbase.golden_sample_app.koin.userModule
import com.backbase.golden_sample_app.koin.workspacesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import java.net.URI

/**
 * Setup the necessary dependencies and configurations.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
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
        setupRemoteClients()
        setupAuthClient()
        setupDependencies()
        initAuthenticationJourney()
    }

    private fun initializeBackbase(
        backbaseConfigAssetPath: String = "backbase/config.json",
        encrypted: Boolean = false
    ) {
        Backbase.initialize(this@MainApplication, backbaseConfigAssetPath, encrypted)
        with(Backbase.requireInstance()) {
            // We need to keep a local model in our code so that Authenticators can be injected there at runtime.
            getModel(
                object : ModelListener<Model> {
                    override fun onModelReady(model: Model) = BBLogger.debug(TAG, "Model loaded")

                    override fun onError(response: Response) = throw IllegalArgumentException(
                        "backbaseConfigAssetPath must point to a valid model. Instead, ${response.errorMessage}"
                    )
                },
                ModelSource.LOCAL
            )
        }
    }

    private fun setupRemoteClients() {
        val baseUri = URI(Sdk.serverUrl + "/api")
        Sdk.clients(this@MainApplication).forEach { client ->
            client.setBaseURI(URI("$baseUri${client.baseURI}"))
            Backbase.getInstance()?.registerClient(client)
        }
    }

    private fun setupAuthClient() {
        val backbase = checkNotNull(Backbase.getInstance())
        backbase.registerAuthClient(authClient)
        backbase.authClient.startSessionObserver(sessionEmitter)
    }

    private fun setupDependencies() = startKoin {
        androidContext(this@MainApplication)

        loadKoinModules(
            listOf(
                securityModule(this@MainApplication),
                commonModule,
                userModule,
                featureFilterModule,
                appModule,
                identityAuthModule(sessionEmitter),
                workspacesModule,
                WorkspacesJourney.create()
            )
        )
    }

    override fun onTerminate() {
        stopAuthenticationJourney()
        super.onTerminate()
    }
}
