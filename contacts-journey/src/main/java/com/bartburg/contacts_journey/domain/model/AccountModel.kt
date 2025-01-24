package com.bartburg.contacts_journey.domain.model

import dev.drewhamilton.poko.Poko

@Poko
class AccountModel private constructor(
    val bankCountry: String, // ISO format
    val accountName: String,
    val alias: String?,
    val iban: String?,
    val accountNumber: String?,
    val bankBranchCode: String?,
    val accountType: String?,
    val bankName: String?,
) {
    init {
        require(iban != null || accountNumber != null) {
            "Either IBAN or account number must be provided"
        }
    }

    class Builder {
        lateinit var bankCountry: String
        lateinit var accountName: String
        var alias: String? = null
        var iban: String? = null
        var accountNumber: String? = null
        var bankBranchCode: String? = null
        var accountType: String? = null
        var bankName: String? = null

        fun build() = AccountModel(
            bankCountry = bankCountry,
            accountName = accountName,
            alias = alias,
            iban = iban,
            accountNumber = accountNumber,
            bankBranchCode = bankBranchCode,
            accountType = accountType,
            bankName = bankName,
        )
    }
}

fun AccountModel(initializer: AccountModel.Builder.() -> Unit): AccountModel {
    return AccountModel.Builder().apply(initializer).build()
} 