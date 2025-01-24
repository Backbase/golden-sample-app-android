package com.bartburg.contacts_journey.domain.model

import dev.drewhamilton.poko.Poko

@Poko
open class ContactModel private constructor(
    val id: String,
    val country: String, // ISO format
    val name: String,
    val alias: String?,
    val address: AddressModel,
    val accounts: List<AccountModel>,
) {
    /**
     * A Builder for [ContactModel]
     */
    class Builder {
        /**
         * See [ContactModel.id]
         */
        lateinit var id: String
        lateinit var country: String
        lateinit var name: String
        var alias: String? = null
        lateinit var address: AddressModel
        var accounts: List<AccountModel> = emptyList()

        /**
         * Builds an instance of [ContactModel]
         */
        @Suppress("ConstructorParameterNaming")
        fun build() = ContactModel(
            id = id,
            country = country,
            name = name,
            alias = alias,
            address = address,
            accounts = accounts,
        )
    }
}

/**
 * DSL to create [ContactModel].
 *
 * @see [ContactModel]
 */
@Suppress("FunctionName")
fun ContactModel(initializer: ContactModel.Builder.() -> Unit): ContactModel {
    return ContactModel.Builder().apply(initializer).build()
}