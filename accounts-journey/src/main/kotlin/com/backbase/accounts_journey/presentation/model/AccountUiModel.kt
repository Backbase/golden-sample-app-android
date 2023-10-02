package com.backbase.accounts_journey.presentation.model

data class AccountUiModel(
    val id: String,
    val name: String,
    val balance: String,
    val state: String?,
    val isVisible: Boolean?,
    override val viewType: ViewType = ViewType.ITEM
) : ListItem
