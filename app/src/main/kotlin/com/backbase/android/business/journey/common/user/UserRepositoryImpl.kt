package com.backbase.android.business.journey.common.user

import androidx.annotation.VisibleForTesting
import com.backbase.android.core.errorhandling.EncryptionException
import com.backbase.android.plugins.storage.StorageComponent

/**
 * user repository implementation
 */
internal class UserRepositoryImpl(
    private val bbStorage: StorageComponent
) : UserRepository {

    @VisibleForTesting
    override fun saveUserInfo(user: User) {
        user.apply {
            serviceAgreementId?.let { bbStorage.setItem(KEY_SERVICE_AGREEMENT_ID, it) }
            serviceAgreementName?.let { bbStorage.setItem(KEY_SERVICE_AGREEMENT_NAME, it) }
            userContext?.let { bbStorage.setItem(KEY_USER_CONTEXT, it) }
            bbStorage.setItem(KEY_BIOMETRICS, isBiometricEnabled.toString())
            bbStorage.setItem(KEY_SETUP_COMPLETE, isSetupCompleted.toString())
            bbStorage.setItem(KEY_CONTEXT_SIZE, serviceAgreementSize.toString())
        }
    }

    @VisibleForTesting
    override fun getUserInfo(): User {
        return User(
            serviceAgreementId = getItemWithSanityCheck(KEY_SERVICE_AGREEMENT_ID),
            serviceAgreementName = getItemWithSanityCheck(KEY_SERVICE_AGREEMENT_NAME),
            userContext = getItemWithSanityCheck(KEY_USER_CONTEXT),
            isBiometricEnabled = getItemWithSanityCheck(KEY_BIOMETRICS)?.toBoolean()
                ?: DEFAULT_BIOMETRIC_VALUE,
            isSetupCompleted = getItemWithSanityCheck(KEY_SETUP_COMPLETE)?.toBoolean()
                ?: DEFAULT_SETUP_COMPLETE,
            serviceAgreementSize = getItemWithSanityCheck(KEY_CONTEXT_SIZE)?.toInt() ?: -1
        )
    }

    @VisibleForTesting
    override fun clearUserInfo() {
        bbStorage.run {
            removeItems(
                KEY_SERVICE_AGREEMENT_ID,
                KEY_SERVICE_AGREEMENT_NAME,
                KEY_USER_CONTEXT,
                KEY_BIOMETRICS,
                KEY_SETUP_COMPLETE,
                KEY_CONTEXT_SIZE,
                KEY_USERNAME,
                KEY_PASSWORD
            )
        }
    }

    @VisibleForTesting
    override fun clearServiceAgreement() {
        bbStorage.run {
            removeItems(
                KEY_SERVICE_AGREEMENT_ID,
                KEY_SERVICE_AGREEMENT_NAME
            )
        }
    }

    override fun isUsernameSaved(): Boolean = getItemWithSanityCheck(KEY_USERNAME) != null

    override fun isUserBiometricRegistered(): Boolean =
        getItemWithSanityCheck(KEY_BIOMETRICS)?.toBoolean() ?: DEFAULT_BIOMETRIC_VALUE

    override fun saveEncryptedCredentials(username: CharArray, password: CharArray) {
        saveUsername(username)
        bbStorage.setItem(KEY_PASSWORD, password.joinToString(""))
    }

    override fun isServiceAgreementSelected(): Boolean =
        !getItemWithSanityCheck(KEY_SERVICE_AGREEMENT_ID).isNullOrEmpty()

    override fun saveUsername(username: CharArray) =
        bbStorage.setItem(KEY_USERNAME, username.joinToString(""))

    override fun isSetupCompleted(): Boolean =
        getItemWithSanityCheck(KEY_SETUP_COMPLETE)?.toBoolean() ?: DEFAULT_SETUP_COMPLETE

    override fun getUsername(): CharArray? =
        getItemWithSanityCheck(KEY_USERNAME)?.toCharArray()

    override fun getPassword(): CharArray? = getItemWithSanityCheck(KEY_PASSWORD)?.toCharArray()

    // Get item from storage, clearing all data and throwing an exception if data is corrupted
    @Suppress("SwallowedException")
    private fun getItemWithSanityCheck(key: String): String? =
        try {
            bbStorage.getItem(key)
        } catch (e: EncryptionException) {
            clearUserInfo()
            throw UserRepository.CorruptedUserDataException()
        }

    internal companion object {
        const val KEY_BIOMETRICS = "KEY_BIOMETRICS"
        const val KEY_SERVICE_AGREEMENT_ID = "SERVICE_AGREEMENT_ID"
        const val KEY_SERVICE_AGREEMENT_NAME = "SERVICE_AGREEMENT_NAME"
        const val KEY_USER_CONTEXT = "KEY_USER_CONTEXT"
        const val KEY_SETUP_COMPLETE = "KEY_SETUP_COMPLETE"
        const val KEY_CONTEXT_SIZE = "KEY_CONTEXT_SIZE"
        const val KEY_USERNAME = "KEY_USERNAME"
        const val KEY_PASSWORD = "KEY_PASSWORD"
        const val DEFAULT_BIOMETRIC_VALUE = false
        const val DEFAULT_SETUP_COMPLETE = false
    }
}

private fun StorageComponent.removeItems(vararg keys: String) =
    keys.forEach(::removeItem)
