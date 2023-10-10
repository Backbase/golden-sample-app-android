package com.backbase.accounts_journey.presentation.model

/**
 * The UI model for AccountSummary. It can generate a list that is ready to be used in the UI.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
data class AccountSummaryUiModel(
    val customProducts: List<AccountsUiModel>,
    val currentAccounts: AccountsUiModel?,
    val savingAccounts: AccountsUiModel?,
    val termDeposits: AccountsUiModel?,
    val loans: AccountsUiModel?,
    val creditCards: AccountsUiModel?,
    val debitCards: AccountsUiModel?,
    val investmentAccounts: AccountsUiModel?,
) {
    fun generateList(query: String = ""): List<ListItem> {
        val list = mutableListOf<ListItem>()
        list.addAll(filter(currentAccounts, query))
        list.addAll(filter(savingAccounts, query))
        list.addAll(filter(termDeposits, query))
        list.addAll(filter(loans, query))
        list.addAll(filter(creditCards, query))
        list.addAll(filter(debitCards, query))
        list.addAll(filter(investmentAccounts, query))
        customProducts.forEach {
            list.addAll(filter(it, query))
        }
        return list
    }

    private fun filter(accounts: AccountsUiModel?, query: String): List<ListItem> {
        val header = accounts?.header as? ListItem
        val query = query.lowercase().trim()
        val products = accounts?.products?.takeIf {
            it.isNotEmpty()
        }?.filter {
            it.name?.lowercase()?.contains(query) ?: false
        } as? Collection<ListItem>

        if (header == null || products.isNullOrEmpty()) return emptyList()

        val list = mutableListOf<ListItem>()
        list.add(header)
        list.addAll(products)
        return list.toList()
    }
}
