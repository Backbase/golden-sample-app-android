package com.backbase.presentation.model

/**
 * An interface to determine if the list item is a header or an item.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
sealed interface ListItem {
    val viewType: ViewType
}
