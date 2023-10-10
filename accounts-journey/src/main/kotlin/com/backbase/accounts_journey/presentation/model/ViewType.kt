package com.backbase.accounts_journey.presentation.model

/**
 * The account types on the list are separated with a header. This enum is used to separate this.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
enum class ViewType(val value: Int) {
    HEADER(0),
    ITEM(1)
}
