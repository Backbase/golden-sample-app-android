package com.backbase.golden_sample_app.koin

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.client.gen2.accesscontrolclient3.api.UserContextApi
import com.backbase.android.client.gen2.accesscontrolclient3.api.UsersApi
import com.backbase.android.client.usermanagerclient2.api.UserProfileManagementApi
import com.backbase.android.clients.common.MoshiResponseBodyParser
import com.backbase.android.clients.common.ResponseBodyParser
import com.backbase.android.clients.common.base64Adapter
import com.backbase.android.clients.common.bigDecimalAdapter
import com.backbase.android.clients.common.dateAdapter
import com.backbase.android.clients.common.dateTimeAdapter
import com.squareup.moshi.Moshi
import org.koin.dsl.module
import java.net.URI

internal fun servicesModule(context: Context) = module {
    val userContextApi = UserContextApi(
        context = context,
        moshi = moshi,
        parser = responseBodyParser,
        serverUri = URI("$apiRoot/$ACCESS_CONTROL_ENDPOINT"),
    )

    val usersApi = UsersApi(
        context = context,
        moshi = moshi,
        parser = responseBodyParser,
        serverUri = URI("$apiRoot/$ACCESS_CONTROL_ENDPOINT"),
    )

    val userProfileManagementApi = UserProfileManagementApi(
        context = context,
        moshi = moshi,
        parser = responseBodyParser,
        serverUri = URI("$apiRoot/${USER_MANAGER_ENDPOINT}"),
    )

    factory { userContextApi }
    factory { usersApi }
    factory { userProfileManagementApi }

    Backbase.requireInstance().apply {
        registerClient(userContextApi)
        registerClient(usersApi)
        registerClient(userProfileManagementApi)
    }
}

private val apiRoot = Backbase.requireInstance().configuration.experienceConfiguration?.apiRoot

private val moshi = Moshi.Builder()
    .add(bigDecimalAdapter)
    .add(dateAdapter)
    .add(dateTimeAdapter)
    .add(base64Adapter)
    .build()

private val responseBodyParser: ResponseBodyParser = MoshiResponseBodyParser(moshi)

private const val ACCESS_CONTROL_ENDPOINT = "access-control"
private const val USER_MANAGER_ENDPOINT = "user-manager"
