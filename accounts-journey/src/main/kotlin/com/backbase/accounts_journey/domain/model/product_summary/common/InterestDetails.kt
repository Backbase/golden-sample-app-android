package com.backbase.accounts_journey.domain.model.product_summary.common

import android.os.Parcelable
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param lastYearAccruedInterest Last year's interest that was earned (credit interest) or due (debit interest)
 * @param dividendWithheldYTD Total amount of interest that is being withheld up to date and the bank provides 1099 document end of the year for Tax return purpose Example: - 12.32: as fixed amount - 3.14%: as percent
 * @param annualPercentageYield The real rate of return earned on a savings deposit or investment taking into account the effect of compounding interest.
 * @param cashAdvanceInterestRate The Interest charge that is added monthly on the outstanding cash advance due on a credit card.
 * @param penaltyInterestRate The Interest charge triggered by infractions such as late credit card payments.
 * @param additions Extra parameters
 */
@SuppressWarnings("MaxLineLength")
@Poko
@Parcelize
class InterestDetails internal constructor(
    val lastYearAccruedInterest: BigDecimal?,
    val dividendWithheldYTD: String?,
    val annualPercentageYield: BigDecimal?,
    val cashAdvanceInterestRate: BigDecimal?,
    val penaltyInterestRate: BigDecimal?,
    val additions: Map<String, String>?,
) : Parcelable {

    /**
     * A builder for this configuration class
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function
     */
    class Builder {

        /**
         * See [InterestDetails.lastYearAccruedInterest]
         */
        var lastYearAccruedInterest: BigDecimal? = null

        /**
         * See [InterestDetails.dividendWithheldYTD]
         */
        var dividendWithheldYTD: String? = null

        /**
         * See [InterestDetails.annualPercentageYield]
         */
        var annualPercentageYield: BigDecimal? = null

        /**
         * See [InterestDetails.cashAdvanceInterestRate]
         */
        var cashAdvanceInterestRate: BigDecimal? = null

        /**
         * See [InterestDetails.penaltyInterestRate]
         */
        var penaltyInterestRate: BigDecimal? = null

        /**
         * See [InterestDetails.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * Build an instance of [InterestDetails]
         */
        fun build() = InterestDetails(
            lastYearAccruedInterest = lastYearAccruedInterest,
            dividendWithheldYTD = dividendWithheldYTD,
            annualPercentageYield = annualPercentageYield,
            cashAdvanceInterestRate = cashAdvanceInterestRate,
            penaltyInterestRate = penaltyInterestRate,
            additions = additions,
        )
    }
}

/**
 * DSL to create [InterestDetails]
 */
@JvmSynthetic
fun InterestDetails(block: InterestDetails.Builder.() -> Unit) = InterestDetails.Builder().apply(block).build()
