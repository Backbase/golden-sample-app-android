package com.backbase.golden_sample_app.router

import android.os.Bundle
import androidx.navigation.NavController
import com.backbase.android.business.journey.workspaces.model.WorkspaceInfo
import com.backbase.android.business.journey.workspaces.navigation.WorkspaceSelectorRouting
import com.backbase.android.core.utils.BBLogger
import com.backbase.golden_sample_app.common.TAG

class WorkspaceSelectorRoutingImpl : WorkspaceSelectorRouting {

    override fun onError() {
        BBLogger.error(TAG, "WorkspaceSelectorRouting onError called")
    }

    override fun onExit(navController: NavController, navigationActionId: Int, args: Bundle?) {

    }

    override fun onNoWorkspacesFound() {
        BBLogger.error(TAG, "WorkspaceSelectorRouting onNoWorkspacesFound called")
    }

    override fun onWorkspaceSelectedV2(workspaceInfo: WorkspaceInfo): Int {
        return 0//R.id.action_workspaceSelectorJourney_to_mainScreen
    }
}
