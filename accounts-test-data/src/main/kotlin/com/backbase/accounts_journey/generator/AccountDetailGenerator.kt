package com.backbase.accounts_journey.generator

import com.backbase.accounts_journey.domain.model.account_detail.AccountDetail
import com.backbase.android.test_data.StringGenerator

/**
 * A random AccountDetail generator. It is not fully implemented, but it gives you an idea how to randomize the data.
 */
object AccountDetailGenerator {

    fun generateAccountDetail(
        id: String = StringGenerator.randomString(),
        productId: String = StringGenerator.randomString(),
        currency: String = StringGenerator.generateRandomCurrency()
    ): AccountDetail {
        return AccountDetail {
            this.id = id
            this.productId = productId
            this.currency = currency
        }
    }
}
