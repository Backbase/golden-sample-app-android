package com.backbase.accounts_journey.generator

import com.backbase.accounts_journey.domain.model.account_summary.AccountSummary
import com.backbase.accounts_journey.domain.model.account_summary.MaskableAttribute
import com.backbase.accounts_journey.domain.model.account_summary.UserPreferences
import com.backbase.accounts_journey.domain.model.account_summary.credit_card.CreditCard
import com.backbase.accounts_journey.domain.model.account_summary.credit_card.CreditCards
import com.backbase.accounts_journey.domain.model.account_summary.current_accounts.CurrentAccount
import com.backbase.accounts_journey.domain.model.account_summary.current_accounts.CurrentAccounts
import com.backbase.accounts_journey.domain.model.account_summary.custom_products.CustomProducts
import com.backbase.accounts_journey.domain.model.account_summary.custom_products.GeneralAccount
import com.backbase.accounts_journey.domain.model.account_summary.debit_card.DebitCard
import com.backbase.accounts_journey.domain.model.account_summary.debit_card.DebitCards
import com.backbase.accounts_journey.domain.model.account_summary.investment_accounts.InvestmentAccount
import com.backbase.accounts_journey.domain.model.account_summary.investment_accounts.InvestmentAccounts
import com.backbase.accounts_journey.domain.model.account_summary.loan.Loan
import com.backbase.accounts_journey.domain.model.account_summary.loan.Loans
import com.backbase.accounts_journey.domain.model.account_summary.savings_accounts.SavingsAccount
import com.backbase.accounts_journey.domain.model.account_summary.savings_accounts.SavingsAccounts
import com.backbase.accounts_journey.domain.model.account_summary.term_deposits.TermDeposit
import com.backbase.accounts_journey.domain.model.account_summary.term_deposits.TermDeposits
import com.backbase.accounts_journey.domain.model.common.AggregatedBalance
import com.backbase.accounts_journey.domain.model.common.ProductState
import com.backbase.android.test_data.StringGenerator.generateRandomBBAN
import com.backbase.android.test_data.StringGenerator.generateRandomBIC
import com.backbase.android.test_data.StringGenerator.generateRandomCurrency
import com.backbase.android.test_data.StringGenerator.randomString
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.OffsetDateTime
import kotlin.random.Random

/**
 * A random AccountSummary generator. It is not fully implemented, but it gives you an idea how to randomize the data.
 */
@Suppress("MaximumLineLength")
object AccountSummaryGenerator {

    fun randomAccountSummaryBuilder(overrideBlock: (AccountSummary.Builder.() -> Unit)? = null) = AccountSummary.Builder().apply {
        // Custom products
        customProducts = listOf(randomCustomProducts().build())

        // Aggregated balance
        aggregatedBalance = randomAggregatedBalance().build()

        // Current accounts
        currentAccounts = randomCurrentAccounts().build()

        // Savings accounts
        savingsAccounts = randomSavingsAccounts().build()

        // Credit cards
        creditCards = randomCreditCards().build()

        // Loans
        loans = randomLoans().build()

        // Term deposits
        termDeposits = randomTermDeposits().build()

        // Debit cards
        debitCards = randomDebitCards().build()

        // Investment accounts
        investmentAccounts = randomInvestmentAccounts().build()

        // Additions
        additions = randomAdditions()

        overrideBlock?.let { this.it() }
    }

    fun randomCurrentAccounts(overrideBlock: (CurrentAccounts.Builder.() -> Unit)? = null) = CurrentAccounts.Builder().apply {
        products = (1..Random.nextInt(1, 4)).map {
            randomCurrentAccount().build()
        }
        name = "Current Accounts"

        overrideBlock?.let { this.it() }
    }

