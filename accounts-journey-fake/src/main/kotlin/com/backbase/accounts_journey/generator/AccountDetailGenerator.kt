package com.backbase.accounts_journey.generator

import com.backbase.accounts_journey.domain.model.account_detail.AccountDetail
import com.backbase.accounts_journey.domain.model.account_detail.AccountUserPreferences
import com.backbase.accounts_journey.domain.model.account_detail.ExternalProductItem
import com.backbase.accounts_journey.domain.model.common.DebitCardItem
import com.backbase.accounts_journey.domain.model.common.ProductState
import com.backbase.accounts_journey.domain.model.common.TimeUnit
import com.backbase.android.test_data.StringGenerator
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.OffsetDateTime
import kotlin.random.Random

/**
 * A random AccountDetail generator. It is not fully implemented, but it gives you an idea how to randomize the data.
 */
@Suppress("MaximumLineLength", "LongMethod", "MaxLineLength")
object AccountDetailGenerator {

    fun randomAccountDetailBuilder(overrideBlock: (AccountDetail.Builder.() -> Unit)? = null) = AccountDetail.Builder().apply {
        // Required fields
        id = StringGenerator.randomString()
        productId = StringGenerator.randomString()
        currency = StringGenerator.generateRandomCurrency()

        // Optional fields
        productKindName = StringGenerator.randomString()
        legalEntityIds = setOf(StringGenerator.randomString(), StringGenerator.randomString())
        productTypeName = listOf("Savings", "Checking", "Credit", "Investment").random()
        externalProductId = StringGenerator.randomString()
        externalArrangementId = StringGenerator.randomString()
        userPreferences = AccountUserPreferences { arrangementId = StringGenerator.randomString() }
        product = ExternalProductItem {}
        state = ProductState {}
        parentId = StringGenerator.randomString()
        name = "Account ${StringGenerator.randomString()}"
        bookedBalance = BigDecimal(Random.nextDouble(1000.0, 100000.0)).setScale(2, RoundingMode.HALF_UP)
        availableBalance = BigDecimal(Random.nextDouble(500.0, 95000.0)).setScale(2, RoundingMode.HALF_UP)
        creditLimit = BigDecimal(Random.nextDouble(1000.0, 50000.0)).setScale(2, RoundingMode.HALF_UP)
        IBAN = "GB${Random.nextInt(10, 99)}${StringGenerator.randomString()}${Random.nextLong(10000000, 99999999)}"
        BBAN = StringGenerator.randomString()
        BIC = "${StringGenerator.randomString()}${StringGenerator.randomString()}${StringGenerator.randomString()}"
        externalTransferAllowed = Random.nextBoolean()
        urgentTransferAllowed = Random.nextBoolean()
        accruedInterest = BigDecimal(Random.nextDouble(0.0, 1000.0)).setScale(2, RoundingMode.HALF_UP)
        number = Random.nextLong(1000000000, 9999999999).toString()
        principalAmount = BigDecimal(Random.nextDouble(10000.0, 500000.0)).setScale(2, RoundingMode.HALF_UP)
        currentInvestmentValue = BigDecimal(Random.nextDouble(5000.0, 100000.0)).setScale(2, RoundingMode.HALF_UP)
        productNumber = Random.nextLong(100000, 999999).toString()
        bankBranchCode = Random.nextInt(1000, 9999).toString()
        bankBranchCode2 = Random.nextInt(100, 999).toString()
        accountOpeningDate = OffsetDateTime.now().minusDays(Random.nextLong(30, 3650))
        accountInterestRate = BigDecimal(Random.nextDouble(0.1, 5.0)).setScale(3, RoundingMode.HALF_UP)
        valueDateBalance = BigDecimal(Random.nextDouble(1000.0, 95000.0)).setScale(2, RoundingMode.HALF_UP)
        creditLimitUsage = BigDecimal(Random.nextDouble(0.0, 10000.0)).setScale(2, RoundingMode.HALF_UP)
        creditLimitInterestRate = BigDecimal(Random.nextDouble(5.0, 25.0)).setScale(3, RoundingMode.HALF_UP)
        creditLimitExpiryDate = OffsetDateTime.now().plusYears(Random.nextLong(1, 5))
        startDate = OffsetDateTime.now().minusDays(Random.nextLong(30, 1000))
        termUnit = TimeUnit.entries.toTypedArray().random()
        termNumber = BigDecimal(Random.nextInt(1, 60))
        interestPaymentFrequencyUnit = TimeUnit.entries.toTypedArray().random()
        interestPaymentFrequencyNumber = BigDecimal(Random.nextInt(1, 12))
        maturityDate = OffsetDateTime.now().plusYears(Random.nextLong(1, 10))
        maturityAmount = BigDecimal(Random.nextDouble(10000.0, 200000.0)).setScale(2, RoundingMode.HALF_UP)
        autoRenewalIndicator = Random.nextBoolean()
        interestSettlementAccount = StringGenerator.randomString()
        outstandingPrincipalAmount = BigDecimal(Random.nextDouble(5000.0, 100000.0)).setScale(2, RoundingMode.HALF_UP)
        monthlyInstalmentAmount = BigDecimal(Random.nextDouble(100.0, 2000.0)).setScale(2, RoundingMode.HALF_UP)
        amountInArrear = BigDecimal(Random.nextDouble(0.0, 500.0)).setScale(2, RoundingMode.HALF_UP)
        minimumRequiredBalance = BigDecimal(Random.nextDouble(0.0, 1000.0)).setScale(2, RoundingMode.HALF_UP)
        creditCardAccountNumber = Random.nextLong(1000000000000000, 9999999999999999).toString()
        validThru = OffsetDateTime.now().plusYears(Random.nextLong(1, 5))
        applicableInterestRate = BigDecimal(Random.nextDouble(0.5, 15.0)).setScale(3, RoundingMode.HALF_UP)
        remainingCredit = BigDecimal(Random.nextDouble(1000.0, 40000.0)).setScale(2, RoundingMode.HALF_UP)
        outstandingPayment = BigDecimal(Random.nextDouble(0.0, 5000.0)).setScale(2, RoundingMode.HALF_UP)
        minimumPayment = BigDecimal(Random.nextDouble(25.0, 500.0)).setScale(2, RoundingMode.HALF_UP)
        minimumPaymentDueDate = OffsetDateTime.now().plusDays(Random.nextLong(1, 30))
        totalInvestmentValue = BigDecimal(Random.nextDouble(10000.0, 500000.0)).setScale(2, RoundingMode.HALF_UP)
        debitCards = setOf(DebitCardItem { })
        accountHolderAddressLine1 = "${Random.nextInt(1, 999)} ${listOf("Main", "Oak", "Elm", "Pine", "First").random()} Street"
        accountHolderAddressLine2 = if (Random.nextBoolean()) "Apt ${Random.nextInt(1, 999)}" else null
        accountHolderStreetName = listOf("Main Street", "Oak Avenue", "Elm Drive", "Pine Road").random()
        town = listOf("New York", "Los Angeles", "Chicago", "Houston", "Phoenix").random()
        postCode = Random.nextInt(10000, 99999).toString()
        countrySubDivision = listOf("NY", "CA", "IL", "TX", "AZ").random()
        accountHolderNames = "${listOf("John", "Jane", "Michael", "Sarah", "David").random()} ${listOf("Smith", "Johnson", "Williams", "Brown", "Jones").random()}"
        accountHolderCountry = listOf("US", "GB", "DE", "FR", "NL").random()
        creditAccount = Random.nextBoolean()
        debitAccount = Random.nextBoolean()
        lastUpdateDate = OffsetDateTime.now().minusDays(Random.nextLong(0, 30))
        bankAlias = "Bank ${StringGenerator.randomString()}"
        sourceId = StringGenerator.randomString()
        externalStateId = StringGenerator.randomString()
        externalParentId = StringGenerator.randomString()
        financialInstitutionId = Random.nextLong(1000, 9999)
        lastSyncDate = OffsetDateTime.now().minusHours(Random.nextLong(1, 48))
        additions = mapOf(
            "customField1" to StringGenerator.randomString(),
            "customField2" to StringGenerator.randomString()
        )
        unmaskableAttributes = null // Complex type - keeping null for now
        displayName = "Display ${StringGenerator.randomString()}"
        cardDetails = null // Complex type - keeping null for now
        interestDetails = null // Complex type - keeping null for now
        reservedAmount = BigDecimal(Random.nextDouble(0.0, 1000.0)).setScale(2, RoundingMode.HALF_UP)
        remainingPeriodicTransfers = BigDecimal(Random.nextInt(0, 10))
        nextClosingDate = LocalDate.now().plusDays(Random.nextLong(1, 30))
        overdueSince = if (Random.nextBoolean()) LocalDate.now().minusDays(Random.nextLong(1, 60)) else null
        externalAccountStatus = listOf("ACTIVE", "INACTIVE", "PENDING", "CLOSED").random()

        overrideBlock?.let { this.it() }
    }
}
