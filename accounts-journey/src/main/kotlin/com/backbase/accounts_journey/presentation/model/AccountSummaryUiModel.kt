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
    fun generateList(): List<ListItem> {
        val list = mutableListOf<ListItem>()
        list.addAll(safeCast(currentAccounts))
        list.addAll(safeCast(savingAccounts))
        list.addAll(safeCast(termDeposits))
        list.addAll(safeCast(loans))
        list.addAll(safeCast(creditCards))
        list.addAll(safeCast(debitCards))
        list.addAll(safeCast(investmentAccounts))
        customProducts.forEach {
            list.addAll(safeCast(it))
        }
        return list
    }

    private fun safeCast(accounts: AccountsUiModel?): List<ListItem> {
        val header = accounts?.header as? ListItem
        val products = accounts?.products?.takeIf {
            it.isNotEmpty()
        } as? Collection<ListItem>

        if (header == null || products.isNullOrEmpty()) return emptyList()

        val list = mutableListOf<ListItem>()
        list.add(header)
        list.addAll(products)
        return list.toList()
    }
}
