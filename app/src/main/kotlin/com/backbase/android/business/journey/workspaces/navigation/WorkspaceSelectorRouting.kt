package com.backbase.android.business.journey.workspaces.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import com.backbase.android.business.journey.workspaces.model.WorkspaceInfo
import com.backbase.android.business.journey.common.navigation.BaseRouting

/**
 * Router interface for workspace selector
 */
interface WorkspaceSelectorRouting : BaseRouting {
    /**
     * Called when user has no access to any workspace, and user is thus unable to continue further.
     */
    fun onNoWorkspacesFound()

    /**
     * Called when user has selected a workspace and it has been switched to.
     *
     * @param workspaceInfo A [WorkspaceInfo] object that contains all
     * the necessary information after a workspace is selected
     */
    fun onWorkspaceSelectedV2(workspaceInfo: WorkspaceInfo): Int

    /**
     * Called when workspace cannot be selected due to some error, for example missing internet
     * connection, and user cannot continue further.
     */
    fun onError()

    /**
     * Action to navigate out of workspace journey when logout button is clicked
     */
    fun onExit(navController: NavController, @IdRes navigationActionId: Int, args: Bundle? = null)
}