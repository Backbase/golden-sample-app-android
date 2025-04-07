package com.backbase.android.journey.contacts.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.repository.MockContactsCreatorImpl

@Composable
fun  ContactAccountsList(accounts: List<AccountModel>) {
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
fun  ContactAccountItem(account: AccountModel) {
    Column(Modifier.padding(horizontal = 0.dp, vertical = 8.dp)) {
        account.accountName?.let { Text(it, fontWeight = FontWeight.Bold) }
        account.iban?.let { Text("IBAN: $it") }
        account.accountNumber?.let { Text("Account Number: $it") }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactAccountsListPreview() {
    val mockAccounts = MockContactsCreatorImpl().createMockContactModels(1)[0].accounts
    ContactAccountsList(accounts = mockAccounts)
}

@Preview(showBackground = true)
@Composable
fun ContactAccountItemPreview() {
    val mockAccount = MockContactsCreatorImpl().createMockContactModels(1)[0].accounts[0]
    ContactAccountItem(account = mockAccount)
}

@Preview(showBackground = true)
@Composable
fun EmptyContactAccountsListPreview() {
    ContactAccountsList(accounts = emptyList())
} 