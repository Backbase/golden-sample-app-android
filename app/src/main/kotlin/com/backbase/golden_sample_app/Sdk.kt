package com.backbase.golden_sample_app

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.client.accesscontrolclient2.api.UserContextApi
import com.backbase.android.client.accesscontrolclient2.api.UsersApi
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
        listOf(
            getUserContextApi(
                context,
                NetworkDBSDataProvider(context),
                ACCESS_CONTROL_ENDPOINT
            ),
            getUsersApi(
                context,
                NetworkDBSDataProvider(context),
                ACCESS_CONTROL_ENDPOINT
            )
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
        dataProvider: DBSDataProvider,
        serverUrl: String
    ) = UserContextApi(
        context = context,
        moshi = moshi,
        parser = MoshiResponseBodyParser(moshi),
        serverUri = URI(serverUrl),
        provider = dataProvider,
        backbase = Backbase.requireInstance()
    )

    private fun getUsersApi(
        context: Context,
        dataProvider: DBSDataProvider,
        serverUrl: String
    ) = UsersApi(
        context = context,
        moshi = moshi,
        parser = MoshiResponseBodyParser(moshi),
        serverUri = URI(serverUrl),
        provider = dataProvider,
        backbase = Backbase.requireInstance()
    )

    private const val ACCESS_CONTROL_ENDPOINT = "/access-control"
}
