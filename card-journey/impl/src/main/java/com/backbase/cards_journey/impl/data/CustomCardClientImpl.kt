package com.backbase.cards_journey.impl.data

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.android.clients.common.Call
import com.backbase.android.clients.common.MoshiResponseBodyParser
import com.backbase.android.common.utils.dbs.Params
import com.backbase.android.dbs.DBSDataProvider
import com.backbase.api.data.CustomCardClient
import com.backbase.cards_journey.impl.utils.BBDbsClient
import com.backbase.cards_journey.impl.utils.Constants
import com.squareup.moshi.Types
import java.net.URI

class CustomCardClientImpl(
    private val parser: MoshiResponseBodyParser,
    context: Context,
    serverUri: URI,
    provider: DBSDataProvider,
    backbase: Backbase
) : BBDbsClient(context, serverUri, provider, backbase), CustomCardClient {
    override fun getCards(): Call<List<CardItem>> {
        return buildGetRequest(
            serverUri,
            Constants.GET_CARDS_END_POINT,
            Params(),
            provider
        ).let {
            Call(
                it,
                provider, parser,
                Types.newParameterizedType(List::class.java, CardItem::class.java)
            )
        }
    }

    override fun getCardDetails(id: String): Call<CardItem> {
        return buildGetRequest(
            serverUri,
            Constants.GET_CARD_DETAILS_END_POINT + id,
            Params(),
            provider
        ).let {
            Call(
                it,
                provider, parser,
                CardItem::class.java
            )
        }
    }

}
