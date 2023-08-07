package com.backbase.golden_sample_app.koin

import com.backbase.android.retail.feature_filter.FeatureFilterUseCase
import com.backbase.android.retail.feature_filter.UserEntitlementFeatureFilterUseCase
import com.backbase.android.retail.feature_filter.entitlements.EntitlementsUseCase
import com.backbase.android.retail.feature_filter.entitlements.UserEntitlement
import com.backbase.android.retail.feature_filter.entitlements.accesscontrol_client_2.AccessControlClient2EntitlementsUseCase
import org.koin.dsl.module

internal val featureFilterModule = module {
    single<EntitlementsUseCase> { AccessControlClient2EntitlementsUseCase(get()) }
    single<FeatureFilterUseCase<UserEntitlement>> {
        UserEntitlementFeatureFilterUseCase(entitlementsUseCase = get())
    }
}
