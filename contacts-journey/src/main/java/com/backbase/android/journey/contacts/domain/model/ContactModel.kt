package com.backbase.android.journey.contacts.domain.model

typealias DefaultContactModel = ContactModel<Unit, Unit>

data class ContactModel<ContactExtension, AccountExtension>(
    val id: String,
    val name: String,
    val alias: String? = null,
    val accounts: List<AccountModel<AccountExtension>>,
    val streetAndNumber: String? = null,
    val postalCode: String? = null,
    val city: String? = null,
    val country: String? = null, // ISO format
    val stateOrArea: String? = null,
    val additionalLine1: String? = null,
    val additionalLine2: String? = null,
    val extension: ContactExtension? = null
)