package com.backbase.android.journey.contacts.presentation.screens.create_contact_account2

import kotlinx.coroutines.flow.MutableStateFlow

interface CreateContactAccountIntentExtensionHandler<StateExtension, IntentExtension> {
    operator fun invoke(
        currentState: CreateContactAccountState<StateExtension>,
        intentExtension: IntentExtension,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>)
}