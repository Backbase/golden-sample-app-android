package com.backbase.android.journey.contacts.data

import com.backbase.android.client.gen2.contactmanagerclient2.model.AccountInformation
import com.backbase.android.client.gen2.contactmanagerclient2.model.ContactGetResponseBody
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.model.DefaultContactModel
import java.util.UUID

interface MockContactsCreator<ContactExtension, AccountExtension> {
    fun createMockContactGetResponseBody(amount: Int = 20): List<ContactGetResponseBody>
    fun createMockContactsList(amount: Int = 20): List<ContactModel<ContactExtension, AccountExtension>>
}

object MockContactsCreatorImpl: MockContactsCreator<Unit, Unit> {
    override fun createMockContactGetResponseBody(amount: Int): List<ContactGetResponseBody> {
        val contacts = mutableListOf<ContactGetResponseBody>()
        repeat(amount) { index ->
            val contactCounter = index + 1
            
            val contactId = UUID.randomUUID().toString()
            val contactName = "Contact $contactCounter"
            val contactAlias = "C$contactCounter"
            val contactCountry = if (index % 2 == 0) "US" else "CA"
            val street = "${index * 10} Main St"
            val city = "City $contactCounter"
            val zipCode = "${90200 + index}"

            val numAccounts = if (index % 3 == 0) 2 else 1
            val accounts = mutableListOf<AccountInformation>()
            repeat(numAccounts) { accountIndex ->
                val accountNumber = "${1234567890 + index * 10 + accountIndex}"
                val accountName = "$contactName's Account $accountIndex"
                val bankName = "Bank ${index + accountIndex}"
                val bankCode = "B${index + accountIndex}"
                val bankCountry = if (index % 2 == 0) "US" else "CA"
                val accountType = if (accountIndex % 2 == 0) "Checking" else "Savings"
                val iban = "${bankCountry}${accountNumber}"
                val alias = if (accountIndex % 2 == 0) "Checking" else "Savings"

                accounts.add(
                    AccountInformation.Builder().apply {
                        this.accountNumber = accountNumber
                        this.name = accountName
                        this.bankName = bankName
                        this.bankCode = bankCode
                        this.bankCountry = bankCountry
                        this.accountType = accountType
                        this.IBAN = iban
                        this.alias = alias
                    }.build()
                )
            }

            contacts.add(
                ContactGetResponseBody.Builder().apply {
                    this.id = contactId
                    this.name = contactName
                    this.alias = contactAlias
                    this.country = contactCountry
                    this.accounts = accounts
                    this.streetName = street
                    this.town = city
                    this.postCode = zipCode
                }.build()
            )
        }
        return contacts
    }

    override fun createMockContactsList(amount: Int): List<DefaultContactModel> {
        return createMockContactGetResponseBody(amount).map { it.toContactModel() }
    }
}