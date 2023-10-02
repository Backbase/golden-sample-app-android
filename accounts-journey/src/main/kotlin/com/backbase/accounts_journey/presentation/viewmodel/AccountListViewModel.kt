package com.backbase.accounts_journey.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.accounts_journey.common.DispatcherProvider
import com.backbase.accounts_journey.common.Result
import com.backbase.accounts_journey.common.onErrorValue
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.presentation.mapper.mapErrorToMessage
import com.backbase.accounts_journey.presentation.mapper.mapToUi
import com.backbase.accounts_journey.presentation.ui.AccountListEvent
import com.backbase.accounts_journey.presentation.ui.AccountListScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountListViewModel(
    private val useCase: AccountsUseCase,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountListScreenState())
    val uiState: StateFlow<AccountListScreenState> = _uiState

    fun onEvent(event: AccountListEvent) {
        when (event) {
            AccountListEvent.OnGetAccounts -> getAccountSummary()
            is AccountListEvent.OnSearch -> searchAccounts()
        }
    }

    private fun getAccountSummary() {
        viewModelScope.launch {
            withContext(dispatchers.default()) {
                when (val result = useCase.getAccounts()) {
                    is Result.Success -> {
                        val domain = result.value
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                accountSummary = domain.mapToUi(),
                                error = null
                            )
                        }
                    }

                    is Result.Error -> {
                        val error = result.onErrorValue().exception.mapErrorToMessage()
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = error
                            )
                        }
                    }
                }
            }
        }
    }

    private fun searchAccounts() {
        viewModelScope.launch {
            withContext(dispatchers.default()) {
            }
        }
    }
}
