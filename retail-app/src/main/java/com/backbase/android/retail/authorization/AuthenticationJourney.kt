package com.backbase.android.retail.authorization

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.backbase.android.identity.journey.authentication.AuthenticationJourney
import com.backbase.android.retail.databinding.ContainerAuthenticationBinding

@Composable
fun AuthenticationJourney() {
    AndroidViewBinding(ContainerAuthenticationBinding::inflate) {
        val fragment = containerAuthentication.getFragment<AuthenticationJourney>()
    }
}
