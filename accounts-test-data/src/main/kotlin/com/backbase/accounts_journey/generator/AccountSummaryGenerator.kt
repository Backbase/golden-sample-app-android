package com.backbase.accounts_journey.generator

import com.backbase.android.client.gen2.arrangementclient2.model.CurrentAccount
import com.backbase.android.client.gen2.arrangementclient2.model.CurrentAccountProductKinds
import com.backbase.android.client.gen2.arrangementclient2.model.MaskableAttribute
import com.backbase.android.client.gen2.arrangementclient2.model.ProductSummary
import com.backbase.android.client.gen2.arrangementclient2.model.StateItem
import com.backbase.android.client.gen2.arrangementclient2.model.UserPreferences
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
        availableBalance: BigDecimal = BigDecimal.valueOf(randomFloat(1, 100).toDouble()),
    ): ProductSummary {
        return ProductSummary {
            customProductKinds = emptyList()
            savingsAccounts = null
            termDeposits = null
            loans = null
            creditCards = null
            debitCards = null
            investmentAccounts = null
            aggregatedBalance = null
            additions = null
            currentAccounts = CurrentAccountProductKinds {
                name = "Current Accounts"
                products = listOf(
                    CurrentAccount {
                        this.id = id
                        this.displayName = displayName
                        name = "TESTDATA"
                        productKindName = "Current Account"
                        productTypeName = "Current Account"
                        bankAlias = "Test Account"
                        accountHolderNames = "Test User"
                        BBAN = generateRandomBBAN()
                        BIC = generateRandomBIC()
                        currency = generateRandomCurrency()
                        bankBranchCode = "12345"
                        bookedBalance = availableBalance.toString()
                        this.availableBalance = availableBalance.toString()
                        creditLimit = "0"
                        creditLimitUsage = BigDecimal.ZERO
                        creditLimitInterestRate = BigDecimal.ZERO
                        creditLimitExpiryDate = OffsetDateTime.of(2029, 12, 21, 0, 0, 0, 0, ZoneOffset.UTC)
                        accountInterestRate = BigDecimal.ZERO
                        accruedInterest = BigDecimal.ZERO
                        startDate = OffsetDateTime.now()
                        accountOpeningDate = OffsetDateTime.now()
                        lastUpdateDate = OffsetDateTime.now()
                        creditAccount = true
                        debitAccount = true
                        externalTransferAllowed = true
                        crossCurrencyAllowed = true
                        unmaskableAttributes = setOf(MaskableAttribute.BBAN)
                        userPreferences = UserPreferences {
                            alias = "Test Account"
                            visible = true
                            favorite = false
                        }
                        state = StateItem {
                            externalStateId = "Active"
                            state = "Active"
                        }
                        debitCardsItems = emptySet()
                    }
                )
            }
        }
    }
}