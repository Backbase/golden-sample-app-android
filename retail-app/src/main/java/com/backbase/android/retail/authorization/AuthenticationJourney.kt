package com.backbase.android.retail.authorization

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.backbase.android.identity.journey.authentication.AuthenticationJourney
import com.backbase.android.identity.journey.authentication.AuthenticationRouter
import com.backbase.android.retail.databinding.ContainerAuthenticationBinding
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

@Composable
fun AuthenticationJourney(onAuthenticated: () -> Unit) {
    InitRouter(onAuthenticated)
    AndroidViewBinding(ContainerAuthenticationBinding::inflate, modifier = Modifier.fillMaxSize()) {
        val fragment = containerAuthentication.getFragment<AuthenticationJourney>()
    }
}

@Composable
private fun InitRouter(onAuthenticated: () -> Unit) {

    DisposableEffect(LocalLifecycleOwner.current) {
        val modules = listOf(
            module {
                factory<AuthenticationRouter> {
                    AuthenticationRouterImpl(onAuthenticated)
                }
            }
        )

        loadKoinModules(modules)
        onDispose {
            unloadKoinModules(modules)
        }
    }
}
