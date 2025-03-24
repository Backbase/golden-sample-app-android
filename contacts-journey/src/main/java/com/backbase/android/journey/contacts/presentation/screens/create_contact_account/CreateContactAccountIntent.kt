package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

sealed class CreateContactAccountIntent {
    class ChangeAccountNumber(val accountNumber: String): CreateContactAccountIntent()
    class ChangeAccountName(val accountName: String): CreateContactAccountIntent()
    object SaveAccount: CreateContactAccountIntent()
}

sealed class CustomCreateContactAccountIntent {
    class DefaultIntent(val value: CreateContactAccountIntent): CustomCreateContactAccountIntent()
    object CustomIntent: CustomCreateContactAccountIntent()
}

sealed class CreateContactAccountIntent2<IntentExtension> {
    class ChangeAccountNumber<IntentExtension>(val accountNumber: String) : CreateContactAccountIntent2<IntentExtension>()
    class ChangeAccountName<IntentExtension>(val accountName: String) : CreateContactAccountIntent2<IntentExtension>()
    class SaveAccount<IntentExtension>() : CreateContactAccountIntent2<IntentExtension>()
    class GenericIntent<IntentExtension>(val data: IntentExtension) : CreateContactAccountIntent2<IntentExtension>()
}


sealed class CustomCreateContactAccountIntent2 {
    object CustomIntent: CustomCreateContactAccountIntent2()
    object CustomIntent2: CustomCreateContactAccountIntent2()
}