    fun randomCurrentAccount(overrideBlock: (CurrentAccount.Builder.() -> Unit)? = null) = CurrentAccount.Builder().apply {
        val randomNumber = Random.nextInt(1, 1000)

        debitCardItems = emptySet()
        id = randomString()
        name = "Current Account $randomNumber"
        displayName = "My Current Account $randomNumber"
        bookedBalance = BigDecimal(Random.nextDouble(1000.0, 50000.0)).setScale(2, RoundingMode.HALF_UP).toString()
        availableBalance = BigDecimal(Random.nextDouble(1000.0, 50000.0)).setScale(2, RoundingMode.HALF_UP).toString()
        creditLimit = BigDecimal(Random.nextDouble(1000.0, 10000.0)).setScale(2, RoundingMode.HALF_UP).toString()
        currency = generateRandomCurrency()
        BBAN = generateRandomBBAN()
        BIC = generateRandomBIC()
        unMaskableAttributes = setOf(MaskableAttribute.BBAN)
        bankBranchCode = Random.nextInt(10000, 99999).toString()
        accountInterestRate = BigDecimal(Random.nextDouble(0.1, 2.0)).setScale(3, RoundingMode.HALF_UP)
        creditLimitUsage = BigDecimal(Random.nextDouble(0.0, 1000.0)).setScale(2, RoundingMode.HALF_UP)
        creditLimitInterestRate = BigDecimal(Random.nextDouble(5.0, 15.0)).setScale(3, RoundingMode.HALF_UP)
        creditLimitExpiryDate = OffsetDateTime.now().plusYears(Random.nextLong(1, 3))
        accruedInterest = BigDecimal(Random.nextDouble(0.0, 100.0)).setScale(2, RoundingMode.HALF_UP)
        accountHolderNames = "${listOf("John", "Jane", "Michael", "Sarah").random()} ${listOf("Smith", "Johnson", "Williams", "Brown").random()}"
        startDate = OffsetDateTime.now().minusDays(Random.nextLong(30, 1000))
        creditAccount = Random.nextBoolean()
        debitAccount = Random.nextBoolean()
        externalTransferAllowed = Random.nextBoolean()
        crossCurrencyAllowed = Random.nextBoolean()
        productKindName = "Current Account"
        productTypeName = "Current Account"
        bankAlias = "Current Account $randomNumber"
        accountOpeningDate = OffsetDateTime.now().minusDays(Random.nextLong(30, 1000))
        lastUpdateDate = OffsetDateTime.now().minusDays(Random.nextLong(0, 30))
        userPreferences = UserPreferences {
            alias = "My Current Account $randomNumber"
            visible = true
            favorite = Random.nextBoolean()
        }
        state = ProductState {
            externalStateId = "Active"
            state = "Active"
        }

        overrideBlock?.let { this.it() }
    }

    fun randomCustomProducts(overrideBlock: (CustomProducts.Builder.() -> Unit)? = null) = CustomProducts.Builder().apply {
        id = Random.nextInt(1, 1000)
        products = listOf(randomGeneralAccount().build())
        name = "Custom Products"

        overrideBlock?.let { this.it() }
    }

    fun randomGeneralAccount(overrideBlock: (GeneralAccount.Builder.() -> Unit)? = null) = GeneralAccount.Builder().apply {
        debitCardItems = emptySet()
        id = randomString()
        name = "Custom Product ${randomString()}"
        displayName = "My Custom Product"
        productTypeName = "Custom"
        currency = generateRandomCurrency()
        bookedBalance = BigDecimal(Random.nextDouble(1000.0, 50000.0)).setScale(2, RoundingMode.HALF_UP).toString()
        availableBalance = BigDecimal(Random.nextDouble(1000.0, 50000.0)).setScale(2, RoundingMode.HALF_UP).toString()
        accountHolderNames = "${listOf("John", "Jane", "Michael", "Sarah").random()} ${listOf("Smith", "Johnson", "Williams", "Brown").random()}"
        state = ProductState {
            externalStateId = "Active"
            state = "Active"
        }
        userPreferences = UserPreferences {
            alias = "My Custom Product"
            visible = true
            favorite = Random.nextBoolean()
        }

        overrideBlock?.let { this.it() }
    }

    fun randomAggregatedBalance(overrideBlock: (AggregatedBalance.Builder.() -> Unit)? = null) = AggregatedBalance.Builder().apply {
        currency = generateRandomCurrency()
        value = BigDecimal(Random.nextDouble(50000.0, 500000.0)).setScale(2, RoundingMode.HALF_UP).toString()

        overrideBlock?.let { this.it() }
    }

    fun randomSavingsAccounts(overrideBlock: (SavingsAccounts.Builder.() -> Unit)? = null) = SavingsAccounts.Builder().apply {
        products = (1..Random.nextInt(1, 3)).map {
            randomSavingsAccount().build()
        }
        name = "Savings Accounts"

        overrideBlock?.let { this.it() }
    }

    fun randomSavingsAccount(overrideBlock: (SavingsAccount.Builder.() -> Unit)? = null) = SavingsAccount.Builder().apply {
        val randomNumber = Random.nextInt(1, 1000)

        id = randomString()
        name = "Savings Account $randomNumber"
        displayName = "My Savings Account $randomNumber"
        bookedBalance = BigDecimal(Random.nextDouble(5000.0, 100000.0)).setScale(2, RoundingMode.HALF_UP).toString()
        availableBalance = BigDecimal(Random.nextDouble(5000.0, 100000.0)).setScale(2, RoundingMode.HALF_UP).toString()
        currency = generateRandomCurrency()
        BBAN = generateRandomBBAN()
        BIC = generateRandomBIC()
        accountInterestRate = BigDecimal(Random.nextDouble(0.5, 4.0)).setScale(3, RoundingMode.HALF_UP)
        accountHolderNames = "${listOf("John", "Jane", "Michael", "Sarah").random()} ${listOf("Smith", "Johnson", "Williams", "Brown").random()}"
        productTypeName = "Savings Account"
        bankAlias = "Savings Account $randomNumber"
        accountOpeningDate = OffsetDateTime.now().minusDays(Random.nextLong(30, 1000))
        lastUpdateDate = OffsetDateTime.now().minusDays(Random.nextLong(0, 30))
        state = ProductState {
            externalStateId = "Active"
            state = "Active"
        }
        userPreferences = UserPreferences {
            alias = "My Savings Account $randomNumber"
            visible = true
            favorite = Random.nextBoolean()
        }

        overrideBlock?.let { this.it() }
    }

