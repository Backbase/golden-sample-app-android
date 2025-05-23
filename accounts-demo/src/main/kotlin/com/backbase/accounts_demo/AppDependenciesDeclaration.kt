package com.backbase.accounts_demo

import android.app.Application
import com.backbase.accounts_demo.auth.appAuthModule
import com.backbase.accounts_demo.feature_filter.featureFilterModule
import com.backbase.accounts_demo.journey.accounts.accountsModule
import com.backbase.accounts_demo.journey.profile.userProfileModule
import com.backbase.accounts_demo.journey.workspaces.workspacesModule
import com.backbase.accounts_demo.user.userModule
import com.backbase.app_common.AppRouting
import com.backbase.app_common.appModule
import com.backbase.app_common.auth.initIdentityAuthModule
import com.backbase.app_common.storage.storageModule
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun Application.getDependenciesDeclaration(): List<Module> =
    listOf(
        module { single<AppRouting> { AppRouter() } },
        storageModule(context = this),
        userModule(),
        featureFilterModule(),
        appModule(),
        initIdentityAuthModule {
            appAuthModule()
        },
        userProfileModule(),
        workspacesModule(),
        accountsModule(),
    )
