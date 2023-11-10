package com.backbase.presentation.model

/**
 * A AccountSummary mapper from domain models to UI models.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
data class AccountsUiModel(
    val header: AccountHeaderUiModel,
    val products: List<AccountUiModel>,
)
