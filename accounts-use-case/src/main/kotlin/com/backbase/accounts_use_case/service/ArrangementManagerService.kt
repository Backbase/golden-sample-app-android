package com.backbase.accounts_use_case.service

import com.backbase.android.client.gen2.arrangementclient2.model.ProductSummary
import retrofit2.Response
import retrofit2.http.GET

interface ArrangementManagerService {

    @GET("api/arrangement-manager/client-api/v2/productsummary")
    suspend fun productSummary(): Response<ProductSummary>
}
