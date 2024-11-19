package com.backbase.cards_journey.impl.ui.list

import androidx.lifecycle.viewModelScope
import com.backbase.api.domain.CustomCardUseCase
import com.backbase.core_ui.mvi.mvi.BaseViewModel
import kotlinx.coroutines.launch

class CardListViewModel(private val useCase: CustomCardUseCase) :
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
            useCase.getCards().fold(
                onSuccess = { result ->
                    setState {
                        copy(
                            isLoading = false,
                            cards = result
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
