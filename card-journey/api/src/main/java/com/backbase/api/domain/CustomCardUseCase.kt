package com.backbase.api.domain

import com.backbase.android.client.cardsclient2.model.CardItem

interface CustomCardUseCase {

    suspend fun getCards(): Result<List<CardItem>>
    suspend fun getCardDetails(id: String): Result<CardItem>
}