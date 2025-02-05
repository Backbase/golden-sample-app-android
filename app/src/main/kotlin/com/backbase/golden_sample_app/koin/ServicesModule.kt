package com.backbase.golden_sample_app.koin

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApi
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
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
//    val userContextApi = UserContextApi(
//        context = context,
//        moshi = moshi,
//        parser = responseBodyParser,
//        serverUri = URI("$apiRoot/$ACCESS_CONTROL_ENDPOINT"),
//    )
//
//    val usersApi = UsersApi(
//        context = context,
//        moshi = moshi,
//        parser = responseBodyParser,
//        serverUri = URI("$apiRoot/$ACCESS_CONTROL_ENDPOINT"),
//    )

    val userProfileManagementApi = UserProfileManagementApi(
        context = context,
        moshi = moshi,
        parser = responseBodyParser,
        serverUri = URI("$apiRoot/${USER_MANAGER_ENDPOINT}"),
    )

    val productSummaryApi = ProductSummaryApi(
        context = context,
        moshi = moshi,
        parser = responseBodyParser,
        serverUri = URI("$apiRoot/$ARRANGEMENT_MANAGER_ENDPOINT"),
    )

    val arrangementsApi = ArrangementsApi(
        context = context,
        moshi = moshi,
        parser = responseBodyParser,
        serverUri = URI("$apiRoot/$ARRANGEMENT_MANAGER_ENDPOINT"),
    )

//    factory { userContextApi }
//    factory { usersApi }
    factory { userProfileManagementApi }
    factory { productSummaryApi }
    factory { arrangementsApi }

    Backbase.requireInstance().apply {
//        registerClient(userContextApi)
//        registerClient(usersApi)
        registerClient(userProfileManagementApi)
        registerClient(productSummaryApi)
        registerClient(arrangementsApi)
    }
}

private val apiRoot = Backbase.requireInstance().configuration.experienceConfiguration?.apiRoot

@Deprecated("use koin")
private val moshi = Moshi.Builder()
    .add(bigDecimalAdapter)
    .add(dateAdapter)
    .add(dateTimeAdapter)
    .add(base64Adapter)
    .build()

@Deprecated("use koin")
private val responseBodyParser: ResponseBodyParser = MoshiResponseBodyParser(moshi)

//private const val ACCESS_CONTROL_ENDPOINT = "access-control"
private const val ARRANGEMENT_MANAGER_ENDPOINT = "arrangement-manager"
private const val USER_MANAGER_ENDPOINT = "user-manager"
