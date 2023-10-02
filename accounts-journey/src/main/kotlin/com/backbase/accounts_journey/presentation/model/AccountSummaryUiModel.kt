package com.backbase.accounts_journey.presentation.model

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
        list.addAll(safeCast(currentAccounts, query))
        list.addAll(safeCast(savingAccounts, query))
        list.addAll(safeCast(termDeposits, query))
        list.addAll(safeCast(loans, query))
        list.addAll(safeCast(creditCards, query))
        list.addAll(safeCast(debitCards, query))
        list.addAll(safeCast(investmentAccounts, query))
        customProducts.forEach {
            list.addAll(safeCast(it, query))
        }
        return list
    }

    private fun safeCast(accounts: AccountsUiModel?, query: String = ""): List<ListItem> {
        val header = accounts?.header as? ListItem
        val query = query.lowercase().trim()
        val products = accounts?.products?.takeIf {
            it.isNotEmpty()
        }?.filter {
            it.name.lowercase().contains(query)
        } as? Collection<ListItem>

        if (header == null || products.isNullOrEmpty()) return emptyList()

        val list = mutableListOf<ListItem>()
        list.add(header)
        list.addAll(products)
        return list.toList()
    }
}
