package com.backbase.golden_sample_app.user

import com.backbase.android.retail.feature_filter.entitlements.UserEntitlement

class UserEntitlementsRepositoryImpl: UserEntitlementsRepository {

    override var entitlements: List<UserEntitlement> = emptyList()
}
