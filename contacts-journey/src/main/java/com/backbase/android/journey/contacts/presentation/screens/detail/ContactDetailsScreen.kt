package com.backbase.android.journey.contacts.presentation.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.backbase.android.journey.contacts.presentation.components.ContactDetails

@Composable
fun ContactDetailsScreen(
    viewModel: ContactDetailsViewModel<Unit, Unit>
) {
    val state by viewModel.state.collectAsState()

    state.contact?.let { contact ->
        ContactDetails(contact = contact)
    }
}