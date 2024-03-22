package com.backbase.android.retail.workspaceselector

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavController
import com.backbase.android.business.journey.workspaces.model.WorkspaceInfo
import com.backbase.android.business.journey.workspaces.navigation.WorkspaceSelectorRouting
import com.backbase.android.retail.databinding.ContainerWorkspacesJourneyBinding
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

@Composable
fun WorkspaceSelectorJourney(onSuccess: () -> Unit) {
    InitRouter(onSuccess)
    AndroidViewBinding(
        factory = { inflater, _, _ ->
            ContainerWorkspacesJourneyBinding.inflate(inflater)
        },
        modifier = Modifier.fillMaxSize(),
    ) {
    }
}

@Composable
private fun InitRouter(onAuthenticated: () -> Unit) {

    DisposableEffect(LocalLifecycleOwner.current) {
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
                            onAuthenticated.invoke()
                            return 0
                        }
                    }
                }
            }
        )

        loadKoinModules(modules)
        onDispose {
            unloadKoinModules(modules)
        }
    }
}
