package com.backbase.golden_sample_app.journey.workspaces

import android.app.Application
import com.backbase.android.business.journey.workspaces.WorkspacesJourney
import com.backbase.android.business.journey.workspaces.accesscontrol_client_2.WorkspacesUseCaseImpl
import com.backbase.android.client.gen2.accesscontrolclient3.api.UserContextApi
import com.backbase.android.client.gen2.accesscontrolclient3.api.UsersApi
import com.backbase.app_common.workspaces.workspacesModule
import com.backbase.app_common.apiRoot
import com.backbase.golden_sample_app.router.WorkspaceSelectorRoutingImpl
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
//        configuration = {
//            retailWorkspaceJourneyConfiguration()
//        }
        workspacesUseCase = {
            WorkspacesUseCaseImpl(
                userContextApi = get(),
                userRepository = get(),
            )
        }
        workspaceSelectorRouting = {
            WorkspaceSelectorRoutingImpl(get(), get(), get())
//            RetailWorkspacesSelectorRouting(
//                appRouting = get(),
//                userRepository = get(),
//                coroutineScope = get(qualifier = COMMON_MAIN_COROUTINE_SCOPE_QUALIFIER),
//                featureFilterUseCase = get(),
//                deviceManagementTokenSynchronizer = get(),
//                authClient = get(),
//                secureStorageWrapper = get<SecureStorageWrapper>()
//            )
        }
//        workspaceSwitcherRouting = {
//            RetailWorkspaceSwitcherRouting(
//                featureFilterUseCase = get(),
//                userRepository = get(),
//                coroutineScope = get(qualifier = COMMON_MAIN_COROUTINE_SCOPE_QUALIFIER),
//                secureStorageWrapper = get<SecureStorageWrapper>(),
//            )
//        }
    }
}

internal fun injectWorkspacesJourney() {
    loadKoinModules(
        WorkspacesJourney.create(getKoin().get())
    )
}
