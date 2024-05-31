package com.backbase.golden_sample_app.user

import com.backbase.android.retail.feature_filter.entitlements.UserEntitlement

/**
 * Created by Backbase R&D B.V. on 23/01/2024.
 *
 * Represents entitlements needed to be able to use a certain feature. These entitlements are used to either show or hide a navigation item.
 */
object UserEntitlements {

    object BillPay {
        val create = UserEntitlement(
            "Billpay",
            "Billpay SSO",
            "create"
        )
    }

    object Payments {
        object A2A {
            val create = UserEntitlement(
                "Payments",
                "A2A Transfer",
                "create"
            )
            val view = UserEntitlement(
                "Payments",
                "A2A Transfer",
                "view"
            )
        }
        object DomesticWire {
            val create = UserEntitlement(
                "Payments",
                "US Domestic Wire",
                "create"
            )
        }
    }
}
