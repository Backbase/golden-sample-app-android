package com.backbase.android.journey.contacts.domain.model


data class AccountModel<Extension> (
    val bankCountry: String?, // ISO format
    val accountName: String?,
    val alias: String?,
    val iban: String?,
    val accountNumber: String?,
    val bankBranchCode: String?,
    val accountType: String?,
    val bankName: String?,
    val extension: Extension? = null
)