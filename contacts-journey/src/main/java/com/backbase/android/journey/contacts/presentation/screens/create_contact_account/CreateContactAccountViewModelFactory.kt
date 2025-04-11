package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCase

class CreateContactAccountViewModelFactory<StateExtension>(
    private val saveNewAccountUseCase: SaveNewAccountUseCase,
    private val saveAccountIntentHandler: SaveAccountIntentHandler<StateExtension> = SaveAccountIntentHandlerImpl<StateExtension>(),
    private val updateNumberIntentHandler: UpdateNumberIntentHandler<StateExtension> = UpdateNumberIntentHandlerImpl<StateExtension>(),
    private val updateNameIntentHandler: UpdateNameIntentHandler<StateExtension> = UpdateNameIntentHandlerImpl<StateExtension>(),
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateContactAccountViewModel2::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateContactAccountViewModel2<StateExtension>(
                saveNewAccountUseCase = saveNewAccountUseCase,
                saveAccountIntentHandler = saveAccountIntentHandler,
                updateNumberIntentHandler = updateNumberIntentHandler,
                updateNameIntentHandler = updateNameIntentHandler
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}