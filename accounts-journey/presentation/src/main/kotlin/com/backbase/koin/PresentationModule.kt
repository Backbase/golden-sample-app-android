package com.backbase.koin

import com.backbase.presentation.mapper.AccountUiMapper
import com.backbase.presentation.viewmodel.AccountListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Dependency setup for the ViewModel.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
val presentationModule = module {
    factory { AccountUiMapper(accountsJourneyConfiguration = get()) }
    viewModel {
        AccountListViewModel(
            useCase = get(),
            mapper = get(),
        )
    }
}
