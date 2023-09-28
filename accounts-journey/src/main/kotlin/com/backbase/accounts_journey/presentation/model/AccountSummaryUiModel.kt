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
        val l = mutableListOf<ListItem>()
        currentAccounts?.header?.let {
            l.add(it)
        }
        (currentAccounts?.products as? Collection<ListItem>)?.let {
            l.addAll(it)
        }
        savingAccounts?.header?.let {
            l.add(it)
        }
        (savingAccounts?.products as? Collection<ListItem>)?.let {
            l.addAll(it)
        }

        //safeCast(currentAccounts?.products)
//        l.addAll(currentAccounts?.products)
//        l.addAll(savingAccounts?.products)
//        l.addAll(termDeposits?.products as Collection<ListItem>)
//        l.addAll(loans?.products as Collection<ListItem>)
//        l.addAll(creditCards?.products as Collection<ListItem>)
//        l.addAll(debitCards?.products as Collection<ListItem>)
//        l.addAll(investmentAccounts?.products as Collection<ListItem>)
        return l
    }

    private fun safeCast(a: List<ListItem>?): Collection<ListItem>? {
        return a as? Collection<AccountUiModel>
    }
}
