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

        /**
         * Get value by id as type String.
         *
         * @param id
         * @return @[AccountType] or null if not found
         */
        fun getValueOrNull(id: String): AccountType? {
            return values().firstOrNull { it.id == id }
        }
    }
}
