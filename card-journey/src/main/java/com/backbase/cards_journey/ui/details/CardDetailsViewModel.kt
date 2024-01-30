package com.backbase.cards_journey.ui.details

import androidx.lifecycle.viewModelScope
import com.backbase.card_use_case.domain.CustomCardUseCase
import com.backbase.cards_journey.mvi.BaseViewModel
import kotlinx.coroutines.launch

class CardDetailsViewModel(val cardId: String, val usecase: CustomCardUseCase) :
    BaseViewModel<CardDetailsScreenViewState, CardDetailsScreenEffect, CardDetailsScreenEvent>() {
    override fun handleEvent(event: CardDetailsScreenEvent) {
        when (event) {
            CardDetailsScreenEvent.GetCardDetails -> getCardDetails()
        }
    }

    override fun createInitialState(): CardDetailsScreenViewState {
        return CardDetailsScreenViewState(false)
    }

    private fun getCardDetails() {
        setState {
            copy(isLoading = true)
        }
        viewModelScope.launch {
            usecase.getCardDetails(cardId).fold(
                onSuccess = { result ->
                    setState {
                        copy(
                            isLoading = false, card = result
                        )
                    }
                },
                onFailure = {
                    setState { copy(isLoading = false) }
                    sendEffect(CardDetailsScreenEffect.HandleErrorResponse)
                }
            )
        }
    }
}
