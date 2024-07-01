package com.backbase.accounts_journey.routing

/**
 * Created by Backbase R&D B.V. on 26/06/2024.
 */
interface AccountsRouting {
    /**
     * This function gets triggered when an account is selected.
     * @return the id of the next fragment id.
     * */
    fun onAccountSelected(): Int
}
