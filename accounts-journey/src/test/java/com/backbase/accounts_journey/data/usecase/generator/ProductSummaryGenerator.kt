package com.backbase.accounts_journey.data.usecase.generator

import com.backbase.android.client.gen2.arrangementclient2.model.ProductSummary

object ProductSummaryGenerator {

    fun generateProductSummary(): ProductSummary {
        return ProductSummary { customProductKinds = emptyList() }
    }
}
