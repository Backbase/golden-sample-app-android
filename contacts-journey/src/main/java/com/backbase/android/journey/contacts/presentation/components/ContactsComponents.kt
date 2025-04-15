package com.backbase.android.journey.contacts.presentation.components

import androidx.compose.runtime.Composable
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import io.ktor.websocket.Frame.Text

/**
 * A composable class that provides customizable UI components for displaying contact-related information.
 *
 * This class acts as a container for various composable functions, allowing developers to easily
 * swap out or customize the default implementations of common contact UI elements. By providing
 * these components as constructor parameters with default implementations, it promotes code
 * reusability and flexibility in building contact management screens.
 *
 * **Components Provided:**
 *
 * * **`contactListItem`**:
 * * A composable function responsible for rendering a single contact item in a list.
 * * **Parameters:**
 * * `contact: ContactModel`: The data model representing a single contact.
 * * `onContactClick: (ContactModel) -> Unit`: A lambda function that will be invoked
 * when the contact item is clicked. It receives the clicked `ContactModel` as a parameter.
 * * **Default Implementation:** Uses the `ContactListItem` composable 
 * to display the contact information and handle click events.
 *
 * * **`contactAccountItem`**:
 * * A composable function responsible for rendering a single account associated with a contact.
 * * **Parameters:**
 * * `account: AccountModel`: The data model representing an account.
 * * **Default Implementation:** Uses the `ContactAccountItem` composable 
 * to display the account information.
 *
 * * **`contactAccountsList`**:
 * * A composable function responsible for rendering a list of accounts associated with a contact.
 * * **Parameters:**
 * * `accounts: List<AccountModel>`: A list of `AccountModel` objects to be displayed.
 * * **Default Implementation:** Uses the `ContactAccountsList` composable 
 * to display the list of accounts.
 *
 * * **`contactDetailField`**:
 * * A composable function responsible for rendering a key-value pair representing a detail field
 * of a contact (e.g., phone number, email).
 * * **Parameters:**
 * * `label: String`: The label for the detail field (e.g., "Phone", "Email").
 * * `value: String`: The value of the detail field.
 * * **Default Implementation:** Uses the `ContactDetailField` composable 
 * to display the label and value.
 *
 * * **`searchBar`**:
 * * A composable function responsible for rendering a search bar that allows users to filter contacts.
 * * **Parameters:**
 * * `onSearch: (String) -> Unit`: A lambda function that will be invoked when the search
 * query changes. It receives the current search string as a parameter.
 * * **Default Implementation:** Uses the `SearchBar` composable 
 * to provide the search input field and handle search query updates.
 *
 * **How to Use:**
 *
 * You can create an instance of `ContactsComponents` and then use its properties to render the
 * respective UI elements within your Composables. If you need to customize the appearance or
 * behavior of any of these components, you can provide your own composable lambda when creating the
 * `ContactsComponents` instance.
 *
 */
class ContactsComponents(
    val contactListItem: @Composable (
        contact: ContactModel,
        onContactClick: (ContactModel) -> Unit)
    -> Unit = {contact, onContactClick ->
        ContactListItem(contact, onContactClick)
    },
    val contactAccountItem: @Composable (account: AccountModel) -> Unit = { account ->
        ContactAccountItem(account)
    },
    val contactAccountsList: @Composable (accounts: List<AccountModel>) -> Unit = { accounts ->
        ContactAccountsList(accounts)
    },
    val contactDetailField: @Composable (label: String, value: String) -> Unit = { label, value ->
        ContactDetailField(label, value)
    },
    val searchBar: @Composable (onSearch: (String) -> Unit) -> Unit = { onSearch ->
        SearchBar(onSearch)
    }
)

val customComponents = ContactsComponents(
    contactListItem = { contact, onContactClick ->
        Text(contact.name)
    }
)