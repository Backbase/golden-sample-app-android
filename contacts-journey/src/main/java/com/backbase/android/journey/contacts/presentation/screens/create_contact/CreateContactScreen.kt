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

@Composable
fun CreateContactScreen(
    viewModel: CreateContactViewModel<Unit, Unit>,
    onNavigateBack: () -> Unit,
    onNavigateAfterSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()


    LaunchedEffect(state.isSaved) {
        if (state.isSaved) {
            onNavigateBack()
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
            onValueChange = { viewModel.handleIntent(CreateContactIntent.UpdateName(it)) },
            label = { Text("Contact Name") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.accountNumber.value,
            onValueChange = { viewModel.handleIntent(CreateContactIntent.UpdateAccountNumber(it)) },
            label = { Text("Account Number") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = state.email.value,
            onValueChange = { viewModel.handleIntent(CreateContactIntent.UpdateEmail(it)) },
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
            onClick = {
                viewModel.handleIntent(
                    CreateContactIntent.SaveContact
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Contact")
        }
    }
}