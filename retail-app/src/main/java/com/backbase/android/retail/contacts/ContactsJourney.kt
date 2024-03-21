package com.backbase.android.retail.contacts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.backbase.android.retail.databinding.ContainerContactsJourneyBinding

@Composable
fun ContactsJourney() {
    AndroidViewBinding(ContainerContactsJourneyBinding::inflate, modifier = Modifier.fillMaxSize()) {
    }
}

