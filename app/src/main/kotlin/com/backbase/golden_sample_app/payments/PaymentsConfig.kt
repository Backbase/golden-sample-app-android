package com.backbase.golden_sample_app.payments

import androidx.navigation.NavController
import com.backbase.android.retail.journey.more.MenuItem
import com.backbase.android.retail.journey.more.MenuSection
import com.backbase.android.retail.journey.more.MenuSections
import com.backbase.android.retail.journey.more.MoreConfiguration
import com.backbase.android.retail.journey.more.MoreJourneyScope
import com.backbase.android.retail.journey.more.MoreMenuInstanceId
import com.backbase.android.retail.journey.more.OnActionComplete
import com.backbase.deferredresources.DeferredDrawable
import com.backbase.deferredresources.DeferredText
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.router.MoreMenuRouterImpl
import com.backbase.golden_sample_app.user.UserEntitlements
import com.backbase.golden_sample_app.user.UserEntitlementsRepository
import org.koin.dsl.module

internal fun paymentsMenuModule(
    navController: NavController
) = module {

    scope<MoreJourneyScope> {
        factory(paymentsScopeId) {
            MoreMenuRouterImpl(navController)
        }

        scoped(paymentsScopeId) { paymentsMenuConfig(get()) }
    }

}

/**
 * Created by Backbase R&D B.V. on 23/07/2020.
 */

fun paymentsMenuConfig(
    userEntitlementsRepository: UserEntitlementsRepository
) = MoreConfiguration {
    showIcons = true
    screenTitle = DeferredText.Resource(R.string.move_money_title)
    contentDescription = DeferredText.Resource(R.string.move_money_title)
    sections = MenuSections {
        if (userEntitlementsRepository.entitlements.contains(UserEntitlements.Payments.A2A.create)) {
            +makeATransferSection()
        }
        if (userEntitlementsRepository.entitlements.contains(UserEntitlements.Payments.DomesticWire.create)) {
            +sendMoneySection()
        }
        if (userEntitlementsRepository.entitlements.contains(UserEntitlements.Payments.A2A.view)) {
            +activitySection()
        }
        if (userEntitlementsRepository.entitlements.contains(UserEntitlements.BillPay.create)) {
            +billPaySection()
        }
    }
}

private fun makeATransferSection() = MenuSection {
    +MenuItem(
        DeferredText.Resource(R.string.move_money_make_a_transfer),
        subtitle = DeferredText.Resource(R.string.move_money_make_a_transfer_description),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_compare_arrows)
    ) {
        OnActionComplete.NavigateTo(R.id.action_more_to_contactsJourney)
    }
}


private fun sendMoneySection() = MenuSection {
    +MenuItem(
        DeferredText.Resource(R.string.move_money_send_to_someone),
        subtitle = DeferredText.Resource(R.string.move_money_send_to_someone_description),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_person)
    ) {
        OnActionComplete.NavigateTo(R.id.action_more_to_contactsJourney)
    }
}

private fun activitySection() = MenuSection {
    +MenuItem(
        DeferredText.Resource(R.string.move_money_activity),
        subtitle = DeferredText.Resource(R.string.move_money_activity_description),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_update)
    ) {
        OnActionComplete.NavigateTo(R.id.action_more_to_contactsJourney)
    }
}

private fun billPaySection() = MenuSection {
    +MenuItem(
        DeferredText.Resource(R.string.move_money_bill_pay),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_attach_money)
    ) {
        OnActionComplete.NavigateTo(R.id.action_more_to_contactsJourney)
    }
}

val paymentsScopeId = MoreMenuInstanceId.Custom("payments-menu")

/**
 * fun DefaultUniversalMoveMoneyConfiguration(
 *     initializer: MoreConfiguration.Builder.() -> Unit = {}
 * ) = MoreConfiguration.Builder().apply {
 *     screenTitle = DeferredText.Resource(R.string.universalApp_moveMoney_title)
 *     contentDescription = DeferredText.Resource(R.string.universalApp_moveMoney_contentDescription)
 *     sections = mutableListOf<MenuSection>().apply {
 *         add(MenuSection(listOf(makeTransferItem)))
 *         add(MenuSection(listOf(scheduledTransfersItem)))
 *         add(MenuSection(listOf(m2mTransfer)))
 *     }
 * }.apply(initializer).build()
 *
 * private val makeTransferItem = MenuItem(
 *     DeferredText.Resource(R.string.universalApp_moveMoney_item_makeATransfer_title),
 *     subtitle = DeferredText.Resource(R.string.universalApp_moveMoney_item_makeATransfer_subtitle),
 *     icon = DeferredDrawable.Resource(R.drawable.backbase_ic_compare_arrows) {
 *         setTint(DeferredColor.Attribute(R.attr.colorPrimary).resolve(it))
 *     },
 *     iconBackgroundColor = DeferredColor.Attribute(R.attr.colorSelected)
 * ) {
 *     OnActionComplete.NavigateTo(
 *         R.id.action_mainScreen_to_paymentsJourney,
 *         bundleOf(PaymentJourney.PAYMENT_JOURNEY_TYPE to PaymentJourneyType.Internal)
 *     )
 * }
 *
 * private val scheduledTransfersItem = MenuItem(
 *     DeferredText.Resource(R.string.universalApp_moveMoney_item_scheduledTransfers_title),
 *     subtitle = DeferredText.Resource(R.string.universalApp_moveMoney_item_scheduledTransfers_subtitle),
 *     icon = DeferredDrawable.Resource(R.drawable.backbase_ic_update) {
 *         setTint(DeferredColor.Attribute(R.attr.colorPrimary).resolve(it))
 *     },
 *     iconBackgroundColor = DeferredColor.Attribute(R.attr.colorSelected)
 * ) {
 *     OnActionComplete.NavigateTo(R.id.action_mainScreen_to_upcomingPaymentsJourney)
 * }
 *
 * private val m2mTransfer = MenuItem(
 *     title = DeferredText.Resource(R.string.universalApp_moveMoney_item_sendMoneyToMember_title),
 *     subtitle = DeferredText.Resource(R.string.universalApp_moveMoney_item_sendMoneyToMember_subtitle),
 *     icon = DeferredDrawable.Resource(R.drawable.backbase_ic_account_box) {
 *         setTint(DeferredColor.Attribute(R.attr.colorPrimary).resolve(it))
 *     },
 *     iconBackgroundColor = DeferredColor.Attribute(R.attr.colorSelected)
 * ) {
 *     OnActionComplete.NavigateTo(
 *         R.id.action_mainScreen_to_paymentsJourney, bundleOf(
 *             PaymentJourney.PAYMENT_JOURNEY_TYPE to PaymentJourneyType.M2M
 *         )
 *     )
 * }
 *
 */