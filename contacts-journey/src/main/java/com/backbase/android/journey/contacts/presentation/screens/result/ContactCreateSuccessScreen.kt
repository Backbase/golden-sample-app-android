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

/**
 * `ContactCreateSuccessScreen` is a composable function that displays a success screen
 * after a new contact has been successfully created.
 *
 * This screen informs the user that the contact creation was successful and provides
 * options for further actions:
 *
 * - **Create New Contact:**  A button that navigates the user back to the contact creation
 *   screen to add another contact. This button is only displayed if the
 *   `onNavigateToCreateContact` lambda is provided (not null).
 * - **Go to Contact List:** A button that navigates the user to the main contact list
 *   screen. This button is only displayed if the `onNavigateToContactList` lambda is
 *   provided (not null).
 *
 * **State Management:**
 * This screen is a purely presentational component and **does not contain a ViewModel**.
 * It does not interact with the domain or perform any business logic.  All navigation
 * actions are handled by the provided lambda functions, which are typically managed
 * by a higher-level component (e.g., a navigation host or a screen that *does* have a
 * ViewModel).  This separation of concerns keeps the UI layer clean and focused on
 * displaying information and handling user interactions.
 *
 * **Parameters:**
 * @param onNavigateToCreateContact An optional lambda function (nullable) that, when
 *                                 invoked, should navigate the user to the contact
 *                                 creation screen. If `null`, the "Create New Contact"
 *                                 button will not be displayed.
 * @param onNavigateToContactList An optional lambda function (nullable) that, when
 *                               invoked, should navigate the user to the contact list
 *                               screen. If `null`, the "Go to Contact List" button
 *                               will not be displayed.
 */
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