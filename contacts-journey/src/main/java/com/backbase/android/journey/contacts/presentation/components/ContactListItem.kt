package com.backbase.android.journey.contacts.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.backbase.android.journey.contacts.domain.model.ContactModel

@Composable
fun <ContactExtension, AccountExtension> ContactListItem(
    contact: ContactModel<ContactExtension, AccountExtension>,
    onContactClick: (ContactModel<ContactExtension, AccountExtension>) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onContactClick(contact) }
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(contact.name, fontWeight = FontWeight.Bold)
            contact.alias?.let { Text(it) }
        }
    }
}