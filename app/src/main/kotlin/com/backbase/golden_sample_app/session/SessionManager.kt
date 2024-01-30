package com.backbase.golden_sample_app.session

import androidx.navigation.NavController
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.identity.client.BBIdentityAuthClient
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
        clearSession()
        navController.clearBackStack(R.id.authenticationJourney)
    }

    fun switchUser() {
        clearSession()
        authClient.reset()
        navController.clearBackStack(R.id.authenticationJourney)
    }

    private fun clearSession() {
        authClient.logOut()
        userRepository.clearUserInfo()
        userEntitlementsRepository.entitlements = emptyList()
    }
}
