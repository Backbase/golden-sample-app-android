package com.backbase.golden_sample_app.router

import androidx.navigation.NavController

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
