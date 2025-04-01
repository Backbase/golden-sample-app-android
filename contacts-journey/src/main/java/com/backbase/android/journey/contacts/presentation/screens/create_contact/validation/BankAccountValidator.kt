package com.backbase.android.journey.contacts.presentation.screens.create_contact.validation

import java.math.BigInteger
import java.util.Locale

object BankAccountValidator {

    fun detectBankAccountType(accountNumber: String): String? {
        val cleanedAccountNumber = accountNumber.replace(" ", "").uppercase(Locale.ROOT)

        if (validateIban(cleanedAccountNumber)) {
            return "IBAN"
        }

        val countryPatterns = mapOf(
            "US" to "^[0-9]{9,17}$",  // US bank account numbers
            "UK" to "^[0-9]{8}$",  // UK bank account numbers
            "DE" to "^[0-9]{10}$",  // Germany bank account numbers
            "FR" to "^[0-9]{23}$",  // France bank account numbers
            "IN" to "^[0-9]{9,18}$"  // India bank account numbers
        )

        for ((country, pattern) in countryPatterns) {
            if (Regex(pattern).matches(cleanedAccountNumber)) {
                return country
            }
        }
        return null  // Unknown format
    }

    fun validateBankAccount(accountNumber: String): Boolean =
        detectBankAccountType(accountNumber) != null


    fun validateIban(iban: String): Boolean {
        val cleanedIban = iban.replace(" ", "").uppercase(Locale.ROOT)
        if (!Regex("^[A-Z]{2}\\d{2}[A-Z0-9]{1,30}$").matches(cleanedIban)) {
            return false
        }
        val rearranged = cleanedIban.drop(4) + cleanedIban.take(4)
        val numericIban = rearranged.map { ch ->
            if (ch.isDigit()) ch.toString() else (ch.code - 'A'.code + 10).toString()
        }.joinToString("")
        return BigInteger(numericIban).mod(BigInteger.valueOf(97)) == BigInteger.ONE
    }

}