    fun randomCreditCards(overrideBlock: (CreditCards.Builder.() -> Unit)? = null) = CreditCards.Builder().apply {
        products = (1..Random.nextInt(1, 3)).map {
            randomCreditCard().build()
        }
        name = "Credit Cards"

        overrideBlock?.let { this.it() }
    }

    fun randomCreditCard(overrideBlock: (CreditCard.Builder.() -> Unit)? = null) = CreditCard.Builder().apply {
        val randomNumber = Random.nextInt(1, 1000)

        id = randomString()
        name = "Credit Card $randomNumber"
        displayName = "My Credit Card $randomNumber"
        bookedBalance = BigDecimal(Random.nextDouble(-10000.0, 0.0)).setScale(2, RoundingMode.HALF_UP).toString()
        availableBalance = BigDecimal(Random.nextDouble(1000.0, 15000.0)).setScale(2, RoundingMode.HALF_UP).toString()
        creditLimit = BigDecimal(Random.nextDouble(5000.0, 25000.0)).setScale(2, RoundingMode.HALF_UP).toString()
        currency = generateRandomCurrency()
        number = Random.nextLong(1000000000000000, 9999999999999999).toString()
        accountHolderNames = "${listOf("John", "Jane", "Michael", "Sarah").random()} ${listOf("Smith", "Johnson", "Williams", "Brown").random()}"
        productTypeName = "Credit Card"
        bankAlias = "Credit Card $randomNumber"
        validThru = OffsetDateTime.now().plusYears(Random.nextLong(1, 5))
        state = ProductState {
            externalStateId = "Active"
            state = "Active"
        }
        userPreferences = UserPreferences {
            alias = "My Credit Card $randomNumber"
            visible = true
            favorite = Random.nextBoolean()
        }

        overrideBlock?.let { this.it() }
    }

    fun randomLoans(overrideBlock: (Loans.Builder.() -> Unit)? = null) = Loans.Builder().apply {
        products = (1..Random.nextInt(1, 2)).map {
            randomLoan().build()
        }
        name = "Loans"

        overrideBlock?.let { this.it() }
    }

    fun randomLoan(overrideBlock: (Loan.Builder.() -> Unit)? = null) = Loan.Builder().apply {
        val randomNumber = Random.nextInt(1, 1000)

        id = randomString()
        name = "Loan $randomNumber"
        displayName = "My Loan $randomNumber"
        bookedBalance = BigDecimal(Random.nextDouble(-200000.0, -5000.0)).setScale(2, RoundingMode.HALF_UP).toString()
        principalAmount = BigDecimal(Random.nextDouble(10000.0, 200000.0)).setScale(2, RoundingMode.HALF_UP)
        currency = generateRandomCurrency()
        accountHolderNames = "${listOf("John", "Jane", "Michael", "Sarah").random()} ${listOf("Smith", "Johnson", "Williams", "Brown").random()}"
        productTypeName = listOf("Personal Loan", "Mortgage", "Car Loan").random()
        bankAlias = "Loan $randomNumber"
        accountOpeningDate = OffsetDateTime.now().minusDays(Random.nextLong(30, 1000))
        lastUpdateDate = OffsetDateTime.now().minusDays(Random.nextLong(0, 30))
        state = ProductState {
            externalStateId = "Active"
            state = "Active"
        }
        userPreferences = UserPreferences {
            alias = "My Loan $randomNumber"
            visible = true
            favorite = false
        }

        overrideBlock?.let { this.it() }
    }

    fun randomTermDeposits(overrideBlock: (TermDeposits.Builder.() -> Unit)? = null) = TermDeposits.Builder().apply {
        products = (1..Random.nextInt(1, 2)).map {
            randomTermDeposit().build()
        }
        name = "Term Deposits"

        overrideBlock?.let { this.it() }
    }

