package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.CreateContactAccountIntent.*
import com.backbase.android.journey.contacts.R

@Composable
fun CreateContactAccountScreen(
    viewModel: CreateContactAccountViewModel2<Unit>,
    onNavigateAfterSuccess: () -> Unit = {}
) {
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    
    LaunchedEffect(effect) {
        when (effect) {
            is CreateContactAccountViewEffect.ToContactCreateResult -> onNavigateAfterSuccess()
            null -> { /* no-op */ }
        }
    }

    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // Form fields

            TextField(
                value = state.accountName.value,
                onValueChange = { viewModel.handleIntent(UpdateAccountName(it)) },
                label = { Text("Contact Name") },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            TextField(
                value = state.accountNumber.value,
                onValueChange = { viewModel.handleIntent(UpdateAccountNumber(it)) },
                label = { Text(stringResource(id = R.string.account_number_label)) },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = { viewModel.handleIntent(Submit) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(stringResource(id = R.string.create_contact_submit))
            }
        }
    }
}