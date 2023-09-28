package com.backbase.accounts_journey.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.accounts_journey.common.DispatcherProvider
import com.backbase.accounts_journey.common.Result
import com.backbase.accounts_journey.common.mutable
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

    val uiState: StateFlow<AccountListScreenState> = MutableStateFlow(AccountListScreenState())

    fun onEvent(event: AccountListEvent) {
        when (event) {
            AccountListEvent.OnGetAccounts -> getAccounts()
        }
    }

    private fun getAccounts() {
        viewModelScope.launch {
            withContext(dispatchers.default()) {
                when (val result = useCase.getAccounts()) {
                    is Result.Success -> {
                        val domain = result.value
                        uiState.mutable().update {
                            it.copy(
                                isLoading = false,
                                accountSummary = domain.mapToUi(),
                                error = null
                            )
                        }
                    }

                    is Result.Error -> {
                        val error = result.onErrorValue().exception.mapErrorToMessage()
                        uiState.mutable().update {
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
}
