package com.backbase.card_use_case.domain

import com.backbase.android.client.cardsclient2.model.CardItem

interface CustomCardUseCase {

    suspend fun getCards(): Result<List<CardItem>>
}