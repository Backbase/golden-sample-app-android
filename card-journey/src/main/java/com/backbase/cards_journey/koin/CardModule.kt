package com.backbase.cards_journey.koin

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.clients.common.MoshiResponseBodyParser
import com.backbase.android.clients.common.base64Adapter
import com.backbase.android.clients.common.bigDecimalAdapter
import com.backbase.android.clients.common.dateAdapter
import com.backbase.android.clients.common.dateTimeAdapter
import com.backbase.android.dbs.dataproviders.NetworkDBSDataProvider
import com.backbase.card_use_case.data.CustomCardClient
import com.backbase.card_use_case.domain.CustomCardUseCase
import com.backbase.cards_journey.data.CustomCardClientImpl
import com.backbase.cards_journey.domain.CustomCardUseCaseImpl
import com.squareup.moshi.Moshi
import org.koin.dsl.module
import java.net.URI

val serverUrl: String?
    get() = Backbase.getInstance()?.configuration?.experienceConfiguration?.serverURL
private const val CARD_MANAGER_ENDPOINT = "/api/cards-presentation-service"
private val moshi = Moshi.Builder()
    .add(bigDecimalAdapter)
    .add(dateAdapter)
    .add(dateTimeAdapter)
    .add(base64Adapter)
    .build()

fun cardModule(context: Context) = module {

    val backbase = Backbase.requireInstance()
    single { backbase.getClient(CustomCardClientImpl::class.java) }
    factory<CustomCardUseCase> {
        CustomCardUseCaseImpl(
            customCardClient = get()
        )
    }
    factory<CustomCardClient> {
        CustomCardClientImpl(
            context = context,
            parser = MoshiResponseBodyParser(moshi),
            serverUri = URI(serverUrl + CARD_MANAGER_ENDPOINT),
            provider = NetworkDBSDataProvider(context),
            backbase = Backbase.requireInstance()
        )
    }

}