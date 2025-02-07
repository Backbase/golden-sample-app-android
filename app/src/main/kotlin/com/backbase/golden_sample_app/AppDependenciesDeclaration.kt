package com.backbase.golden_sample_app

import android.app.Application
import com.backbase.app_common.AppRouting
import com.backbase.app_common.appModule
import com.backbase.app_common.auth.initIdentityAuthModule
import com.backbase.app_common.storage.storageModule
import com.backbase.golden_sample_app.auth.appAuthModule
import com.backbase.golden_sample_app.feature_filter.featureFilterModule
import com.backbase.golden_sample_app.journey.accounts.accountsModule
import com.backbase.golden_sample_app.journey.profile.userProfileModule
import com.backbase.golden_sample_app.journey.workspaces.workspacesModule
import com.backbase.golden_sample_app.menu.moreMenuModule
import com.backbase.golden_sample_app.presentation.presentationModule
import com.backbase.golden_sample_app.user.userModule
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun Application.getDependenciesDeclaration(): List<Module> =
    listOf(
        module { single<AppRouting> { AppRouter(tabListConfig = get()) } },
        storageModule(context = this),
        userModule(),
        featureFilterModule(),
        appModule(),
        presentationModule(context = this),
        initIdentityAuthModule {
            appAuthModule()
        },
        moreMenuModule(),
        userProfileModule(),
        workspacesModule(),
        accountsModule(),
    )
