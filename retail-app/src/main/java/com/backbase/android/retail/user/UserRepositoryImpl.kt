package com.backbase.golden_sample_app.user

import androidx.annotation.VisibleForTesting
import com.backbase.android.business.journey.common.user.User
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.secure.storage.ReadValueResult
import com.backbase.android.secure.storage.SecureStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * user repository implementation
 */
internal class UserRepositoryImpl(
    private val secureStorage: SecureStorage,
    private val coroutineScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {

    @VisibleForTesting
    override fun saveUserInfo(user: User) {
        coroutineScope.launch(dispatcher) {
            user.apply {
                secureStorage.run {
                    serviceAgreementId?.let { storeValue(KEY_SERVICE_AGREEMENT_ID, it) }
                    serviceAgreementName?.let { storeValue(KEY_SERVICE_AGREEMENT_NAME, it) }
                    userContext?.let { storeValue(KEY_USER_CONTEXT, it) }
                    storeValue(KEY_BIOMETRICS, isBiometricEnabled.toString())
                    storeValue(KEY_SETUP_COMPLETE, isSetupCompleted.toString())
                    storeValue(KEY_CONTEXT_SIZE, serviceAgreementSize.toString())
                }
            }
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
        coroutineScope.launch(dispatcher) {
            secureStorage.deleteAllWithKeyPrefix(PREFIX)
        }
    }

    @VisibleForTesting
    override fun clearServiceAgreement() {
        coroutineScope.launch(dispatcher) {
            secureStorage.run {
                deleteValue(KEY_SERVICE_AGREEMENT_ID)
            }
        }
    }

    override fun isUsernameSaved(): Boolean = getItemWithSanityCheck(KEY_USERNAME) != null

    override fun isUserBiometricRegistered(): Boolean =
        getItemWithSanityCheck(KEY_BIOMETRICS)?.toBoolean() ?: DEFAULT_BIOMETRIC_VALUE

    override fun saveEncryptedCredentials(username: CharArray, password: CharArray) {
        saveUsername(username)
        coroutineScope.launch(dispatcher) {
            secureStorage.storeValue(KEY_PASSWORD, password.joinToString(""))
        }
    }

    override fun isServiceAgreementSelected(): Boolean =
        !getItemWithSanityCheck(KEY_SERVICE_AGREEMENT_ID).isNullOrEmpty()

    override fun saveUsername(username: CharArray) {
        coroutineScope.launch(dispatcher) {
            secureStorage.storeValue(KEY_USERNAME, username.joinToString(""))
        }
    }

    override fun isSetupCompleted(): Boolean =
        getItemWithSanityCheck(KEY_SETUP_COMPLETE)?.toBoolean() ?: DEFAULT_SETUP_COMPLETE

    override fun getUsername(): CharArray? =
        getItemWithSanityCheck(KEY_USERNAME)?.toCharArray()

    override fun getPassword(): CharArray? = getItemWithSanityCheck(KEY_PASSWORD)?.toCharArray()

    // Get item from storage, clearing all data and throwing an exception if data is corrupted
    @Suppress("SwallowedException")
    private fun getItemWithSanityCheck(key: String): String? {
        var value: String? = null
        coroutineScope.launch(dispatcher) {
            println(key)
            when (val result = secureStorage.readValue(key)) {
                is ReadValueResult.Found -> value = result.value
                ReadValueResult.EmptyKey -> println("key is empty")
                ReadValueResult.NotFound -> println("$key not found")
                is ReadValueResult.Error -> {
                    clearUserInfo()
                    throw UserRepository.CorruptedUserDataException()
                }
            }
        }
        return value
    }

    internal companion object {
        private const val PREFIX = "KEY"
        const val KEY_BIOMETRICS = "${PREFIX}_BIOMETRICS"
        const val KEY_SERVICE_AGREEMENT_ID = "${PREFIX}_SERVICE_AGREEMENT_ID"
        const val KEY_SERVICE_AGREEMENT_NAME = "${PREFIX}_SERVICE_AGREEMENT_NAME"
        const val KEY_USER_CONTEXT = "${PREFIX}_USER_CONTEXT"
        const val KEY_SETUP_COMPLETE = "${PREFIX}_SETUP_COMPLETE"
        const val KEY_CONTEXT_SIZE = "${PREFIX}_CONTEXT_SIZE"
        const val KEY_USERNAME = "${PREFIX}_USERNAME"
        const val KEY_PASSWORD = "${PREFIX}_PASSWORD"
        const val DEFAULT_BIOMETRIC_VALUE = false
        const val DEFAULT_SETUP_COMPLETE = false
    }
}
