package com.backbase.accounts_journey.presentation.model

sealed interface ListItem {
    val viewType: ViewType
}