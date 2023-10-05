package com.backbase.accounts_journey.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.presentation.mapper.mapErrorToMessage
import com.backbase.accounts_journey.presentation.mapper.mapToUi
import com.backbase.accounts_journey.presentation.ui.AccountListEvent
import com.backbase.accounts_journey.presentation.ui.AccountListScreenState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * The ViewModel for the account list. It contains use case to fetch the data and it updates the
 * UI state that is observed by the UI.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
class AccountListViewModel(
    private val useCase: AccountsUseCase,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountListScreenState())
    val uiState: StateFlow<AccountListScreenState> = _uiState

    fun onEvent(event: AccountListEvent) {
        when (event) {
            AccountListEvent.OnGetAccounts -> getAccountSummary(showLoading = true)
            AccountListEvent.OnRefresh -> getAccountSummary(useCache = false, showLoading = true)
            is AccountListEvent.OnSearch -> getAccountSummary(query = event.query)
        }
    }

    private fun getAccountSummary(
        query: String = "",
        useCache: Boolean = true,
        showLoading: Boolean = false
    ) {
        viewModelScope.launch {
            if (showLoading) _uiState.update { it.copy(isLoading = true) }

            withContext(defaultDispatcher) {
                val result = useCase.getAccountSummary(useCache)
                result.onSuccess { domain ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            accountSummary = domain.mapToUi().generateList(query),
                            error = null
                        )
                    }
                }
                result.onFailure { throwable ->
                    val error = throwable.mapErrorToMessage()
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
