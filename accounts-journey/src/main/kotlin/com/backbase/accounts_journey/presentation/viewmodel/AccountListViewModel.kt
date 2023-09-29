package com.backbase.accounts_journey.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.accounts_journey.common.DispatcherProvider
import com.backbase.accounts_journey.common.Result
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountListViewModel(
    private val useCase: AccountsUseCase,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    fun getAccounts() {
        viewModelScope.launch {
            withContext(dispatchers.default()) {
                when (val result = useCase.getAccounts()) {
                    is Result.Success -> {
                        val domain = result.value
                        println(domain.customProducts)
                        println(domain.currentAccounts)
                        println(domain.savingsAccounts)
                        println(domain.termDeposits)
                        println(domain.loans)
                        println(domain.creditCards)
                        println(domain.debitCards)
                        println(domain.investmentAccounts)
                        println(domain.aggregatedBalance)
                        println(domain.additions)
                    }

                    is Result.Error -> {
                    }
                }
            }
        }
    }
}
