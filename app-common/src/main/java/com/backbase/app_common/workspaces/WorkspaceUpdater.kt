package com.backbase.app_common.workspaces

import com.backbase.android.business.journey.workspaces.model.WorkspaceInfo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object WorkspaceUpdater {
    private val _selectedWorkspace = MutableSharedFlow<WorkspaceInfo>()
    val selectedWorkspace = _selectedWorkspace.asSharedFlow()

    suspend fun updateSelectedWorkspace(workspaceInfo: WorkspaceInfo) {
        _selectedWorkspace.emit(workspaceInfo)
    }
}
