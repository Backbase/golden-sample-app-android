package com.backbase.golden_sample_app.router

import com.backbase.android.business.journey.common.navigation.BaseRouting
import com.backbase.android.business.journey.workspaces.model.WorkspaceInfo

/**
 * Router interface for Workspace Switcher
 */
interface WorkspaceSwitcherRouting : BaseRouting {

    /**
     * Called when user switches from one workspace to another in the WorkspaceSwitcherBottomSheet
     *
     * @param workspaceInfo A [WorkspaceInfo] object that contains all
     * the necessary information after a workspace is switched
     */
    fun onWorkspaceSwitchedV2(workspaceInfo: WorkspaceInfo): Int
}
