/*
 * Created by Backbase R&D B.V. on 18/07/2024.
 */
package com.backbase.app_common.auth.session

import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.app_common.auth.logOut

/**
 * Manages user session operations, including logging out and switching users.
 *
 * The [SessionManager] class provides functionality to handle user sessions,
 * such as logging out a user and switching between users. It interacts with
 * an authentication client and a user repository to perform these operations.
 *
 * @property authClient An instance of [BBIdentityAuthClient] responsible for handling
 *                      authentication tasks such as logging out and resetting sessions.
 * @property userRepository An instance of [UserRepository] used to manage user information,
 *                          specifically for clearing user data during session changes.
 */
class SessionManager(
    private val authClient: BBIdentityAuthClient,
    private val userRepository: UserRepository,
) {

    /**
     * Logs the user out and performs additional operations after logout.
     *
     * @param doAfter A lambda function to be executed after the logout process is completed.
     */
    suspend fun logOut(doAfter: () -> Unit) {
        authClient.logOut {
            clearSession()
            doAfter()
        }
    }

    /**
     * Switches the user session and performs additional operations afterward.
     *
     * @param doAfter A lambda function to be executed to perform actions
     *                such as navigation to login screen
     *                after the user session has been cleared.
     */
    fun switchUser(doAfter: () -> Unit) {
        clearSession()
        authClient.reset()
        doAfter()
    }

    /**
     * Clears the current user session data.
     */
    private fun clearSession() {
        userRepository.clearUserInfo()
    }
}
