package com.backbase.golden_sample_app.payments

import androidx.core.os.bundleOf
import com.backbase.android.retail.journey.more.MenuItem
import com.backbase.android.retail.journey.more.MenuSection
import com.backbase.android.retail.journey.more.MenuSections
import com.backbase.android.retail.journey.more.MoreConfiguration
import com.backbase.android.retail.journey.more.MoreMenuInstanceId
import com.backbase.android.retail.journey.more.OnActionComplete
import com.backbase.android.retail.journey.payments.PaymentJourney
import com.backbase.android.retail.journey.payments.PaymentJourneyType
import com.backbase.deferredresources.DeferredDrawable
import com.backbase.deferredresources.DeferredText
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.user.UserEntitlements
import com.backbase.golden_sample_app.user.UserEntitlementsRepository

/**
 * Created by Backbase R&D B.V. on 23/01/2024.
 *
 * More menu used to create the Move Money screen.
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
        OnActionComplete.NavigateTo(
            R.id.paymentJourney,
            bundleOf(
                PaymentJourney.PAYMENT_JOURNEY_TYPE to PaymentJourneyType.A2A
            )
        )
    }
}

private fun sendMoneySection() = MenuSection {
    +MenuItem(
        DeferredText.Resource(R.string.move_money_send_to_someone),
        subtitle = DeferredText.Resource(R.string.move_money_send_to_someone_description),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_person)
    ) {
        OnActionComplete.NavigateTo(
            R.id.move_money_to_payments_journey,
            bundleOf(
                PaymentJourney.PAYMENT_JOURNEY_TYPE to PaymentJourneyType.P2P
            )
        )
    }
}

private fun activitySection() = MenuSection {
    +MenuItem(
        DeferredText.Resource(R.string.move_money_activity),
        subtitle = DeferredText.Resource(R.string.move_money_activity_description),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_update)
    ) {
        OnActionComplete.NavigateTo(R.id.move_money_to_upcoming_payments)
    }
}

private fun billPaySection() = MenuSection {
    +MenuItem(
        DeferredText.Resource(R.string.move_money_bill_pay),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_attach_money)
    ) {
        OnActionComplete.NavigateTo(R.id.move_money_to_bill_pay_sso)
    }
}

val paymentsScopeId = MoreMenuInstanceId.Custom("payments-menu")
