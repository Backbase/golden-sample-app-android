package com.backbase.accounts_journey.domain.model.common

import android.os.Parcelable
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.time.LocalDate

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param cardProvider This field specifies the Card Provider associated with the Account. Example: Maestro, Visa, Master Card, American Express or Discover.
 * @param secured A card can either be Secured or Unsecured. true: amount deposited in the CC determines the Limit. false: credit limit is based off various factors including the CC holder’s Credit Score, Credit History and is determined by the lending bank.
 * @param availableCashCredit The amount of money currently available for a bank cash advance.This is calculated given the portion of the CashCreditLimit which has been used for Cash Advance Transactions.
 * @param cashCreditLimit The portion of the credit limit available for bank cash advance transactions.
 * @param lastPaymentDate The Date the last payment was made on the Credit-based arrangement.
 * @param lastPaymentAmount The amount of the last payment that was made on the Credit-based arrangement.
 * @param latePaymentFee The charge triggered by infractions such as late credit card payments. It can be expressed as fixed amount or as percent.Example: 12.32: as fixed amount, 3.14%: as percent
 * @param previousStatementDate The date of the previous billing cycle for the arrangement.
 * @param previousStatementBalance The amount owed on the credit card as of the previous billing cycle.
 * @param statementBalance The amount owed on the credit card as of the latest billing cycle.
 * @param additions Extra parameters
 */
@Suppress("LongParameterList", "MaxLineLength")
@Poko
@Parcelize
class CardDetails internal constructor(
    val cardProvider: String?,
    val secured: Boolean?,
    val availableCashCredit: BigDecimal?,
    val cashCreditLimit: BigDecimal?,
    val lastPaymentDate: LocalDate?,
    val lastPaymentAmount: BigDecimal?,
    val latePaymentFee: String?,
    val previousStatementDate: LocalDate?,
    val previousStatementBalance: BigDecimal?,
    val statementBalance: BigDecimal?,
    val additions: Map<String, String>?
) : Parcelable {

    /**
     * A builder for this configuration class
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function
     */
    class Builder {

        /**
         * See [CardDetails.cardProvider]
         */
        var cardProvider: String? = null

        /**
         * See [CardDetails.secured]
         */
        var secured: Boolean? = null

        /**
         * See [CardDetails.availableCashCredit]
         */
        var availableCashCredit: BigDecimal? = null

        /**
         * See [CardDetails.cashCreditLimit]
         */
        var cashCreditLimit: BigDecimal? = null

        /**
         * See [CardDetails.lastPaymentDate]
         */
        var lastPaymentDate: LocalDate? = null

        /**
         * See [CardDetails.lastPaymentAmount]
         */
        var lastPaymentAmount: BigDecimal? = null

        /**
         * See [CardDetails.latePaymentFee]
         */
        var latePaymentFee: String? = null

        /**
         * See [CardDetails.previousStatementDate]
         */
        var previousStatementDate: LocalDate? = null

        /**
         * See [CardDetails.previousStatementBalance]
         */
        var previousStatementBalance: BigDecimal? = null

        /**
         * See [CardDetails.statementBalance]
         */
        var statementBalance: BigDecimal? = null

        /**
         * See [CardDetails.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * Build an instance of [CardDetails]
         */
        fun build() = CardDetails(
            cardProvider,
            secured,
            availableCashCredit,
            cashCreditLimit,
            lastPaymentDate,
            lastPaymentAmount,
            latePaymentFee,
            previousStatementDate,
            previousStatementBalance,
            statementBalance,
            additions
        )
    }
}

/**
 * DSL to create [CardDetails]
 */
fun CardDetails(block: CardDetails.Builder.() -> Unit) = CardDetails.Builder().apply(block).build()
