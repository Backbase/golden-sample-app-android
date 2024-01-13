package com.backbase.golden_sample_app.router

import android.os.Bundle
import androidx.navigation.NavController
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.client.BBIdentityAuthClientListener
import com.backbase.android.retail.journey.more.MoreRouter
import com.backbase.android.utils.net.response.Response
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.authentication.logOut
import com.backbase.golden_sample_app.user.UserEntitlementsRepository

class MoreMenuRouterImpl(
    private val authClient: BBIdentityAuthClient,
    private val navController: NavController,
    private val userRepository: UserRepository,
    private val userEntitlementsRepository: UserEntitlementsRepository

): MoreRouter {
    override fun onExit(navigationActionId: Int, args: Bundle?) {
        authClient.logOut()
        userRepository.clearUserInfo()
        userEntitlementsRepository.entitlements = emptyList()
        navController.navigate(R.id.authenticationJourney)
    }

}