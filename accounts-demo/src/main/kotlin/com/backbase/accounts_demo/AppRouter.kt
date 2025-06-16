package com.backbase.accounts_demo

import androidx.navigation.NavController
import com.backbase.app_common.AppRouting

/**
 * App routing implementation
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
class AppRouter : AppRouting {

    private var navController: NavController? = null

    override fun bind(navController: NavController) {
        this.navController = navController
    }

    override fun unbind() {
        navController = null
    }

    override fun getNavController(): NavController? = navController
}
