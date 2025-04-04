package com.backbase.android.journey.contacts.presentation.screens.result

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.backbase.android.journey.contacts.R


@Composable
fun ContactCreateSuccessScreen(
    onNavigateToCreateContact: (() -> Unit)?,
    onNavigateToContactList: (() -> Unit)?,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.contacts_create_success_title),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.contacts_create_success_message),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Button to navigate to create contact
            if (onNavigateToCreateContact != null) {
                Button(
                    onClick = { onNavigateToCreateContact() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.contacts_create_success_button_create_new))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button to navigate to contact list
            if (onNavigateToContactList != null) {
                Button(
                    onClick = { onNavigateToContactList() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.contacts_create_success_button_go_to_list))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactCreateSuccessScreenPreview() {
    ContactCreateSuccessScreen(
        onNavigateToCreateContact = {},
        onNavigateToContactList = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ContactCreateSuccessScreenPreview_NoCreate() {
    ContactCreateSuccessScreen(
        onNavigateToCreateContact = null,
        onNavigateToContactList = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ContactCreateSuccessScreenPreview_NoList() {
    ContactCreateSuccessScreen(
        onNavigateToCreateContact = {},
        onNavigateToContactList = null
    )
}