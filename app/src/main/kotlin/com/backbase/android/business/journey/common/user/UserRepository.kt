package com.backbase.android.business.journey.common.user

import java.lang.Exception

/**
 * Created by Backbase R&D B.V on 2019-08-26.
 * Contract for user repository. Holds the user data collected during enrollment
 * TODO Can be moved to login interface class, will be finalized during full implementation
 */
interface UserRepository {

    /** Method to save user info. Refer to [User] for the properties.
     * Note: This is mutable for the whole app lifecycle
     * @param user
     */
    fun saveUserInfo(user: User)

    /** Method to return User object. Refer to [User] for the properties.
     * @return [User]
     */
    fun getUserInfo(): User

    /** Method to clear the complete user info. To be used during logout
     */
    fun clearUserInfo()

    /** Method to check the status of service agreement. Returns true if service agreement is
     * selected already
     * @return [Boolean]
     */
    fun isServiceAgreementSelected(): Boolean

    /** Method to clear the service agreements
     * but not the entire [User] object.
     */
    fun clearServiceAgreement()

    /** Method to check the status of username. Returns true if username is stored
     * @return [Boolean]
     */
    fun isUsernameSaved(): Boolean

    /** Method to check the status of biometric registration.
     * Returns true if biometric is registered
     * @return [Boolean]
     */
    fun isUserBiometricRegistered(): Boolean

    /**
     * Encrypt and safely store user credentials.
     * @param username User account name
     * @param password User password
     */
    @Deprecated(
        message = "This method is unsafe. You shouldn't store user's password.",
        replaceWith = ReplaceWith("saveUsername(username")
    )
    fun saveEncryptedCredentials(username: CharArray, password: CharArray)

    /**
     *  Safely store username
     *  @param username in CharArray
     */
    fun saveUsername(username: CharArray)

    /**
     * Get username in char array
     */
    fun getUsername(): CharArray?

    /**
     * Get password in char array
     */
    @Deprecated(message = "This method is unsafe and shouldn't be used.")
    fun getPassword(): CharArray?

    /**
     * Status of enrollment setup. From authentication to biometric preference
     */
    fun isSetupCompleted(): Boolean

    class CorruptedUserDataException : Exception()
}
