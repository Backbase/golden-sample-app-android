package com.backbase.cards_journey.ui.list

import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.cards_journey.mvi.UiEffect
import com.backbase.cards_journey.mvi.UiEvent
import com.backbase.cards_journey.mvi.UiState


data class CardListScreenViewState(
    val isLoading: Boolean,
    val cards: List<CardItem>? = null
) : UiState()

sealed class CardListScreenEffect : UiEffect() {
    data object HandleErrorResponse : CardListScreenEffect()
}

sealed class CardListScreenEvent : UiEvent() {
    data object GetCardList : CardListScreenEvent()
}
