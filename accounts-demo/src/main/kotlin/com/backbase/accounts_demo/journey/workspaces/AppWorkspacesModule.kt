package com.backbase.accounts_demo.journey.workspaces

import android.app.Application
import com.backbase.accounts_demo.workspaces.WorkspaceSelectorRoutingImpl
import com.backbase.android.business.journey.workspaces.WorkspacesJourney
import com.backbase.android.business.journey.workspaces.accesscontrol_client_2.WorkspacesUseCaseImpl
import com.backbase.android.client.gen2.accesscontrolclient3.api.UserContextApi
import com.backbase.android.client.gen2.accesscontrolclient3.api.UsersApi
import com.backbase.app_common.apiRoot
import com.backbase.app_common.workspaces.workspacesModule
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin
import java.net.URI

internal fun workspacesModule(): Module = module {
    single {
        UserContextApi(
            context = get<Application>(),
            moshi = get(),
            parser = get(),
            serverUri = URI("${apiRoot()}/access-control"),
            provider = get(),
            backbase = get(),
        )
    }

    single {
        UsersApi(
            context = get<Application>(),
            moshi = get(),
            parser = get(),
            serverUri = URI("${apiRoot()}/access-control"),
            provider = get(),
            backbase = get()
        )
    }

    workspacesModule {
        workspacesUseCase = {
            WorkspacesUseCaseImpl(
                userContextApi = get(),
                userRepository = get(),
            )
        }
        workspaceSelectorRouting = {
            WorkspaceSelectorRoutingImpl(
                appRouter = get(),
                user = get(),
                userEntitlementsRepository = get()
            )
        }
    }
}

internal fun injectWorkspacesJourney() {
    loadKoinModules(
        WorkspacesJourney.create(getKoin().get())
    )
}
