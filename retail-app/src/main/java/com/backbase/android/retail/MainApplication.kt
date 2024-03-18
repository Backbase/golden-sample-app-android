package com.backbase.android.retail

import android.app.Application
import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.client.contactmanagerclient2.api.ContactsApi
import com.backbase.android.clients.common.MoshiResponseBodyParser
import com.backbase.android.clients.common.ResponseBodyParser
import com.backbase.android.clients.common.base64Adapter
import com.backbase.android.clients.common.bigDecimalAdapter
import com.backbase.android.clients.common.dateAdapter
import com.backbase.android.clients.common.dateTimeAdapter
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.dbs.DBSClient
import com.backbase.android.dbs.DBSDataProvider
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
        setupRemoteClients()
        setupAuthClient()
        setupHttpHeaders()
        startKoinForApplication()
        initAuthenticationJourney()
    }

    private fun setupRemoteClients() {
        val baseUri = URI("$serverUrl/api")
        val client: DBSClient = getContactsApi()
        client.setBaseURI(URI("$baseUri${client.baseURI}"))
        Backbase.requireInstance().registerClient(client)
    }

    private fun getContactsApi(
    ): ContactsApi {

        return ContactsApi(
            context = this,
            moshi = moshi,
            parser = responseBodyParser,
            serverUri = URI("/contact-manager"),
            provider = NetworkDBSDataProvider(this),
            backbase = Backbase.requireInstance()
        )
    }

    val moshi = Moshi.Builder()
        .add(bigDecimalAdapter)
        .add(dateAdapter)
        .add(dateTimeAdapter)
        .add(base64Adapter)
        .build()

    val responseBodyParser: ResponseBodyParser = MoshiResponseBodyParser(moshi)
}
