package com.bartburg.contacts_journey.domain.model

import dev.drewhamilton.poko.Poko

@Poko
class AddressModel private constructor(
    val streetAndNumber: String,
    val additionalLine1: String?,
    val additionalLine2: String?,
    val postalCode: String,
    val city: String,
    val stateOrArea: String?,
    val country: String, // ISO format
) {
    class Builder {
        lateinit var streetAndNumber: String
        var additionalLine1: String? = null
        var additionalLine2: String? = null
        lateinit var postalCode: String
        lateinit var city: String
        var stateOrArea: String? = null
        lateinit var country: String

        fun build() = AddressModel(
            streetAndNumber = streetAndNumber,
            additionalLine1 = additionalLine1,
            additionalLine2 = additionalLine2,
            postalCode = postalCode,
            city = city,
            stateOrArea = stateOrArea,
            country = country,
        )
    }
}

fun AddressModel(initializer: AddressModel.Builder.() -> Unit): AddressModel {
    return AddressModel.Builder().apply(initializer).build()
} 