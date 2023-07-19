package com.backbase.golden_sample_app.router

import androidx.navigation.NavController

/**
 * Router for the apps
 */
interface AppRouting {
    /** Method to bind the root [NavController] of the app
     */
    fun bind(navController: NavController)

    /** Method to unbind the root [NavController] of the app
     *  Simple implementation is to set null
     */
    fun unbind()

    /** Method to get the root [NavController] of the app
     */
    fun getNavController(): NavController?

    companion object {
        fun getRouterName(router: String) = "${AppRouting::class.java.simpleName}_$router"
    }
}
