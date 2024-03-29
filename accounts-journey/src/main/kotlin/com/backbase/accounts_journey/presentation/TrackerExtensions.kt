package com.backbase.accounts_journey.presentation

import com.backbase.android.observability.event.ScreenViewEvent
import com.backbase.android.observability.event.UserActionEvent

/**
 * Created by Backbase R&D B.V on 05/10/2021.
 * Events that can be tracked.
 */
private const val JOURNEY_NAME = "accounts_transactions"

private const val REFRESH_ACCOUNTS = "refresh_accounts"
private const val SEARCH_ACCOUNTS = "search_accounts"
private const val CLICK_ACCOUNTS = "click_accounts"

private const val ACCOUNT_DETAILS = "account_details"
private const val ACCOUNTS = "accounts"

val clickUserActionEvent = UserActionEvent(CLICK_ACCOUNTS, JOURNEY_NAME)
val refreshUserActionEvent = UserActionEvent(REFRESH_ACCOUNTS, JOURNEY_NAME)
val searchUserActionEvent = UserActionEvent(SEARCH_ACCOUNTS, JOURNEY_NAME)

val accountScreenViewEvent = ScreenViewEvent(ACCOUNTS, JOURNEY_NAME)
val accountDetailsScreenViewEvent = ScreenViewEvent(ACCOUNT_DETAILS, JOURNEY_NAME)
