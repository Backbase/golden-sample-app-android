package com.backbase.presentation.model

import androidx.annotation.DrawableRes

/**
 * The UI model for the Account. Only the required properties are mapped for displaying on the
 * screen.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
data class AccountUiModel(
    val id: String?,
    val name: String?,
    val balance: String?,
    val state: String?,
    val isVisible: Boolean?,
    @DrawableRes val icon: Int,
    override val viewType: ViewType = ViewType.ITEM
) : ListItem
