package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.foundation.mvi.IntentHandlerCollection
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase

fun <StateExtension> createContactIntentHandlers(
    saveNewContactUseCase: SaveNewContactUseCase
) = IntentHandlerCollection(
    handlers = listOf(
        updateNameHandler<StateExtension>(),
        updateEmailHandler<StateExtension>(),
        updateAccountNumberHandler<StateExtension>(),
        saveContactIntentHandler(saveNewContactUseCase)
    )
)
