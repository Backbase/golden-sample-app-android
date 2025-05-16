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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CreateContactScreen(
    state: CreateContactState<*>,
    onIntent: (CreateContactIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        TextField(
            value = state.name.value,
            onValueChange = { onIntent(CreateContactIntent.UpdateName(it)) },
            label = { Text("Contact Name") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.accountNumber.value,
            onValueChange = { onIntent(CreateContactIntent.UpdateAccountNumber(it)) },
            label = { Text("Account Number") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.email.value,
            onValueChange = { onIntent(CreateContactIntent.UpdateEmail(it)) },
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
            onClick = { onIntent(CreateContactIntent.Submit) },
            modifier = Modifier.fillMaxWidth(),
            content = { Text("Save Contact") }
        )
    }
}

@Preview
@Composable
fun CreateContactScreenPreview() {
    CreateContactScreen(
        state = CreateContactState<Unit>(),
        onIntent = {}
    )
}