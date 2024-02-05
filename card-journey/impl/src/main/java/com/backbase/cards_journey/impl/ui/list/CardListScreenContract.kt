package com.backbase.cards_journey.impl.ui.list

import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.core_ui.mvi.mvi.UiEffect
import com.backbase.core_ui.mvi.mvi.UiEvent
import com.backbase.core_ui.mvi.mvi.UiState


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
