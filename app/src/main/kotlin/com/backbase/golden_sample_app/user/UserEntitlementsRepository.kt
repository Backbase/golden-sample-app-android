package com.backbase.golden_sample_app.user

import com.backbase.android.retail.feature_filter.entitlements.UserEntitlement

interface UserEntitlementsRepository {
    var entitlements: List<UserEntitlement>
}
