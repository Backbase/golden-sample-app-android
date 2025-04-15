package com.backbase.android.journey.contacts.data

import com.backbase.android.client.gen2.contactmanagerclient2.model.AccountInformation
import com.backbase.android.client.gen2.contactmanagerclient2.model.ContactGetResponseBody
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import java.math.BigDecimal

data class ContactModelExtension(
    val lastPaymentAmount: BigDecimal?
)

// Extension functions for mapping between domain and data models
fun ContactGetResponseBody.toContactModel(): ContactModel {
    return ContactModel(
        id = this@toContactModel.id,
        name = this@toContactModel.name,
        alias = this@toContactModel.alias,
        accounts = this@toContactModel.accounts.map {
            it.toAccountModel()
        },
        country = this@toContactModel.country ?: "",
        streetAndNumber = this@toContactModel.streetName ?: "",
        postalCode = this@toContactModel.postCode ?: "",
        city = this@toContactModel.town ?: "",
        additionalLine1 = null,
        additionalLine2 = null,
        stateOrArea = null,
        extension = this@toContactModel
    )
}

fun ContactModel.toContactGetResponseBody(): ContactGetResponseBody {
    return ContactGetResponseBody {
        id = this@toContactGetResponseBody.id
        name = this@toContactGetResponseBody.name
        accounts = emptyList()
    }
}

fun AccountInformation.toAccountModel() =
    AccountModel(
        bankCountry = this.bankCountry,
        accountName = this.name,
        alias = this.alias,
        iban = this.IBAN,
        accountNumber = this.accountNumber!!,
        bankBranchCode = this.bankCode,
        accountType = this.accountType,
        bankName = this.bankName
    )
