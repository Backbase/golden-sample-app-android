package com.backbase.golden_sample_app

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.client.contactmanagerclient2.api.ContactsApi
import com.backbase.android.client.gen2.accesscontrolclient3.api.UserContextApi
import com.backbase.android.client.gen2.accesscontrolclient3.api.UsersApi
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApi
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
import com.backbase.android.clients.common.MoshiResponseBodyParser
import com.backbase.android.clients.common.base64Adapter
import com.backbase.android.clients.common.bigDecimalAdapter
import com.backbase.android.clients.common.dateAdapter
import com.backbase.android.clients.common.dateTimeAdapter
import com.backbase.android.dbs.DBSClient
import com.backbase.android.dbs.DBSDataProvider
import com.backbase.android.dbs.dataproviders.NetworkDBSDataProvider
import com.squareup.moshi.Moshi
import java.net.URI

/**
 * Setup DBClient, server url and API's of the journeys.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
object Sdk {

    val serverUrl: String?
        get() = Backbase.getInstance()?.configuration?.experienceConfiguration?.serverURL

    val clients: (Context) -> List<DBSClient> = { context ->
        val networkDBSDataProvider = NetworkDBSDataProvider(context)
        listOf(
            getUserContextApi(context, networkDBSDataProvider),
            getUsersApi(context, networkDBSDataProvider),
            getProductSummaryApi(context, networkDBSDataProvider),
            getArrangementsApi(context, networkDBSDataProvider),
            getContactsApi(context, networkDBSDataProvider)
        )
    }

    private val moshi = Moshi.Builder()
        .add(bigDecimalAdapter)
        .add(dateAdapter)
        .add(dateTimeAdapter)
        .add(base64Adapter)
        .build()

    private fun getUserContextApi(
        context: Context,
        dataProvider: DBSDataProvider
    ) = UserContextApi(
        context = context,
        moshi = moshi,
        parser = MoshiResponseBodyParser(moshi),
        serverUri = URI(ACCESS_CONTROL_ENDPOINT),
        provider = dataProvider,
        backbase = Backbase.requireInstance()
    )

    private fun getUsersApi(
        context: Context,
        dataProvider: DBSDataProvider
    ) = UsersApi(
        context = context,
        moshi = moshi,
        parser = MoshiResponseBodyParser(moshi),
        serverUri = URI(ACCESS_CONTROL_ENDPOINT),
        provider = dataProvider,
        backbase = Backbase.requireInstance()
    )

    private fun getProductSummaryApi(
        context: Context,
        dataProvider: DBSDataProvider
    ) = ProductSummaryApi(
        context = context,
        moshi = moshi,
        parser = MoshiResponseBodyParser(moshi),
        serverUri = URI(ARRANGEMENT_MANAGER_ENDPOINT),
        provider = dataProvider,
        backbase = Backbase.requireInstance()
    )

    private fun getArrangementsApi(
        context: Context,
        dataProvider: DBSDataProvider
    ) = ArrangementsApi(
        context = context,
        moshi = moshi,
        parser = MoshiResponseBodyParser(moshi),
        serverUri = URI(ARRANGEMENT_MANAGER_ENDPOINT),
        provider = dataProvider,
        backbase = Backbase.requireInstance()
    )

    private fun getContactsApi(
        context: Context,
        dataProvider: DBSDataProvider
    ) = ContactsApi(
        context = context,
        moshi = moshi,
        parser = MoshiResponseBodyParser(moshi),
        serverUri = URI(CONTACT_MANAGER_ENDPOINT),
        provider = dataProvider,
        backbase = Backbase.requireInstance()
    )

    private const val ACCESS_CONTROL_ENDPOINT = "/access-control"
    private const val ARRANGEMENT_MANAGER_ENDPOINT = "/arrangement-manager"
    private const val CONTACT_MANAGER_ENDPOINT = "//contact-manager"
}
