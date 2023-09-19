package com.backbase.accounts_journey.domain.model.product_summary

import android.os.Parcelable
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param alias The name that can be assigned by the user to label the arrangement
 * @param visible User indicator whether to show or hide the arrangement on the front end
 * @param favorite Indication whether an arrangement can be favorable for a user
 * @param additions Extra parameters
 */
@Poko
@Parcelize
class UserPreferences internal constructor(
    val alias: String?,
    val visible: Boolean?,
    val favorite: Boolean?,
    val additions: Map<String, String>?
) : Parcelable {

    /**
     * A builder for this configuration class
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function
     */
    class Builder {
        /**
         * See [UserPreferences.alias]
         */
        @set:JvmSynthetic
        var alias: String? = null

        /**
         * See [UserPreferences.visible]
         */
        @set:JvmSynthetic
        var visible: Boolean? = null

        /**
         * See [UserPreferences.favorite]
         */
        @set:JvmSynthetic
        var favorite: Boolean? = null

        /**
         * See [UserPreferences.additions]
         */
        @set:JvmSynthetic
        var additions: Map<String, String>? = null

        /**
         * See [UserPreferences.alias]
         */
        fun setAlias(alias: String?) = apply {
            this.alias = alias
        }

        /**
         * See [UserPreferences.visible]
         */
        fun setVisible(visible: Boolean?) = apply {
            this.visible = visible
        }

        /**
         * See [UserPreferences.favorite]
         */
        fun setFavorite(favorite: Boolean?) = apply {
            this.favorite = favorite
        }

        /**
         * See [UserPreferences.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Build an instance of [UserPreferences]
         */
        fun build() = UserPreferences(
            alias,
            visible,
            favorite,
            additions
        )
    }
}

/**
 * DSL to create [UserPreferences]
 */
@JvmSynthetic
fun UserPreferences(block: UserPreferences.Builder.() -> Unit) = UserPreferences.Builder().apply(block).build()
