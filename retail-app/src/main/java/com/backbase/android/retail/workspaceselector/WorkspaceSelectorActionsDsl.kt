package com.backbase.android.retail.workspaceselector

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import com.backbase.android.business.journey.workspaces.model.WorkspaceInfo
import com.backbase.android.business.journey.workspaces.navigation.WorkspaceSelectorRouting
import org.koin.android.ext.android.getKoin
import org.koin.dsl.module

fun Activity.workspaceSelectorActions(block: WorkspaceSelectorActionsScope.() -> Unit) {
    val scope = WorkspaceSelectorActionsScope().apply(block)
    val modules = listOf(
        module {
            factory<WorkspaceSelectorRouting> {
                object : WorkspaceSelectorRouting {
                    override fun onError() {
                    }

                    override fun onExit(navController: NavController, navigationActionId: Int, args: Bundle?) {
                    }

                    override fun onNoWorkspacesFound() {
                    }

                    override fun onWorkspaceSelectedV2(workspaceInfo: WorkspaceInfo): Int {
                        scope.onSuccess!!.invoke(workspaceInfo)
                        return 0
                    }
                }
            }
        }
    )
    this.getKoin().loadModules(modules)
}

class WorkspaceSelectorActionsScope {
    var onSuccess: ((workspaceInfo: WorkspaceInfo) -> Unit)? = null
}
