package com.backbase.app_common

import androidx.navigation.NavController

/**
 * Router for the apps
 *
 * Created by Backbase R&D B.V on 17/08/2023.
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
