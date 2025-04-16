package com.backbase.android.journey.contacts.presentation.screens.detail.intent

import com.backbase.android.journey.contacts.domain.usecase.GetContactDetailsUseCase
import com.backbase.android.journey.contacts.presentation.screens.detail.ContactDetailsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ContactDetailsIntentHandler<StateExtension>(
    private val getContactDetailsUseCase: GetContactDetailsUseCase,
    private val stateFlow: MutableStateFlow<ContactDetailsState<StateExtension>>,
    private val scope: CoroutineScope
) {

    fun handleIntent(intent: ContactDetailsIntent) {
        when (intent) {
            is ContactDetailsIntent.LoadContact -> loadContact(
                intent,
                stateFlow,
                scope,
                getContactDetailsUseCase
            )
        }

    }

    private fun loadContact(
        intent: ContactDetailsIntent.LoadContact,
        stateFlow: MutableStateFlow<ContactDetailsState<StateExtension>>,
        scope: CoroutineScope,
        getContactDetails: GetContactDetailsUseCase,
    ) {
        stateFlow.value = stateFlow.value.copy(isLoading = true)

        scope.launch {
            getContactDetails(intent.contactId).let { result ->
                result.onSuccess {
                    stateFlow.value = stateFlow.value.copy(
                        contact = it,
                        isLoading = false,
                        error = null
                    )
                }.onFailure {
                    stateFlow.value = stateFlow.value.copy(
                        isLoading = false,
                        error = it.message
                    )
                }
            }
        }

    }

}