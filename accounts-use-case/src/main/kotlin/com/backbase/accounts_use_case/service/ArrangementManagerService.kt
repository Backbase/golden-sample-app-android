package com.backbase.accounts_use_case.service

import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.data.usecase.AccountDetailUseCase
import com.backbase.android.client.gen2.arrangementclient2.model.AccountArrangementItem
import com.backbase.android.client.gen2.arrangementclient2.model.ProductSummary
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.appendPathSegments

class ArrangementManagerService(private val httpClient: HttpClient) {

    suspend fun getProductSummary(): ProductSummary {
        val response: HttpResponse = httpClient.get(PRODUCT_SUMMARY_ENDPOINT)
        return when (response.status.value) {
            in 200..299 -> {
                response.body()
            }

            in 300..399 -> {
                println("e-tag")
                response.body()
            }

            in 400..499 -> {
                throw FailedGetDataException("SNAFU")
            }

            else -> throw FailedGetDataException("Unknown error")
        }
    }

    suspend fun getAccountDetail(params: AccountDetailUseCase.Params): AccountArrangementItem {
        val response: HttpResponse = httpClient.get {
            url {
                appendPathSegments(PRODUCT_DETAILS_ENDPOINT, params.id)
            }
        }
        return when (response.status.value) {
            in 200..299 -> {
                response.body()
            }

            in 300..399 -> {
                println("e-tag")
                response.body()
            }

            in 400..499 -> {
                throw FailedGetDataException("SNAFU")
            }

            else -> throw FailedGetDataException("Unknown error")
        }
    }

    private companion object {
        const val PRODUCT_SUMMARY_ENDPOINT: String = "api/arrangement-manager/client-api/v2/productsummary"
        const val PRODUCT_DETAILS_ENDPOINT: String = "api/arrangement-manager/client-api/v2/arrangements"
    }
}
