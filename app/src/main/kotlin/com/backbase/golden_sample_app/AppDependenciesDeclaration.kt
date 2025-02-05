package com.backbase.golden_sample_app

import android.app.Application
import com.backbase.app_common.auth.initIdentityAuthModule
import com.backbase.app_common.storage.storageModule
import com.backbase.golden_sample_app.auth.appAuthModule
import com.backbase.golden_sample_app.journey.workspaces.workspacesModule
import com.backbase.golden_sample_app.koin.appModule
import com.backbase.golden_sample_app.koin.featureFilterModule
import com.backbase.golden_sample_app.koin.presentationModule
import com.backbase.golden_sample_app.koin.securityModule
import com.backbase.golden_sample_app.koin.servicesModule
import com.backbase.golden_sample_app.koin.userModule
import org.koin.core.module.Module

internal fun Application.getDependenciesDeclaration(): List<Module> =
    listOf(
        securityModule(this),
        storageModule(this),
        servicesModule(this),
        userModule(),
        featureFilterModule,
        appModule(),
        presentationModule(context = this),
        initIdentityAuthModule {
            appAuthModule()
        },
        workspacesModule(),
    )