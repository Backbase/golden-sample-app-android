package com.backbase.golden_sample_app.extend_journey.accounts.usecase

import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.domain.model.account_summary.AccountSummary
import com.backbase.accounts_journey.domain.model.account_summary.UserPreferences
import com.backbase.accounts_journey.domain.model.account_summary.current_accounts.CurrentAccount
import com.backbase.accounts_journey.domain.model.account_summary.current_accounts.CurrentAccounts
import com.backbase.accounts_journey.domain.model.account_summary.savings_accounts.SavingsAccount
import com.backbase.accounts_journey.domain.model.account_summary.savings_accounts.SavingsAccounts
import com.backbase.accounts_journey.domain.model.common.ProductState
import com.backbase.golden_sample_app.extend_journey.accounts.usecase.RandomData.randomName
import com.backbase.golden_sample_app.extend_journey.accounts.usecase.RandomData.randomString
import com.backbase.golden_sample_app.extend_journey.accounts.usecase.RandomData.round
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.UUID
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random
import kotlin.streams.asSequence

/**
 * An example how to replace a use case.
 * This use case generates fake accounts.
 */
class CustomAccountsUseCaseImpl : AccountsUseCase {

    private var cache: AccountSummary? = null

    override suspend fun getAccountSummary(useCache: Boolean): Result<AccountSummary> {
        if (useCache && cache != null) {
            return Result.success(cache!!)
        }
        val domainModel = AccountSummary {
            currentAccounts = generateCurrentAccounts()
            savingsAccounts = generateSavingsAccounts()
        }
        cache = domainModel
        return Result.success(domainModel)
    }
}

/**
 * Generate random current accounts and sort by descending on the available balance and sort by status.
 */
private fun generateCurrentAccounts(amount: Int = Random.nextInt(2, 10)): CurrentAccounts {
    return CurrentAccounts {
        products = List(amount) {
            CurrentAccount {
                debitCardItems = emptySet()
                id = randomString()
                displayName = randomName()
                availableBalance = ((0..10000).random().toFloat().round().toString())
                state = ProductState {
                    val status = if (Random.nextBoolean()) "Active" else "Closed"
                    externalStateId = status
                    state = status
                }
                BBAN = "*********************" + Random.nextInt(1, 100)
                userPreferences = UserPreferences {
                    alias = randomName()
                    visible = Random.nextBoolean()
                    favorite = Random.nextBoolean()
                }
            }
        }.sortedByDescending { it.availableBalance?.toBigDecimal() }.sortedBy { it.state?.state }
        name = "Fake Current Accounts"
    }
}

/**
 * Generate random savings accounts and sort by descending on the available balance and sort by status.
 */
private fun generateSavingsAccounts(amount: Int = Random.nextInt(2, 5)): SavingsAccounts {
    return SavingsAccounts {
        products = List(amount) {
            SavingsAccount {
                id = randomString()
                displayName = randomName()
                availableBalance = ((0..10000).random().toFloat().round().toString())
                state = ProductState {
                    val status = if (Random.nextBoolean()) "Active" else "Closed"
                    externalStateId = status
                    state = status
                }
                BBAN = "*********************" + Random.nextInt(1, 100)
                userPreferences = UserPreferences {
                    alias = randomName()
                    visible = Random.nextBoolean()
                    favorite = Random.nextBoolean()
                }
            }
        }.sortedByDescending { it.availableBalance?.toBigDecimal() }.sortedBy { it.state?.state }
        name = "Fake Savings Accounts"
    }
}

object RandomData {

    fun Float.round(decimalPlace: Int = 2): Float {
        var bigDecimal = BigDecimal(this.toString())
        bigDecimal = bigDecimal.setScale(decimalPlace, RoundingMode.HALF_EVEN)
        return bigDecimal.toFloat()
    }

    fun randomString(): String = UUID.randomUUID().toString().substring(0, Random.nextInt(10, 25))

    val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun randomName(length: Int = Random.nextInt(5, 25)) = ThreadLocalRandom.current()
        .ints(length.toLong(), 0, charPool.size)
        .asSequence()
        .map(charPool::get)
        .joinToString("")
}
