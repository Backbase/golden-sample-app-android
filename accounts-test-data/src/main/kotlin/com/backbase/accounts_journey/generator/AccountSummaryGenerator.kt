package com.backbase.accounts_journey.generator

import com.backbase.accounts_journey.domain.model.account_summary.AccountSummary
import com.backbase.accounts_journey.domain.model.account_summary.MaskableAttribute
import com.backbase.accounts_journey.domain.model.account_summary.UserPreferences
import com.backbase.accounts_journey.domain.model.account_summary.current_accounts.CurrentAccount
import com.backbase.accounts_journey.domain.model.account_summary.current_accounts.CurrentAccounts
import com.backbase.accounts_journey.domain.model.common.ProductState
import com.backbase.android.test_data.NumberGenerator.randomFloat
import com.backbase.android.test_data.StringGenerator.generateRandomBBAN
import com.backbase.android.test_data.StringGenerator.generateRandomBIC
import com.backbase.android.test_data.StringGenerator.generateRandomCurrency
import com.backbase.android.test_data.StringGenerator.randomString
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.time.ZoneOffset
import kotlin.random.Random

/**
 * A random AccountSummary generator. It is not fully implemented, but it gives you an idea how to randomize the data.
 */
object AccountSummaryGenerator {

    fun generateAccountSummary(
        id: String = randomString(),
        displayName: String = randomString(),
        availableBalance: String = randomFloat(1, 100).toString(),
    ): AccountSummary {
        return AccountSummary {
            currentAccounts = CurrentAccounts {
                products = listOf(
                    CurrentAccount {
                        debitCardItems = emptySet()
                        bookedBalance = randomFloat().toString()
                        this.availableBalance = availableBalance
                        creditLimit = randomFloat().toString()
                        BBAN = generateRandomBBAN()
                        BIC = generateRandomBIC()
                        unMaskableAttributes = setOf(MaskableAttribute.BBAN)
                        currency = generateRandomCurrency()
                        bankBranchCode = Random.nextInt(10000, 99999).toString()
                        bankBranchCode2 = null
                        accountInterestRate = BigDecimal.ONE
                        creditLimitUsage = BigDecimal.TEN
                        creditLimitInterestRate = BigDecimal.ONE
                        creditLimitExpiryDate =
                            OffsetDateTime.of(2029, 12, 21, 0, 0, 0, 0, ZoneOffset.UTC)
                        accruedInterest = BigDecimal.ZERO
                        accountHolderNames = "Paolo Doe"
                        startDate = OffsetDateTime.now()
                        creditAccount = true
                        debitAccount = true
                        this.id = id
                        name = "TESTDATA"
                        externalTransferAllowed = true
                        crossCurrencyAllowed = true
                        productKindName = "Current Account"
                        productTypeName = "Current Account"
                        bankAlias = "Paolo's Current Account"
                        accountOpeningDate = OffsetDateTime.now()
                        lastUpdateDate = OffsetDateTime.now()
                        userPreferences = UserPreferences {
                            alias = "Paolo’s Current Test"
                            visible = true
                            favorite = false
                        }
                        state = ProductState {
                            externalStateId = "Active"
                            state = "Active"
                        }
                        this.displayName = displayName
                    }
                )
                name = "Current Accounts"
            }
        }
    }
}
