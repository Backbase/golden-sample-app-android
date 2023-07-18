package com.backbase.golden_sample_app.router

import com.backbase.android.identity.journey.authentication.AuthenticationDeregistrationListener

internal class AuthenticationDeregistrationListenerImpl(
//    private val appNavigator: AppRouting,
//    private val userRepository: UserRepository
) : AuthenticationDeregistrationListener {

    override fun onDeviceDeregistered() {
//        userRepository.clearUserInfo()
//        appNavigator.getNavController()?.navigate(R.id.action_global_authenticationJourney)
    }
}