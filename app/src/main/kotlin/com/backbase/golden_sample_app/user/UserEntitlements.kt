package com.backbase.golden_sample_app.user

import com.backbase.android.retail.feature_filter.entitlements.UserEntitlement

object UserEntitlements {

    object BillPay {
        val create = UserEntitlement(
            "Billpay",
            "Billpay SSO",
            "create"
        )
    }

    object Contact {
        val view = UserEntitlement(
            "Contacts",
            "Contacts",
            "view"
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
