package com.backbase.android.journey.contacts.presentation.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.repository.MockContactsCreatorImpl
import com.backbase.android.journey.contacts.presentation.components.ContactAccountsList
import com.backbase.android.journey.contacts.presentation.components.ContactDetailField

@Composable
fun ContactDetailsScreen(
    viewModel: ContactDetailsViewModel
) {
    val state by viewModel.state.collectAsState()

    state.contact?.let { contact ->
        ContactDetails(contact = contact)
    }
}

@Composable
fun  ContactDetails(
    contact: ContactModel
) {
    Column(Modifier.padding(16.dp)) {
        Text(contact.name, style = MaterialTheme.typography.h5)
        contact.alias?.let { Text("(${it})", style = MaterialTheme.typography.subtitle1) }
        Spacer(modifier = Modifier.height(8.dp))

        ContactDetailField("Address", "${contact.streetAndNumber}, ${contact.city}, ${contact.country}")

        Spacer(modifier = Modifier.height(16.dp))
        ContactAccountsList(contact.accounts)
    }
}


@Preview(showBackground = true)
@Composable
fun ContactDetailsPreview() {
    val mockContact = MockContactsCreatorImpl().createMockContactModels(1)[0]
    ContactDetails(contact = mockContact)
}