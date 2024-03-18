package com.backbase.android.retail.contacts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.backbase.android.identity.journey.authentication.AuthenticationJourney
import com.backbase.android.identity.journey.authentication.AuthenticationRouter
import com.backbase.android.retail.authorization.AuthenticationRouterImpl
import com.backbase.android.retail.databinding.ContainerAuthenticationBinding
import com.backbase.android.retail.databinding.ContainerContactsJourneyBinding
import com.backbase.android.retail.journey.contacts.ContactsConfiguration
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

@Composable
fun ContactsJourney() {
//    InitRouter(onAuthenticated)
    AndroidViewBinding(ContainerContactsJourneyBinding::inflate) {
//        val fragment = containerAuthentication.getFragment<AuthenticationJourney>()
    }
}

@Composable
private fun InitRouter(onAuthenticated: () -> Unit) {

    DisposableEffect(LocalLifecycleOwner.current) {
        // dirty hack?
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
