package com.backbase.accounts_use_case.generator

import com.backbase.android.client.gen2.arrangementclient2.model.AccountArrangementItem

object AccountArrangementItemGenerator {

    fun generateAccountArrangement(
        id: String = "",
        currency: String = "",
        productId: String = ""
    ): AccountArrangementItem {
        return AccountArrangementItem {
            this.id = id
            this.currency = currency
            this.productId = productId
        }
    }
}
