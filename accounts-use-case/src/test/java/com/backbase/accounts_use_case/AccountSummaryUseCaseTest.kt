package com.backbase.accounts_use_case

import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.common.NoInternetException
import com.backbase.accounts_journey.common.NoResponseException
import com.backbase.accounts_use_case.generator.ProductSummaryGenerator
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApiParams
import com.backbase.android.client.gen2.arrangementclient2.model.ProductSummary
import com.backbase.android.clients.common.CallResult
import com.backbase.android.core.errorhandling.ErrorCodes
import com.backbase.android.utils.net.response.Response
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class AccountSummaryUseCaseTest {

    @Test
    fun `should get AccountSummary when success`() = runTest {
        val productSummaryApi = mockk<ProductSummaryApi>()
        val callResult = CallResult.Success(
            data = ProductSummaryGenerator.generateProductSummary()
        )
        val useCase = AccountSummaryUseCaseImpl(productSummaryApi, Dispatchers.Unconfined)
        every {
            productSummaryApi.getProductSummary(ProductSummaryApiParams.GetProductSummary { })
                .parseExecute()
        } returns callResult

        val actual = useCase.getAccountSummary(false)

        then(actual.isSuccess).isTrue
        actual.onSuccess { domain ->
            then(domain).isNotNull
        }
    }

    @Test
    fun `should get AccountSummary when use cache`() = runTest {
        val productSummaryApi = mockk<ProductSummaryApi>()
        val callResult = CallResult.Success(
            data = ProductSummaryGenerator.generateProductSummary()
        )
        val useCase = AccountSummaryUseCaseImpl(productSummaryApi, Dispatchers.Unconfined)

        every {
            productSummaryApi.getProductSummary(ProductSummaryApiParams.GetProductSummary { })
                .parseExecute()
        } returns callResult

        useCase.getAccountSummary(true)
        useCase.getAccountSummary(true)

        verify(exactly = 1) { productSummaryApi.getProductSummary(ProductSummaryApiParams.GetProductSummary { }) }
    }

    @Test
    fun `should get FailedDataException when error code is 500`() = runTest {
        val productSummaryApi = mockk<ProductSummaryApi>()
        val callResult = CallResult.Error<ProductSummary>(
            errorResponse = Response(500, "Internal Server Error")
        )
        val useCase = AccountSummaryUseCaseImpl(productSummaryApi, Dispatchers.Unconfined)

        every {
            productSummaryApi.getProductSummary(ProductSummaryApiParams.GetProductSummary { })
                .parseExecute()
        } returns callResult

        val actual = useCase.getAccountSummary(false)

        then(actual.isFailure).isTrue
        actual.onFailure { throwable ->
            then(throwable).isInstanceOf(FailedGetDataException::class.java)
        }
    }

    @Test
    fun `should get NoInternetException when there is no internet`() = runTest {
        val productSummaryApi = mockk<ProductSummaryApi>()
        val callResult = CallResult.Error<ProductSummary>(
            errorResponse = Response(ErrorCodes.NO_INTERNET.code, "No internet")
        )
        val useCase = AccountSummaryUseCaseImpl(productSummaryApi, Dispatchers.Unconfined)

        every {
            productSummaryApi.getProductSummary(ProductSummaryApiParams.GetProductSummary { })
                .parseExecute()
        } returns callResult

        val actual = useCase.getAccountSummary(false)

        then(actual.isFailure).isTrue
        actual.onFailure { throwable ->
            then(throwable).isInstanceOf(NoInternetException::class.java)
        }
    }

    @Test
    fun `should get NoResponseException when there is nothing happens`() = runTest {
        val productSummaryApi = mockk<ProductSummaryApi>()
        val callResult = CallResult.None<ProductSummary>()
        val useCase = AccountSummaryUseCaseImpl(productSummaryApi, Dispatchers.Unconfined)

        every {
            productSummaryApi.getProductSummary(ProductSummaryApiParams.GetProductSummary { })
                .parseExecute()
        } returns callResult

        val actual = useCase.getAccountSummary(false)

        then(actual.isFailure).isTrue
        actual.onFailure { throwable ->
            then(throwable).isInstanceOf(NoResponseException::class.java)
        }
    }
}
