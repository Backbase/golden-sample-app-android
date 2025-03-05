package com.backbase.accounts_journey.presentation.accountdetail.ui

import com.backbase.accounts_journey.CoroutineTest
import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.data.usecase.AccountDetailUseCase
import com.backbase.accounts_journey.data.usecase.Params
import com.backbase.accounts_journey.generator.AccountDetailGenerator.generateAccountDetail
import com.backbase.accounts_journey.generator.StringGenerator
import com.backbase.accounts_journey.presentation.accountdetail.mapper.AccountDetailUiMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AccountDetailViewModelTest : CoroutineTest {

    override lateinit var testScope: TestScope

    private val accountDetailUseCase: AccountDetailUseCase = mockk()

    private val viewModel by lazy {
        AccountDetailViewModel(
            useCase = accountDetailUseCase,
            mapper = AccountDetailUiMapper(mockk(relaxed = true)),
            defaultDispatcher = Dispatchers.Unconfined
        )
    }

    @Test
    fun `should get account detail when success`() = runTest {
        val id = StringGenerator.randomString()
        val params = Params({ this.id = id })
        coEvery {
            accountDetailUseCase.getAccountDetail(params)
        } returns Result.success(generateAccountDetail(id = id))

        viewModel.onEvent(AccountDetailEvent.OnGetAccountDetail(id))

        val uiState = viewModel.uiState.value
        then(uiState.accountDetail?.id).isEqualTo(id)
    }

    @Test
    fun `should get error detail when failed`() = runTest {
        coEvery {
            accountDetailUseCase.getAccountDetail(any())
        } returns Result.failure(FailedGetDataException())

        viewModel.onEvent(AccountDetailEvent.OnGetAccountDetail(""))

        val uiState = viewModel.uiState.value
        then(uiState.error).isNotNull
    }
}
