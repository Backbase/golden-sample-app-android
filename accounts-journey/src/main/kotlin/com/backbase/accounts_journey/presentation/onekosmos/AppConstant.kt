package com.backbase.accounts_journey.presentation.onekosmos

import com.onekosmos.blockid.sdk.datamodel.BIDTenant

/**
 * Created by 1Kosmos Engineering
 * Copyright © 2021 1Kosmos. All rights reserved.
 */
object AppConstant {
    const val licenseKey: String = "d590166b-972d-46b2-9557-fdaa1cd6600e"
    const val dvcId: String = "default_config"
    val defaultTenant: BIDTenant = BIDTenant(
        "staging",
        "chinabank",
        "https://staging-in.1kosmos.in"
    )
}