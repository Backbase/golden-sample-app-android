package com.backbase.accounts_journey.presentation.accountdetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.accounts_journey.data.usecase.AccountDetailUseCase
import com.backbase.accounts_journey.data.usecase.Params
import com.backbase.accounts_journey.presentation.accountdetail.mapper.AccountDetailUiMapper
import com.backbase.accounts_journey.presentation.mapper.mapErrorToMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * The ViewModel for the account detail. It contains use case to fetch the data and it updates the
 * UI state that is observed by the UI.
 *
 * Created by Backbase R&D B.V on 16/11/2023.
 */
class AccountDetailViewModel(
    private val useCase: AccountDetailUseCase,
    private val mapper: AccountDetailUiMapper,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountDetailScreenState())
    val uiState: StateFlow<AccountDetailScreenState> = _uiState

    fun onEvent(event: AccountDetailEvent) {
        when (event) {
            is AccountDetailEvent.OnGetAccountDetail -> getAccountDetail(event.id)
        }
    }

    private fun getAccountDetail(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            withContext(defaultDispatcher) {
                val result = useCase.getAccountDetail(Params { this.id = id })
                result.onSuccess { domain ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            accountDetail = mapper.mapToUi(domain),
                            error = null
                        )
                    }
                }

                result.onFailure { throwable ->
                    println(throwable.message)
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
