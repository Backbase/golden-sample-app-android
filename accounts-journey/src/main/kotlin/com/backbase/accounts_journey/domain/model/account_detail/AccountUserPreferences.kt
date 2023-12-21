package com.backbase.accounts_journey.domain.model.account_detail

import dev.drewhamilton.poko.Poko

/**
 * Created by Backbase R&D B.V. on 19/05/2021.
 *
 * User defined preferences for a specific arrangement. Note: each user might have different preferences.
 * @param arrangementId The unique Arrangement identifier related to these user preferences.
 * @param alias The name that can be assigned by the user to label the arrangement.
 * @param visible User indicator whether to show or hide the arrangement on the front end.
 * @param favorite Indication whether an arrangement can be favorable for a user.
 * @param additions Additional information.
 */
@Poko
class AccountUserPreferences private constructor(
    val arrangementId: String,
    val alias: String?,
    val visible: Boolean?,
    val favorite: Boolean?,
    val additions: Map<String, String>?
) {

    /**
     * A builder for this configuration class
     */
    class Builder {

        /**
         * See [AccountUserPreferences.arrangementId]
         */
        lateinit var arrangementId: String

        /**
         * See [AccountUserPreferences.alias]
         */
        var alias: String? = null

        /**
         * See [AccountUserPreferences.visible]
         */
        var visible: Boolean? = null

        /**
         * See [AccountUserPreferences.favorite]
         */
        var favorite: Boolean? = null

        /**
         * See [AccountUserPreferences.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * Build an instance of [AccountUserPreferences]
         */
        fun build() = AccountUserPreferences(
            arrangementId,
            alias,
            visible,
            favorite,
            additions
        )
    }
}

/**
 * DSL to create [AccountUserPreferences]
 */
@JvmSynthetic
fun AccountUserPreferences(block: AccountUserPreferences.Builder.() -> Unit) =
    AccountUserPreferences.Builder().apply(block).build()
