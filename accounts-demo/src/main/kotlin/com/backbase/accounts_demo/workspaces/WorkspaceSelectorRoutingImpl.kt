package com.backbase.accounts_demo.workspaces

import android.os.Bundle
import androidx.navigation.NavController
import com.backbase.accounts_demo.R
import com.backbase.android.business.journey.common.user.User
import com.backbase.android.business.journey.workspaces.model.WorkspaceInfo
import com.backbase.android.business.journey.workspaces.navigation.WorkspaceSelectorRouting
import com.backbase.android.core.utils.BBLogger
import com.backbase.app_common.AppRouting
import com.backbase.app_common.feature_filter.UserEntitlementsRepository

/**
 * Workspace selector router implementation
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
class WorkspaceSelectorRoutingImpl(
    private val appRouter: AppRouting,
    private var user: User,
    private val userEntitlementsRepository: UserEntitlementsRepository
) : WorkspaceSelectorRouting {

    private val navController by lazy { appRouter.getNavController() }

    override fun onError() {
        BBLogger.error("Error", "WorkspaceSelectorRouting onError called")
    }

    override fun onExit(navController: NavController, navigationActionId: Int, args: Bundle?) {
        navController.navigate(navigationActionId, args)
    }

    override fun onNoWorkspacesFound() {
        BBLogger.error("", "WorkspaceSelectorRouting onNoWorkspacesFound called")
        navController?.popBackStack()
    }

    override fun onWorkspaceSelectedV2(workspaceInfo: WorkspaceInfo): Int {
        user.userContext = workspaceInfo.workspace.name
        userEntitlementsRepository.entitlements = workspaceInfo.entitlements ?: emptyList()

        return R.id.accountListFragment
    }
}
