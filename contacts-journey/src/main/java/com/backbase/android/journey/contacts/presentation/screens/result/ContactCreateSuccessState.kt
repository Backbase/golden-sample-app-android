package com.backbase.android.journey.contacts.presentation.screens.result

import com.backbase.android.journey.contacts.domain.model.ContactModel

data class ContactCreateSuccessState<StateExtension> (
    val stateExtension: StateExtension? = null
)

typealias DefaultContactCreateSuccessState = ContactCreateSuccessState<Unit>