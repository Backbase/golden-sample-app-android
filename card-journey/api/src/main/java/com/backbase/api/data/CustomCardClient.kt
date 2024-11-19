package com.backbase.api.data

import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.android.clients.common.Call

interface CustomCardClient {
    fun getCards(): Call<List<CardItem>>
    fun getCardDetails(id: String): Call<CardItem>
}