    fun randomTermDeposit(overrideBlock: (TermDeposit.Builder.() -> Unit)? = null) = TermDeposit.Builder().apply {
        val randomNumber = Random.nextInt(1, 1000)

        id = randomString()
        name = "Term Deposit $randomNumber"
        displayName = "My Term Deposit $randomNumber"
        bookedBalance = BigDecimal(Random.nextDouble(10000.0, 200000.0)).setScale(2, RoundingMode.HALF_UP).toString()
        principalAmount = BigDecimal(Random.nextDouble(10000.0, 200000.0)).setScale(2, RoundingMode.HALF_UP)
        currency = generateRandomCurrency()
        accountInterestRate = BigDecimal(Random.nextDouble(1.0, 6.0)).setScale(3, RoundingMode.HALF_UP)
        accountHolderNames = "${listOf("John", "Jane", "Michael", "Sarah").random()} ${listOf("Smith", "Johnson", "Williams", "Brown").random()}"
        productTypeName = "Term Deposit"
        bankAlias = "Term Deposit $randomNumber"
        maturityDate = OffsetDateTime.now().plusYears(Random.nextLong(1, 5))
        accountOpeningDate = OffsetDateTime.now().minusDays(Random.nextLong(30, 1000))
        lastUpdateDate = OffsetDateTime.now().minusDays(Random.nextLong(0, 30))
        state = ProductState {
            externalStateId = "Active"
            state = "Active"
        }
        userPreferences = UserPreferences {
            alias = "My Term Deposit $randomNumber"
            visible = true
            favorite = Random.nextBoolean()
        }

        overrideBlock?.let { this.it() }
    }

    fun randomDebitCards(overrideBlock: (DebitCards.Builder.() -> Unit)? = null) = DebitCards.Builder().apply {
        products = (1..Random.nextInt(1, 2)).map {
            randomDebitCard().build()
        }
        name = "Debit Cards"

        overrideBlock?.let { this.it() }
    }

    fun randomDebitCard(overrideBlock: (DebitCard.Builder.() -> Unit)? = null) = DebitCard.Builder().apply {
        val randomNumber = Random.nextInt(1, 1000)

        id = randomString()
        name = "Debit Card $randomNumber"
        displayName = "My Debit Card $randomNumber"
        number = Random.nextLong(1000000000000000, 9999999999999999).toString()
        accountHolderNames = "${listOf("John", "Jane", "Michael", "Sarah").random()} ${listOf("Smith", "Johnson", "Williams", "Brown").random()}"
        productTypeName = "Debit Card"
        bankAlias = "Debit Card $randomNumber"
        validThru = OffsetDateTime.now().plusYears(Random.nextLong(1, 5))
        state = ProductState {
            externalStateId = "Active"
            state = "Active"
        }
        userPreferences = UserPreferences {
            alias = "My Debit Card $randomNumber"
            visible = true
            favorite = Random.nextBoolean()
        }

        overrideBlock?.let { this.it() }
    }

    fun randomInvestmentAccounts(overrideBlock: (InvestmentAccounts.Builder.() -> Unit)? = null) = InvestmentAccounts.Builder().apply {
        products = (1..Random.nextInt(1, 2)).map {
            randomInvestmentAccount().build()
        }
        name = "Investment Accounts"

        overrideBlock?.let { this.it() }
    }

    fun randomInvestmentAccount(overrideBlock: (InvestmentAccount.Builder.() -> Unit)? = null) = InvestmentAccount.Builder().apply {
        val randomNumber = Random.nextInt(1, 1000)

        id = randomString()
        name = "Investment Account $randomNumber"
        displayName = "My Investment Account $randomNumber"
        currentInvestmentValue = BigDecimal(Random.nextDouble(10000.0, 500000.0)).setScale(2, RoundingMode.HALF_UP).toString()
        currency = generateRandomCurrency()
        accountHolderNames = "${listOf("John", "Jane", "Michael", "Sarah").random()} ${listOf("Smith", "Johnson", "Williams", "Brown").random()}"
        productTypeName = "Investment Account"
        bankAlias = "Investment Account $randomNumber"
        accountOpeningDate = OffsetDateTime.now().minusDays(Random.nextLong(30, 1000))
        lastUpdateDate = OffsetDateTime.now().minusDays(Random.nextLong(0, 30))
        state = ProductState {
            externalStateId = "Active"
            state = "Active"
        }
        userPreferences = UserPreferences {
            alias = "My Investment Account $randomNumber"
            visible = true
            favorite = Random.nextBoolean()
        }

        overrideBlock?.let { this.it() }
    }

    fun randomAdditions(): Map<String, String> {
        return mapOf(
            "customField1" to randomString(),
            "customField2" to randomString(),
            "region" to listOf("US", "EU", "APAC").random(),
            "tier" to listOf("Basic", "Premium", "Gold").random()
        )
    }
}
