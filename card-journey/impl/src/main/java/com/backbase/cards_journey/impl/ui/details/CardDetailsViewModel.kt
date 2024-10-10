package com.backbase.cards_journey.impl.ui.details

import androidx.lifecycle.viewModelScope
import com.backbase.api.domain.CustomCardUseCase
import com.backbase.core_ui.mvi.mvi.BaseViewModel
import kotlinx.coroutines.launch

class CardDetailsViewModel(val cardId: String, private val useCase: CustomCardUseCase) :
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
            useCase.getCardDetails(cardId).fold(
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
