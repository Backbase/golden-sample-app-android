package com.backbase.accounts_journey.presentation.accountdetail.model

import androidx.annotation.DrawableRes

/**
 * The UI model for AccountDetail.
 *
 * Created by Backbase R&D B.V on 16/11/2023.
 */
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
