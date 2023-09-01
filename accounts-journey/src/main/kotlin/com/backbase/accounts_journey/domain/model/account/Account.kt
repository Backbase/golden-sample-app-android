package com.backbase.accounts_journey.domain.model.account

import dev.drewhamilton.poko.Poko

/**
 * Created by Backbase R&D B.V. on 01/07/2020.
 *
 * @param id the account id. Will be used to fetch account statements.
 * @param name the account name, like "Current Account".
 * @param number a string representation of the account number.
 * @param amount the absolute amount measured in the currency represented by [currencyCode].
 * @param currencyCode ISO4217 currency code of the [amount].
 */
@Poko
class Account internal constructor(
    val id: String?,
    val name: String?,
    val number: String?,
    val amount: Double?,
    val currencyCode: String?
) {

    /**
     * A builder for [AccountModel]
     */
    class Builder {

        /**
         * The account id. Will be used to fetch account statements.
         */
        @set:JvmSynthetic
        var id: String? = null

        /**
         * The account name, like "Current Account".
         */
        @set:JvmSynthetic
        var name: String? = null

        /**
         * A string representation of the account number.
         */
        @set:JvmSynthetic
        var number: String? = null

        /**
         * The absolute amount measured in the currency represented by [currencyCode].
         */
        @set:JvmSynthetic
        var amount: Double? = null

        /**
         * ISO4217 currency code of the [amount].
         */
        @set:JvmSynthetic
        var currencyCode: String? = null

        /**
         * The account id. Will be used to fetch account statements.
         */
        fun setId(id: String?) =
            apply {
                this.id = id
            }

        /**
         * The account name, like "Current Account".
         */
        fun setName(name: String?) =
            apply {
                this.name = name
            }

        /**
         * A string representation of the account number.
         */
        fun setNumber(number: String?) =
            apply {
                this.number = number
            }

        /**
         * The absolute amount measured in the currency represented by [currencyCode].
         */
        fun setAmount(amount: Double?) =
            apply {
                this.amount = amount
            }

        /**
         * ISO4217 currency code of the [amount].
         */
        fun setCurrencyCode(currencyCode: String?) =
            apply {
                this.currencyCode = currencyCode
            }

        /**
         * Builds an instance of [AccountModel]
         */
        fun build() =
            Account(
                id = id,
                name = name,
                number = number,
                amount = amount,
                currencyCode = currencyCode
            )
    }
}

/**
 * @see [AccountModel]
 */
@Suppress("FunctionName") // DSL initializer
@JvmSynthetic // Hide from Java callers who should use Builder
fun AccountModel(
    initializer: Account.Builder.() -> Unit
): Account =
    Account.Builder()
        .apply(initializer).build()