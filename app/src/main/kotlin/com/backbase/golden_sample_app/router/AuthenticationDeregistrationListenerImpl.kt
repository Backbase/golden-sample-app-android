package com.backbase.golden_sample_app.router

import com.backbase.android.identity.journey.authentication.AuthenticationDeregistrationListener
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.user.UserRepository

internal class AuthenticationDeregistrationListenerImpl(
    private val appNavigator: AppRouting,
    private val userRepository: UserRepository
) : AuthenticationDeregistrationListener {

    override fun onDeviceDeregistered() {
        println("onDeviceDeregistered")
        userRepository.clearUserInfo()
        appNavigator.getNavController()?.navigate(R.id.action_global_authenticationJourney)
    }
}
