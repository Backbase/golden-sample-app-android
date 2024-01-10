package com.backbase.golden_sample_app.router

import android.os.Bundle
import androidx.navigation.NavController
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.retail.journey.more.MoreRouter
import com.backbase.golden_sample_app.R

class MoreMenuRouterImpl(
    private val userRepository: UserRepository,
    private val navController: NavController,
    private val authClient: BBIdentityAuthClient

): MoreRouter {
    override fun onExit(navigationActionId: Int, args: Bundle?) {
        authClient.endSession {
            //Result ignored; fire and forget strategy
        }
        userRepository.clearUserInfo()
        navController.navigate(R.id.authenticationJourney)
    }

}