package com.backbase.android.journey.contacts.presentation.screens.create_contact_account2

sealed class CreateContactAccountIntent2<IntentExtension> {
    class ChangeAccountNumber<IntentExtension>(val accountNumber: String) : CreateContactAccountIntent2<IntentExtension>()
    class ChangeAccountName<IntentExtension>(val accountName: String) : CreateContactAccountIntent2<IntentExtension>()
    class SaveAccount<IntentExtension>() : CreateContactAccountIntent2<IntentExtension>()
    class GenericIntent<IntentExtension>(val data: IntentExtension) : CreateContactAccountIntent2<IntentExtension>()
}