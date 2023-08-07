package com.backbase.golden_sample_app.koin

import com.backbase.android.business.journey.workspaces.accesscontrol_client_2.WorkspacesUseCaseImpl
import com.backbase.android.business.journey.workspaces.usecase.WorkspacesUseCase
import com.backbase.android.business.journey.workspaces.navigation.WorkspaceSelectorRouting
import com.backbase.golden_sample_app.router.WorkspaceSelectorRoutingImpl
import com.backbase.golden_sample_app.router.WorkspaceSwitcherRouterImpl
import com.backbase.golden_sample_app.router.WorkspaceSwitcherRouting
import org.koin.dsl.module

internal val workspacesModule = module {
    single<WorkspaceSwitcherRouting> { WorkspaceSwitcherRouterImpl() }
    factory<WorkspacesUseCase> { WorkspacesUseCaseImpl(get(), get()) }
    factory<WorkspaceSelectorRouting> { WorkspaceSelectorRoutingImpl() }
}
