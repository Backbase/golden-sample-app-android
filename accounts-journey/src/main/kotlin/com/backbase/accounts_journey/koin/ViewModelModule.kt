package com.backbase.accounts_journey.koin

import com.backbase.accounts_journey.presentation.viewmodel.AccountListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Dependency setup for the ViewModel.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
val viewModelModule = module {
    viewModel {
        AccountListViewModel(
            useCase = get()
        )
    }
}
