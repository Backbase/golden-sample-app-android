package com.backbase.cards_journey.impl.ui.list

import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.cards_journey.impl.mvi.UiEvent


data class CardListScreenViewState(
    val isLoading: Boolean,
    val cards: List<CardItem>? = null
) : com.backbase.cards_journey.impl.mvi.UiState()

sealed class CardListScreenEffect : com.backbase.cards_journey.impl.mvi.UiEffect() {
    data object HandleErrorResponse : CardListScreenEffect()
}

sealed class CardListScreenEvent : UiEvent() {
    data object GetCardList : CardListScreenEvent()
}
