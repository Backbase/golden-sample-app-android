package com.backbase.golden_sample_app.router

import com.backbase.android.business.journey.workspaces.WorkspacesJourney
import com.backbase.android.business.journey.workspaces.usecase.Workspace
import com.backbase.android.identity.journey.authentication.AuthenticationRouter
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.common.user.UserRepository
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

internal class AuthenticationRouterImpl(
    private val userRepository: UserRepository,
    private val appNavigator: AppRouting,
    private val credentialsStorage: StorageComponent,
) : AuthenticationRouter {
    private var workspacesModule = WorkspacesJourney.create()

    override fun onAuthenticated() {
        val username = credentialsStorage.getItem("Authentication Journey username") ?: ""
        userRepository.saveUsername(username.toCharArray())

        unloadKoinModules(workspacesModule)

        if (userRepository.isServiceAgreementSelected()) {
            val user = userRepository.getUserInfo()
            val workspace = Workspace {
                id = user.serviceAgreementId
                name = user.serviceAgreementName
                description = ""
                isMaster = false
            }
            workspacesModule = WorkspacesJourney.create(
                workspace = workspace
            )
        }
        loadKoinModules(workspacesModule)
        appNavigator.getNavController()?.navigate(R.id.action_authenticationJourney_to_helloFragment)
//        appNavigator.getNavController()?.navigate(R.id.action_authenticationJourneyFragment_to_workspaceSelector)
    }
}
