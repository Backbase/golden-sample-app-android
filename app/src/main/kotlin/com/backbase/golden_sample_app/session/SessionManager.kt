package com.backbase.golden_sample_app.session

import androidx.navigation.NavController
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.modules.SessionState
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.authentication.logOut
import com.backbase.golden_sample_app.user.UserEntitlementsRepository

class SessionManager(
    private val authClient: BBIdentityAuthClient,
    private val navController: NavController,
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
        navController.popBackStack(R.id.workspaces_selector, true)
    }

    fun switchUser() {
        userRepository.clearUserInfo()
        userEntitlementsRepository.entitlements = emptyList()
        authClient.reset()
        authClient.logOut()
        navController.popBackStack(R.id.workspaces_selector, true)
    }
}
