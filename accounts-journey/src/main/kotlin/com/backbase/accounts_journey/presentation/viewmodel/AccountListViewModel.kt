package com.backbase.accounts_journey.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountListViewModel(
    private val useCase: AccountsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
//    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    fun getAccounts() {
        viewModelScope.launch {
            val data = withContext(dispatcher) {
                useCase.getAccounts()
                // TODO map to UI
            }
        }
    }
}
