package com.backbase.app_common.auth.session

import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.modules.SessionState
import com.backbase.app_common.auth.logOut
import com.backbase.app_common.feature_filter.UserEntitlementsRepository

class SessionManager(
    private val authClient: BBIdentityAuthClient,
    private val userRepository: UserRepository,
    private val userEntitlementsRepository: UserEntitlementsRepository
) {

    fun logOut() {
        authClient.endSession(null) { sessionState, _ ->
            when (sessionState) {
                SessionState.NONE -> {
                    userRepository.clearUserInfo()
                    userEntitlementsRepository.entitlements = emptyList()
                }

                SessionState.VALID -> {
                    BBLogger.error("End session", "Session ending failed")
                }
            }
        }
        // navController.popBackStack(R.id.workspaces_selector, true)
    }

    fun switchUser() {
        userRepository.clearUserInfo()
        userEntitlementsRepository.entitlements = emptyList()
        authClient.reset()
        authClient.logOut()
        // navController.popBackStack(R.id.workspaces_selector, true)
    }
}
