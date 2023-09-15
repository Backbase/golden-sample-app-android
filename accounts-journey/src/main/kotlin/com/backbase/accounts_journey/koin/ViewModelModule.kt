package com.backbase.accounts_journey.koin

import com.backbase.accounts_journey.presentation.viewmodel.AccountListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AccountListViewModel(
            useCase = get(),
            dispatchers = get()
        )
    }

    // TODO add ui mapper
}
