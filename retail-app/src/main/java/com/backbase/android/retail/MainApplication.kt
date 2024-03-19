package com.backbase.android.retail

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.client.contactmanagerclient2.api.ContactsApi
import com.backbase.android.client.gen2.accesscontrolclient3.api.UserContextApi
import com.backbase.android.client.gen2.accesscontrolclient3.api.UsersApi
import com.backbase.android.clients.common.MoshiResponseBodyParser
import com.backbase.android.clients.common.ResponseBodyParser
import com.backbase.android.clients.common.base64Adapter
import com.backbase.android.clients.common.bigDecimalAdapter
import com.backbase.android.clients.common.dateAdapter
import com.backbase.android.clients.common.dateTimeAdapter
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.dbs.DBSClient
import com.backbase.android.dbs.dataproviders.NetworkDBSDataProvider
import com.backbase.android.identity.fido.FidoUafFacetUtils
import com.backbase.android.identity.journey.authentication.initAuthenticationJourney
import com.squareup.moshi.Moshi
import java.net.URI

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            BBLogger.setLogLevel(BBLogger.LogLevel.DEBUG)
            BBLogger.debug("network", "Facet ID: <${FidoUafFacetUtils.getFacetID(this)}>")
        }
        initializeBackbase()
        setupContacts()
        setupUserContextApi()
        setupAuthClient()
        setupHttpHeaders()
        startKoinForApplication()
        initAuthenticationJourney()
        setupUserApi()
    }

    private fun setupContacts() {
        val baseUri = URI("$serverUrl/api")
        val client: DBSClient = ContactsApi(
            context = this,
            moshi = moshi,
            parser = responseBodyParser,
            serverUri = URI("/contact-manager"),
            provider = NetworkDBSDataProvider(this),
            backbase = Backbase.requireInstance()
        )
        client.setBaseURI(URI("$baseUri${client.baseURI}"))
        Backbase.requireInstance().registerClient(client)
    }

    private fun setupUserContextApi() {
        val client = UserContextApi(
            context = this,
            moshi = moshi,
            parser = responseBodyParser,
            serverUri = URI("/access-control"),
            provider = NetworkDBSDataProvider(this),
            backbase = Backbase.requireInstance()
        )
        val baseUri = URI("$serverUrl/api")
        client.setBaseURI(URI("$baseUri${client.baseURI}"))
        Backbase.requireInstance().registerClient(client)
    }

    private fun setupUserApi() {
        val client = UsersApi(
            context = this,
            moshi = moshi,
            parser = responseBodyParser,
            serverUri = URI("/access-control"),
            provider = NetworkDBSDataProvider(this),
            backbase = Backbase.requireInstance()
        )
        val baseUri = URI("$serverUrl/api")
        client.setBaseURI(URI("$baseUri${client.baseURI}"))
        Backbase.requireInstance().registerClient(client)
    }

    private val moshi: Moshi = Moshi.Builder()
        .add(bigDecimalAdapter)
        .add(dateAdapter)
        .add(dateTimeAdapter)
        .add(base64Adapter)
        .build()

    private val responseBodyParser: ResponseBodyParser = MoshiResponseBodyParser(moshi)
}
