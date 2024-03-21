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
import com.backbase.android.dbs.DBSClient
import com.backbase.android.dbs.dataproviders.NetworkDBSDataProvider
import com.squareup.moshi.Moshi
import java.net.URI

fun Application.setupContacts() {
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

fun Application.setupUserContextApi() {
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

fun Application.setupUserApi() {
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
