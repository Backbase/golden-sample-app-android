package com.backbase.golden_sample_app.router

import android.os.Bundle
import androidx.navigation.NavController
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.retail.journey.more.MoreRouter
import com.backbase.golden_sample_app.R

class MoreMenuRouterImpl(
    private val userRepository: UserRepository,
    private val navController: NavController

): MoreRouter {
    override fun onExit(navigationActionId: Int, args: Bundle?) {
        userRepository.clearUserInfo()
        navController.navigate(R.id.authenticationJourney)
    }


}