package com.backbase.android.journey.contacts.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import com.backbase.android.journey.contacts.data.MockContactsCreatorImpl
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel

@Composable
fun <ContactExtension, AccountExtension> ContactList(
    contacts: List<ContactModel<ContactExtension, AccountExtension>>,
    onContactClick: (ContactModel<ContactExtension, AccountExtension>) -> Unit,
    onSearch: (String) -> Unit,
    onLoadMore: () -> Unit, // For pagination
    isLoading: Boolean
) {
    Column(Modifier.fillMaxSize()) {
        ContactSearchBox(onSearch)
        LazyColumn(Modifier.weight(1f)) {
            items(contacts) { contact ->
                ContactListItem(contact, onContactClick)
            }
            if (isLoading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            } else if (contacts.isNotEmpty()) {
                item {
                    Button(onClick = onLoadMore, modifier = Modifier.fillMaxWidth()) {
                        Text("Load More")
                    }
                }
            }
        }
    }
}


@Composable
fun ContactSearchBox(onSearch: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") }
    OutlinedTextField(
        value = searchText,
        onValueChange = {
            searchText = it
            onSearch(it)
        },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
        placeholder = { Text("Search Contacts") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun <ContactExtension, AccountExtension> ContactListItem(contact: ContactModel<ContactExtension, AccountExtension>, onContactClick: (ContactModel<ContactExtension, AccountExtension>) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onContactClick(contact) }
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Placeholder for contact image (using Coil library)
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(contact.name, fontWeight = FontWeight.Bold)
            contact.alias?.let { Text(it) }
        }
    }
}


@Composable
fun ContactContainer(modifier: Modifier = Modifier) {
    val contact = remember {
        MockContactsCreatorImpl.createMockContactsList(1)[0]
    }
    ContactDetails(contact)
}

@Composable
fun <ContactExtension, AccountExtension> ContactDetails(contact: ContactModel<ContactExtension, AccountExtension>) {
    Column(Modifier.padding(16.dp)) {
        Text(contact.name, style = MaterialTheme.typography.h5)
        contact.alias?.let { Text("(${it})", style = MaterialTheme.typography.subtitle1) }
        Spacer(modifier = Modifier.height(8.dp))

        ContactDetailField("Address", "${contact.streetAndNumber}, ${contact.city}, ${contact.country}") // Simplified address
        // Add other address fields as needed

        Spacer(modifier = Modifier.height(16.dp))
        ContactAccountsList(contact.accounts)
    }
}

@Composable
fun ContactDetailField(label: String, value: String) {
    Row {
        Text(label, fontWeight = FontWeight.Bold)
        Text(value, Modifier.padding(start = 8.dp))
    }
}

@Composable
fun <AccountExtension> ContactAccountsList(accounts: List<AccountModel<AccountExtension>>) {
    Column(Modifier.padding(top = 8.dp)) {
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.12f))
        )
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
            text = "Accounts",
            style = MaterialTheme.typography.h6
        )
        if (accounts.isEmpty()) {
            Text("No accounts connected.")
        } else {
            LazyColumn {
                items(accounts) { account ->
                    ContactAccountItem(account)
                }
            }
        }
    }
}

@Composable
fun <AccountExtension> ContactAccountItem(account: AccountModel<AccountExtension>) {
    Column(Modifier.padding(horizontal = 0.dp, vertical = 8.dp)) {
        account.accountName?.let { Text(it, fontWeight = FontWeight.Bold) }
        account.iban?.let { Text("IBAN: $it") }
        account.accountNumber?.let { Text("Account Number: $it") }
        // Add other account details as needed
    }
}

//Create contact list preview
@Preview(showBackground = true)
@Composable
fun ContactsListPreview() {
    val mockContacts = MockContactsCreatorImpl.createMockContactsList(20)
    ContactList<Unit, Unit>(
        contacts = mockContacts,
        onContactClick = {},
        onSearch = {},
        onLoadMore = {},
        isLoading = false
    )
}

@Preview(showBackground = true)
@Composable
fun ContactDetailsPreview() {
    val mockContact = MockContactsCreatorImpl.createMockContactsList(1)[0]
    ContactDetails(contact = mockContact)
}

@Preview(showBackground = true)
@Composable
fun ContactDetailFieldPreview() {
    ContactDetailField(
        label = "Test Label",
        value = "Test Value"
    )
}

@Preview(showBackground = true)
@Composable
fun ContactAccountsListPreview() {
    val mockAccounts = MockContactsCreatorImpl.createMockContactsList(1)[0].accounts
    ContactAccountsList(accounts = mockAccounts)
}

@Preview(showBackground = true)
@Composable
fun ContactAccountItemPreview() {
    val mockAccount = MockContactsCreatorImpl.createMockContactsList(1)[0].accounts[0]
    ContactAccountItem(account = mockAccount)
}

@Preview(showBackground = true)
@Composable
fun EmptyContactAccountsListPreview() {
    ContactAccountsList<Unit>(accounts = emptyList())
}
