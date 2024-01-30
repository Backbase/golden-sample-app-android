package com.backbase.cards_journey.ui.list

import androidx.lifecycle.viewModelScope
import com.backbase.android.core.utils.BBLogger
import com.backbase.card_use_case.domain.CustomCardUseCase
import com.backbase.cards_journey.mvi.BaseViewModel
import kotlinx.coroutines.launch

class CardListViewModel(val usecase: CustomCardUseCase) :
    BaseViewModel<CardListScreenViewState, CardListScreenEffect, CardListScreenEvent>() {
    override fun handleEvent(event: CardListScreenEvent) {
        when (event) {
            CardListScreenEvent.GetCardList -> getCardList()
        }
    }

    override fun createInitialState(): CardListScreenViewState {
        return CardListScreenViewState(false)
    }

    private fun getCardList() {
        if (state.value.cards != null) return
        setState {
            copy(isLoading = true)
        }

        viewModelScope.launch {
            usecase.getCards().fold(
                onSuccess = { result ->
                    setState {
                        copy(
                            isLoading = false, cards = result
                        )
                    }
                },
                onFailure = {
                    setState { copy(isLoading = false) }
                    sendEffect(CardListScreenEffect.HandleErrorResponse)
                }
            )
        }
    }
}
