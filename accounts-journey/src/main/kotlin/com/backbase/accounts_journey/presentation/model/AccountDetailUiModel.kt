package com.backbase.accounts_journey.presentation.model

import androidx.annotation.DrawableRes

data class AccountDetailUiModel(
    val id: String,
    val name: String,
    @Suppress("ConstructorParameterNaming")
    val BBAN: String,
    val availableBalance: String,
    val accountHolderNames: String,
    val productKindName: String,
    val bankBranchCode: String?,
    val lastUpdateDate: String,
    val accountInterestRate: String?,
    val accruedInterest: String,
    val creditLimit: String,
    val accountOpeningDate: String,
    @DrawableRes val icon: Int,
)
