package com.backbase.android.journey.contacts.domain.model

typealias DefaultContactModel = ContactModel<Unit, Unit>

data class ContactModel<ContactExtension, AccountExtension>(
    val id: String,
    val name: String,
    val alias: String?,
    val accounts: List<AccountModel<AccountExtension>>,
    val streetAndNumber: String,
    val postalCode: String,
    val city: String,
    val country: String, // ISO format
    val stateOrArea: String? = null,
    val additionalLine1: String? = null,
    val additionalLine2: String? = null,
    val extension: ContactExtension? = null
)
