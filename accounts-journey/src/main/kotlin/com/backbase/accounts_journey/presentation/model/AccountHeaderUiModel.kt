package com.backbase.accounts_journey.presentation.model

class AccountHeaderUiModel(
    val name: String,
    override val viewType: ViewType = ViewType.HEADER
) : ListItem
