package com.backbase.android.business.journey.common.user

data class User(
    val isSetupCompleted: Boolean = false,
    val serviceAgreementId: String? = null,
    val serviceAgreementName: String? = null,
    val userContext: String? = null,
    val isBiometricEnabled: Boolean = false,
    val serviceAgreementSize: Int = 0
)
