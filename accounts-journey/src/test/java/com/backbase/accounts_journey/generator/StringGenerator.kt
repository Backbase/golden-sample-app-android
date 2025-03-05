package com.backbase.accounts_journey.generator

import java.util.UUID
import kotlin.random.Random

object StringGenerator {
    const val STRING_LENGTH = 10
    val charPool: List<Char> = ('a'..'z') + ('A'..'Z')

    fun randomName() =
        (1..STRING_LENGTH)
            .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
            .joinToString("")

    fun randomString(): String = UUID.randomUUID().toString()

    fun String.removeWhiteSpaces(): String {
        return replace("\\s".toRegex(), "")
    }

    fun generateRandomBIC(): String {
        val letters = ('A'..'Z').toList()
        val countryCodes = listOf("DE", "FR", "IT", "ES", "NL", "BE", "US", "GB", "CH", "SE")

        // Bank Code (4 letters)
        val bankCode = (1..4).map { letters.random() }.joinToString("")

        // Country Code (2 letters)
        val countryCode = countryCodes.random()

        // Location Code (2 letters or digits)
        val locationCode = (1..2).map {
            if (Random.nextBoolean()) letters.random() else Random.nextInt(0, 10).toString()
        }.joinToString("")

        // Optional Branch Code (3 letters or digits, not always present)
        val branchCode = if (Random.nextBoolean()) (1..3).map {
            if (Random.nextBoolean()) letters.random() else Random.nextInt(0, 10).toString()
        }.joinToString("") else ""

        return bankCode + countryCode + locationCode + branchCode
    }

    fun generateRandomBBAN(): String {
        val letters = ('A'..'Z').toList()
        val digits = (0..9).toList()

        // Bank Code (4 letters)
        val bankCode = (1..4).map { letters.random() }.joinToString("")

        // Branch Code (4 digits)
        val branchCode = (1..4).map { digits.random() }.joinToString("")

        // Account Number (10 digits)
        val accountNumber = (1..10).map { digits.random() }.joinToString("")

        // National Check Digits (2 digits)
        val checkDigits = (1..2).map { digits.random() }.joinToString("")

        return "$bankCode$branchCode$accountNumber$checkDigits"
    }

    fun generateRandomCurrency(): String {
        val currencyCodes = listOf("USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD")
        return currencyCodes.random()
    }
}
