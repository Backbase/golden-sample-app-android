package com.backbase.golden_sample_app.user

import com.backbase.android.retail.feature_filter.entitlements.UserEntitlement
import com.backbase.app_common.feature_filter.UserEntitlementsRepository

class UserEntitlementsRepositoryImpl : UserEntitlementsRepository {

    override var entitlements: List<UserEntitlement> = emptyList()
}
