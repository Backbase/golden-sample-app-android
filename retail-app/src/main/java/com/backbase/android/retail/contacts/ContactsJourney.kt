package com.backbase.android.retail.contacts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.backbase.android.retail.databinding.ContainerContactsJourneyBinding
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

@Composable
fun ContactsJourney() {
//    InitRouter(onAuthenticated)
    AndroidViewBinding(ContainerContactsJourneyBinding::inflate, modifier = Modifier.fillMaxSize()) {
//        val fragment = containerAuthentication.getFragment<AuthenticationJourney>()
    }
}

@Composable
private fun InitRouter(onAuthenticated: () -> Unit) {

    DisposableEffect(LocalLifecycleOwner.current) {
        val modules = listOf(
            module {

            }
        )

        loadKoinModules(modules)
        onDispose {
            unloadKoinModules(modules)
        }
    }
}
