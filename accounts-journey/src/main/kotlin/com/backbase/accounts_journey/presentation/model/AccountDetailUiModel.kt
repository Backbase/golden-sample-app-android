package com.backbase.accounts_journey.presentation.model

data class AccountDetailUiModel(
    val id: String,
    val name: String,
    val BBAN: String,
    val availableBalance: String,
    val accountHolderNames: String,
    val productTypeName: String,
    val bankBranchCode: String,
    val lastUpdateDate: String,
    val accountInterestRate: String,
    val accruedInterest: String,
    val creditLimit: String,
    val accountOpeningDate: String,
)
