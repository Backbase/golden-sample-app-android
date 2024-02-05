package com.backbase.cards_journey.impl.ui.details

import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.core_ui.mvi.mvi.UiEffect
import com.backbase.core_ui.mvi.mvi.UiEvent
import com.backbase.core_ui.mvi.mvi.UiState


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
