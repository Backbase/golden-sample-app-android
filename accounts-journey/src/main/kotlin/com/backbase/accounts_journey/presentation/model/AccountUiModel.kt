package com.backbase.accounts_journey.presentation.model

data class AccountUiModel(
    val id: String,
    val name: String,
    val currency: String,
    val balance: String,
    val state: String?,
    val isVisible: Boolean
)