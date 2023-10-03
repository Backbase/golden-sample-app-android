package com.backbase.accounts_journey.presentation.model

import androidx.annotation.DrawableRes

data class AccountUiModel(
    val id: String,
    val name: String,
    val balance: String,
    val state: String?,
    val isVisible: Boolean?,
    @DrawableRes val icon: Int,
    override val viewType: ViewType = ViewType.ITEM
) : ListItem
