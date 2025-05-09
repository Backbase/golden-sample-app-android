package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect.ToContactCreateResult
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent

@Composable
fun CreateContactScreen(
    viewModel: CreateContactViewModel<*>,
    onNavigateAfterSuccess: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()
    val effect by viewModel.effects.collectAsState(initial = null)

    LaunchedEffect(effect) {
        when (effect) {
            is ToContactCreateResult -> onNavigateAfterSuccess()
            null -> { /* no-op */ }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        TextField(
            value = state.name.value,
            onValueChange = { viewModel.handle(intent = CreateContactIntent.UpdateName(it)) },
            label = { Text("Contact Name") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.accountNumber.value,
            onValueChange = { viewModel.handle(intent = CreateContactIntent.UpdateAccountNumber(it)) },
            label = { Text("Account Number") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = state.email.value,
            onValueChange = { viewModel.handle(intent = CreateContactIntent.UpdateEmail(it)) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        state.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colors.error,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = { viewModel.handle(intent = CreateContactIntent.Submit) },
            modifier = Modifier.fillMaxWidth(),
            content = { Text("Save Contact") }
        )
    }
}