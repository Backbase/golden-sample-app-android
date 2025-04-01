package com.backbase.android.journey.contacts.domain.model

import com.backbase.android.journey.contacts.domain.model.util.ModelExtension


data class AccountModel(
    val bankCountry: String? = null, // ISO format
    val accountName: String? = null,
    val alias: String? = null,
    val iban: String? = null,
    val accountNumber: String?,
    val bankBranchCode: String? = null,
    val accountType: String? = null,
    val bankName: String? = null,
    val extension: ModelExtension<*>? = null
)