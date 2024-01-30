package com.backbase.cards_journey.ui.details

import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.cards_journey.mvi.UiEffect
import com.backbase.cards_journey.mvi.UiEvent
import com.backbase.cards_journey.mvi.UiState


data class CardDetailsScreenViewState(
    val isLoading: Boolean,
    val card: CardItem? = null
) : UiState()

sealed class CardDetailsScreenEffect : UiEffect() {
    data object HandleErrorResponse : CardDetailsScreenEffect()
}

sealed class CardDetailsScreenEvent : UiEvent() {
    data object GetCardDetails : CardDetailsScreenEvent()
}
