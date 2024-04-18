package com.backbase.golden_sample_app.koin

import com.backbase.android.retail.feature_filter.FeatureFilterUseCase
import com.backbase.android.retail.feature_filter.UserEntitlementFeatureFilterUseCase
import com.backbase.android.retail.feature_filter.entitlements.EntitlementsUseCase
import com.backbase.android.retail.feature_filter.entitlements.UserEntitlement
import com.backbase.android.retail.feature_filter.entitlements.accesscontrol_client_2.AccessControlClient2EntitlementsUseCase
import org.koin.dsl.module

/**
 * Dependency setup for Entitlements and Feature filter.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
internal val featureFilterModule = module {
    single<EntitlementsUseCase> { AccessControlClient2EntitlementsUseCase(get()) }
    single<FeatureFilterUseCase<UserEntitlement>> { UserEntitlementFeatureFilterUseCase(get()) }
}
