package com.backbase.accounts_use_case

import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.common.NoInternetException
import com.backbase.accounts_journey.common.NoResponseException
import com.backbase.accounts_journey.data.usecase.Params
import com.backbase.accounts_use_case.generator.AccountArrangementItemGenerator
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApi
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApiParams
import com.backbase.android.client.gen2.arrangementclient2.model.AccountArrangementItem
import com.backbase.android.clients.common.CallResult
import com.backbase.android.core.errorhandling.ErrorCodes
import com.backbase.android.test_data.CoroutineTest
import com.backbase.android.test_data.StringGenerator.randomString
import com.backbase.android.utils.net.response.Response
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AccountDetailUseCaseTest : CoroutineTest {

    override lateinit var testScope: TestScope
    override lateinit var testDispatcherProvider: TestDispatcher

    @Test
    fun `should get AccountDetail when success`() = runTest {
        val id = randomString()
        val arrangementsApi = mockk<ArrangementsApi>()
        val callResult = CallResult.Success(
            data = AccountArrangementItemGenerator.generateAccountArrangement(id = id)
        )
        val useCase = AccountDetailUseCaseImpl(arrangementsApi, testDispatcherProvider)

        every {
            arrangementsApi.getArrangementById(
                ArrangementsApiParams.GetArrangementById {
                    this.arrangementId = id
                }
            ).parseExecute()
        } returns callResult

        val actual = useCase.getAccountDetail(Params { this.id = id })

        then(actual.isSuccess).isTrue
        actual.onSuccess { accountDetail ->
            then(accountDetail.id).isEqualTo(id)
        }
    }

    @Test
    fun `should get FailedGetDataException when error code is 500`() = runTest {
        val id = randomString()
        val arrangementsApi = mockk<ArrangementsApi>()
        val callResult = CallResult.Error<AccountArrangementItem>(
            errorResponse = Response(500, "Internal Server Error")
        )
        val useCase = AccountDetailUseCaseImpl(arrangementsApi, testDispatcherProvider)

        every {
            arrangementsApi.getArrangementById(
                ArrangementsApiParams.GetArrangementById {
                    this.arrangementId = id
                }
            ).parseExecute()
        } returns callResult

        val actual = useCase.getAccountDetail(Params { this.id = id })

        then(actual.isFailure).isTrue
        actual.onFailure { exception ->
            then(exception).isInstanceOf(FailedGetDataException::class.java)
        }
    }

    @Test
    fun `should get NoInternetException when there is no internet`() = runTest {
        val id = randomString()
        val arrangementsApi = mockk<ArrangementsApi>()
        val callResult = CallResult.Error<AccountArrangementItem>(
            errorResponse = Response(ErrorCodes.NO_INTERNET.code, "No internet")
        )
        val useCase = AccountDetailUseCaseImpl(arrangementsApi, testDispatcherProvider)

        every {
            arrangementsApi.getArrangementById(
                ArrangementsApiParams.GetArrangementById {
                    this.arrangementId = id
                }
            ).parseExecute()
        } returns callResult

        val actual = useCase.getAccountDetail(Params { this.id = id })

        then(actual.isFailure).isTrue
        actual.onFailure { exception ->
            then(exception).isInstanceOf(NoInternetException::class.java)
        }
    }

    @Test
    fun `should get NoResponseException when there is no internet`() = runTest {
        val id = randomString()
        val arrangementsApi = mockk<ArrangementsApi>()
        val callResult = CallResult.None<AccountArrangementItem>()
        val useCase = AccountDetailUseCaseImpl(arrangementsApi, testDispatcherProvider)

        every {
            arrangementsApi.getArrangementById(
                ArrangementsApiParams.GetArrangementById {
                    this.arrangementId = id
                }
            ).parseExecute()
        } returns callResult

        val actual = useCase.getAccountDetail(Params { this.id = id })

        then(actual.isFailure).isTrue
        actual.onFailure { exception ->
            then(exception).isInstanceOf(NoResponseException::class.java)
        }
    }
}
