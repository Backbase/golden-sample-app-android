package com.backbase.cards_journey.impl.utils

import com.backbase.android.client.cardsclient2.model.CardItem

object Constants {
    const val GET_CARDS_END_POINT = "/client-api/v2/cards"
    const val GET_CARD_DETAILS_END_POINT = "/client-api/v2/cards/"
}

fun getMaskedText(item: CardItem?): String? {
    return item?.let {
        "**** **** **** ${it.maskedNumber}"
    }
}
