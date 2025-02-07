package com.backbase.app_common.feature_filter

import com.backbase.android.retail.feature_filter.entitlements.UserEntitlement

interface UserEntitlementsRepository {
    var entitlements: List<UserEntitlement>
}
