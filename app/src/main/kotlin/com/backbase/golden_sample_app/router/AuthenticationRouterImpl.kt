package com.backbase.golden_sample_app.router

import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.identity.journey.authentication.AuthenticationRouter
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.golden_sample_app.R

/**
 * Authentication router implementation
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
internal class AuthenticationRouterImpl(
    private val userRepository: UserRepository,
    private val appNavigator: AppRouting,
    private val credentialsStorage: StorageComponent,
) : AuthenticationRouter {

    override fun onAuthenticated() {
        val username = credentialsStorage.getItem("Authentication Journey username") ?: ""
        userRepository.saveUsername(username.toCharArray())
        appNavigator.getNavController()?.navigate(R.id.action_authenticationJourneyFragment_to_workspaceSelector)
    }
}
