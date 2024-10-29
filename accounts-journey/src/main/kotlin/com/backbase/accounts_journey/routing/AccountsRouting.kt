package com.backbase.accounts_journey.routing

import androidx.navigation.NavController

/**
 * Created by Backbase R&D B.V. on 26/06/2024.
 */
interface AccountsRouting {

    /**
     * Method to bind the root [NavController] of the journey
     */
    fun bind(navController: NavController)

    /**
     * Method to unbind the root [NavController] of the journey
     * Simple implementation is to set null
     */
    fun unbind()

    /**
     * Method to get the root [NavController] of the journey
     */
    fun getNavController(): NavController?

    /**
     * This function gets triggered when an account is selected.
     * @return the id of the next fragment id.
     * */
    fun onAccountSelected(id: String)
}
