package com.backbase.accounts_journey.presentation.accountlist.ui

import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.generator.AccountSummaryGenerator
import com.backbase.accounts_journey.presentation.accountlist.mapper.AccountUiMapper
import com.backbase.android.test_data.CoroutineTest
import com.backbase.android.test_data.StringGenerator.randomString
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AccountListViewModelTest : CoroutineTest {

    override lateinit var testScope: TestScope
    override lateinit var testDispatcherProvider: TestDispatcher

    private val accountsUseCase: AccountsUseCase = mockk()

    private val viewModel by lazy {
        AccountListViewModel(
            useCase = accountsUseCase,
            mapper = AccountUiMapper(mockk(relaxed = true)),
            defaultDispatcher = testDispatcherProvider
        )
    }

    @Test
    fun `should get account summary when success`() = runTest {
        val accountSummary = AccountSummaryGenerator.randomAccountSummaryBuilder().build()
        coEvery {
            accountsUseCase.getAccountSummary(true)
        } returns Result.success(accountSummary)

        viewModel.onEvent(AccountListEvent.OnGetAccounts)

        val uiState = viewModel.uiState.value
        then(uiState.accountSummary).isNotEmpty
    }

    @Test
    fun `should get error when failed`() = runTest {
        coEvery { accountsUseCase.getAccountSummary(true) } returns Result.failure(
            FailedGetDataException()
        )

        viewModel.onEvent(AccountListEvent.OnGetAccounts)

        val uiState = viewModel.uiState.value
        then(uiState.error).isNotNull
    }

    @Test
    fun `should get account summary when refresh`() = runTest {
        val accountSummary = AccountSummaryGenerator.randomAccountSummaryBuilder().build()

        coEvery {
            accountsUseCase.getAccountSummary(false)
        } returns Result.success(accountSummary)

        viewModel.onEvent(AccountListEvent.OnRefresh)

        val uiState = viewModel.uiState.value
        then(uiState.accountSummary).isNotEmpty
    }

    @Test
    fun `should get account summary when search`() = runTest {
        val query = randomString()

        val accountSummary = AccountSummaryGenerator.randomAccountSummaryBuilder {
            currentAccounts = AccountSummaryGenerator.randomCurrentAccounts {
                this.products = listOf(AccountSummaryGenerator.randomCurrentAccount { this.displayName = query }.build())
            }.build()
        }.build()

        coEvery {
            accountsUseCase.getAccountSummary()
        } returns Result.success(accountSummary)

        viewModel.onEvent(AccountListEvent.OnSearch(query))

        val uiState = viewModel.uiState.value
        // Header + List Item  = 2
        then(uiState.accountSummary.size).isEqualTo(2)
    }

    @Test
    fun `should get empty account summary when nothing found`() = runTest {
        val query = randomString()
        val accountSummary = AccountSummaryGenerator.randomAccountSummaryBuilder {
            currentAccounts = AccountSummaryGenerator.randomCurrentAccounts {
                this.products = listOf(AccountSummaryGenerator.randomCurrentAccount { this.displayName = query }.build())
            }.build()
        }.build()
        coEvery {
            accountsUseCase.getAccountSummary()
        } returns Result.success(accountSummary)

        accountSummary.currentAccounts?.products?.forEach {
            println(it.displayName)
        }

        viewModel.onEvent(AccountListEvent.OnSearch(randomString()))

        val uiState = viewModel.uiState.value
        then(uiState.accountSummary.size).isZero
    }
}
