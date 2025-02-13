package com.backbase.golden_sample_app.menu

import android.os.Bundle
import androidx.navigation.NavController
import com.backbase.android.retail.journey.more.MoreRouter

class MoreMenuRouterImpl(
    private val navController: NavController
) : MoreRouter {
    override fun onExit(navigationActionId: Int, args: Bundle?) {
        navController.navigate(navigationActionId, args)
    }
}
