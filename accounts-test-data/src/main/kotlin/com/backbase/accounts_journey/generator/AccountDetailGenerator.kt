package com.backbase.accounts_journey.generator

import com.backbase.android.client.gen2.arrangementclient2.model.AccountArrangementItem
import com.backbase.android.test_data.StringGenerator

/**
 * A random AccountDetail generator. It is not fully implemented, but it gives you an idea how to randomize the data.
 */
object AccountDetailGenerator {

    fun generateAccountDetail(
        id: String = StringGenerator.randomString(),
        productId: String = StringGenerator.randomString(),
        currency: String = StringGenerator.generateRandomCurrency()
    ): AccountArrangementItem {
        return AccountArrangementItem {
            this.id = id
            this.productId = productId
            this.currency = currency
        }
    }
}
