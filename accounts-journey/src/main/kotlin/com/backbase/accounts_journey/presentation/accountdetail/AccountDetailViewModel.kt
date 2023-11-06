package com.backbase.accounts_journey.presentation.accountdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.accounts_journey.data.usecase.AccountDetailUseCase
import com.backbase.accounts_journey.presentation.mapper.mapErrorToMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountDetailViewModel(
    private val useCase: AccountDetailUseCase,
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
            withContext(defaultDispatcher) {
                val result = useCase.getAccountDetail(id)
                result.onSuccess { domain ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            accountDetail = null,
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