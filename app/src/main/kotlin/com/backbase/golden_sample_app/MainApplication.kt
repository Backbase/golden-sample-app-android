package com.backbase.golden_sample_app

import android.app.Application
import com.backbase.accounts_journey.AccountsJourney
import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.configuration.accountlist.AccountListScreenConfiguration
import com.backbase.analytics.observabilityModule
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
import com.backbase.android.observability.Tracker
import com.backbase.android.observability.event.ScreenViewEvent
import com.backbase.android.observability.event.UserActionEvent
import com.backbase.android.utils.net.NetworkConnectorBuilder
import com.backbase.android.utils.net.response.Response
import com.backbase.golden_sample_app.authentication.CompositeSessionListener
import com.backbase.golden_sample_app.common.TAG
import com.backbase.golden_sample_app.koin.accountsModule
import com.backbase.golden_sample_app.koin.appModule
import com.backbase.golden_sample_app.koin.contactsModule
import com.backbase.golden_sample_app.koin.featureFilterModule
import com.backbase.golden_sample_app.koin.identityAuthModule
import com.backbase.golden_sample_app.koin.securityModule
import com.backbase.golden_sample_app.koin.userModule
import com.backbase.golden_sample_app.koin.workspacesModule
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.android.inject
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

    private val tracker: Tracker by inject()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

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
        setupHttpHeaders()
        setupDependencies()
        initAuthenticationJourney()
        setupAnalytics()
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
            Backbase.requireInstance().registerClient(client)
        }
    }

    private fun setupAuthClient() {
        val backbase = checkNotNull(Backbase.getInstance())
        backbase.registerAuthClient(authClient)
        backbase.authClient.startSessionObserver(sessionEmitter)
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
            BBLogger.error(TAG, "Failed to cast header values")
        }
        NetworkConnectorBuilder.Configurations.appendHeaders(hashMap)
    }

    private fun setupAccountsJourneyConfiguration(): AccountsJourneyConfiguration {
        return AccountsJourneyConfiguration {
            this.accountListScreenConfiguration = AccountListScreenConfiguration {
                this.screenTitle = R.string.accounts_screen_title
            }
        }
    }

    private fun setupDependencies() = startKoin {
        androidContext(this@MainApplication)
        loadKoinModules(
            listOf(
                securityModule(this@MainApplication),
                userModule,
                featureFilterModule,
                appModule(this@MainApplication),
                identityAuthModule(sessionEmitter),
                contactsModule(),
                workspacesModule,
                WorkspacesJourney.create(),
                accountsModule,
                AccountsJourney.create(configuration = setupAccountsJourneyConfiguration()),
                observabilityModule,
            )
        )
    }

    private fun setupAnalytics() {
        tracker.subscribe(this, ScreenViewEvent::class, scope) { event ->
            // In this code block we can start receiving events from the analytics framework.
            // After that, you can start forwarding it to the Analytics tools of your choice.
            // Here we receive the Screen View Events.

            // Samples (Actual method call could be different, please refer to the actual Analytics tools documentation)
            // Firebase.logEvent(${event.name})
            // Adjust.screen(${event.name})
            // Log.d("Tracker", "Tracked screen view: ${event.name}")
        }
        tracker.subscribe(this, UserActionEvent::class, scope) { event ->
            // Same can be done with the User Action Events.
        }
    }

    override fun onTerminate() {
        stopAuthenticationJourney()
        super.onTerminate()
    }
}
