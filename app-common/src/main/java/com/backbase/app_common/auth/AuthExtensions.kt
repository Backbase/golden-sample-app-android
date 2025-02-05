/*
* Created by Backbase R&D B.V. on 10/07/2024.
*/
package com.backbase.app_common.auth

import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.client.BBIdentityAuthClientListener
import com.backbase.android.modules.SessionState
import com.backbase.android.utils.net.response.Response
import com.backbase.app_common.storage.SecureStorageWrapper

// Visibility increased for testing
internal const val AUTHENTICATION_JOURNEY_KEY_USER_FULL_NAME = "Authentication Journey full name"
internal const val AUTHENTICATION_JOURNEY_KEY_USER_NAME = "Authentication Journey username"

/**
 * Returns authenticated user's full name.
 *
 * @param storage: storage component which is used by authentication journey to store user information.
 */
@JvmSynthetic
fun getAuthenticatedUserFullName(
    storage: SecureStorageWrapper
): String? = storage.getItemWithSanityCheck(AUTHENTICATION_JOURNEY_KEY_USER_FULL_NAME)

/**
 * Returns authenticated user's user name.
 *
 * @param storage: storage component which is used by authentication journey to store user information.
 */
@JvmSynthetic
fun getAuthenticatedUserName(
    storage: SecureStorageWrapper
) = storage.getItemWithSanityCheck(AUTHENTICATION_JOURNEY_KEY_USER_NAME) ?: ""

fun BBIdentityAuthClient.logOut(afterLogout: () -> Unit) {
    invalidateTokens(object : BBIdentityAuthClientListener {
        override fun oAuth2AuthClientTokenInvalidated() {
            // Result ignored; fire and forget strategy
        }

        override fun oAuth2AuthClientAccessTokenRefreshed(p0: MutableMap<String, MutableList<String>>) {
            // Result ignored; fire and forget strategy
        }

        override fun oAuth2AuthClientAccessTokenRefreshFailed(p0: Response) {
            // Result ignored; fire and forget strategy
        }

        override fun oAuth2AuthClientTokenInvalidateFailed(p0: Response) {
            // Result ignored; fire and forget strategy
        }
    })
    endSession(null) { sessionState, _ ->
        when (sessionState) {
            SessionState.VALID -> {
                BBLogger.error("End session", "Session ending failed")
            }

            SessionState.NONE -> {
                BBLogger.debug("End session", "Session ended successfully")
                afterLogout()
            }

            else -> {}
        }
    }
}
