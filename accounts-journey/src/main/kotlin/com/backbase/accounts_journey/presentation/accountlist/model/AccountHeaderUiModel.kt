package com.backbase.accounts_journey.presentation.accountlist.model

/**
 * The header of an account type shown in the account list.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
class AccountHeaderUiModel(
    val name: String?,
    override val viewType: ViewType = ViewType.HEADER
) : ListItem
