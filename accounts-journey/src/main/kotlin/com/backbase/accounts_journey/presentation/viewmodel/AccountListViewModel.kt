package com.backbase.accounts_journey.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.backbase.accounts_journey.common.DispatcherProvider
import com.backbase.accounts_journey.data.usecase.AccountsUseCase

class AccountListViewModel(
    private val useCase: AccountsUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

}
