package com.backbase.golden_sample_app.router

import com.backbase.android.identity.journey.authentication.AuthenticationRouter
import com.backbase.android.plugins.storage.StorageComponent

internal class AuthenticationRouterImpl(
    //private val userRepository: UserRepository,
    //private val appNavigator: AppRouting,
    private val credentialsStorage: StorageComponent,
) : AuthenticationRouter {
//    private var workspacesModule = WorkspacesJourney.create(
//        workspacesJourneyConfiguration = configurationUsa.workspacesJourney
//    )

    override fun onAuthenticated() {
        val username = credentialsStorage.getItem("Authentication Journey username") ?: ""
        //userRepository.saveUsername(username.toCharArray())

//        unloadKoinModules(workspacesModule)

//        if (userRepository.isServiceAgreementSelected()) {
//            val user = userRepository.getUserInfo()
//            val workspace = Workspace {
//                id = user.serviceAgreementId
//                name = user.serviceAgreementName
//                description = ""
//                isMaster = false
//            }
//            workspacesModule = WorkspacesJourney.create(
//                workspacesJourneyConfiguration = configurationUsa.workspacesJourney,
//                workspace = workspace
//            )
//        }
//        loadKoinModules(workspacesModule)
//        appNavigator.getNavController()?.navigate(R.id.action_authenticationJourneyFragment_to_workspaceSelector)
    }
}