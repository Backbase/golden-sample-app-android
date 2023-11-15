package com.backbase.accounts_journey.domain.model

enum class AccountType(val id: String) {
    CURRENT_ACCOUNT("current-account"),
    SAVINGS_ACCOUNT("savings-account"),
    TERM_DEPOSIT("term-deposit"),
    LOAN("loan"),
    CREDIT_CARD("credit-card"),
    DEBIT_CARD("debit-card"),
    INVESTMENT_ACCOUNT("investment-account"),
    GENERAL_ACCOUNT("general-account");

    companion object {
        fun getValue(id: String): AccountType? {
            return values().firstOrNull { it.id == id }
            throw IllegalArgumentException("Value not found, use default")
        }
    }
}
