package com.backbase.accounts_journey.domain.model.account_summary

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * Supported Masking/unMasking for Account Number on Account Detail Screen.
 * ## Important
 * Do not use this enum or its cases in exhaustive searches, at least not without an else branch.
 * New cases will be added to this enum in the future.
 *
 * @param attributeName the name of the DBS attribute
 */

enum class MaskableAttribute(val attributeName: String) {

    /**
     * IBAN
     */
    IBAN(attributeName = "IBAN"),

    /**
     * BBAN
     */
    BBAN(attributeName = "BBAN"),

    /**
     * Number
     */
    Number(attributeName = "Number")
}
