package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.ViewModelProvider
import com.backbase.android.foundation.mvi.SideEffectHandler
import com.backbase.android.foundation.mvi.ViewModel

class CreateContactViewModel<StateExtension>(
    initialState: CreateContactState<StateExtension> = CreateContactState(),
    reducer: CreateContactReducer<StateExtension>,
    sideEffectHandler: SideEffectHandler<CreateContactIntent, CreateContactState<StateExtension>, CreateContactSideEffect>,
) : ViewModel<CreateContactIntent, CreateContactState<StateExtension>, CreateContactSideEffect>(
    initialState = initialState,
    reducer = reducer,
    sideEffectHandler = sideEffectHandler
)

class CreateContactViewModelFactory<S>(
    private val reducer: CreateContactReducer<S>,
    private val sideEffectHandler: SideEffectHandler<CreateContactIntent, CreateContactState<S>, CreateContactSideEffect>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T = CreateContactViewModel<S>(
        initialState = CreateContactState(),
        reducer = reducer,
        sideEffectHandler = sideEffectHandler
    ) as T
}
