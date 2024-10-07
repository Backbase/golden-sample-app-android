package com.backbase.accounts_journey.routing

import androidx.navigation.NavController
import com.backbase.accounts_journey.presentation.accountlist.ui.AccountListFragmentDirections

/**
 * Created by Backbase R&D B.V. on 26/06/2024.
 */
internal class AccountsRoutingImpl : AccountsRouting {

    private var navController: NavController? = null

    override fun bind(navController: NavController) {
        this.navController = navController
    }

    override fun unbind() {
        navController = null
    }

    override fun getNavController(): NavController? {
        return navController
    }

    override fun onAccountSelected(id: String) {
        navController?.navigate(AccountListFragmentDirections.actionAccountListFragmentToAccountDetailFragment(id))
    }
}
