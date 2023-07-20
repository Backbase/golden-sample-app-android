package com.backbase.golden_sample_app.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    var isSetupCompleted: Boolean = false,
    var serviceAgreementId: String? = null,
    var serviceAgreementName: String? = null,
    var userContext: String? = null,
    var isBiometricEnabled: Boolean = false,
    var serviceAgreementSize: Int = 0
) : Parcelable {

    /**
     * A builder for this configuration class
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function
     */
    class Builder {
        var isSetupCompleted: Boolean = false
        var serviceAgreementId: String? = null
        var serviceAgreementName: String? = null
        var userContext: String? = null
        var isBiometricEnabled: Boolean = false
        var serviceAgreementSize: Int = 0

        fun build() = User(
            isSetupCompleted,
            serviceAgreementId,
            serviceAgreementName,
            userContext,
            isBiometricEnabled,
            serviceAgreementSize,
        )
    }
}

/**
 * DSL to create [User]
 */
@Suppress("FunctionName") // DSL initializer
@JvmSynthetic // Hide from Java callers who should use Builder
fun User(block: User.Builder.() -> Unit) = User.Builder().apply(block).build()