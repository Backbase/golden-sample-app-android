package com.backbase.android.journey.contacts.presentation.screens.create_contact_account2.customer_extension

import com.backbase.android.journey.contacts.presentation.screens.create_contact.FieldValue


//Custom project implementation example below
sealed class CustomCreateContactAccountIntent2 {
    object CustomIntent: CustomCreateContactAccountIntent2()
    object CustomIntent2: CustomCreateContactAccountIntent2()
}

data class CustomCreateContactStateExtension (
    val accountAlias: FieldValue<String> = FieldValue("")
)

class CustomFragment: Fragment(){
    private val viewModel: CreateContactAccountViewModel2<CustomCreateContactStateExtension, CustomCreateContactAccountIntent2> by viewModels {
        CreateContactAccountViewModelFactory<CustomCreateContactStateExtension, CustomCreateContactAccountIntent2>(intentExtensionHandler = CustomIntentHandler)
    }

    private val viewModelNoCustomization: DefaultViewModel by viewModels {
        defaultViewModelFactory()
    }
}

object CustomIntentHandler: IntentExtensionHandler<CustomCreateContactStateExtension, CustomCreateContactAccountIntent2>{
    override fun invoke(
        currentState: CreateContactAccountState<CustomCreateContactStateExtension>,
        intentExtension: CustomCreateContactAccountIntent2,
        stateFlow: MutableStateFlow<CreateContactAccountState<CustomCreateContactStateExtension>>,
    ) {
        when (intentExtension) {
            CustomCreateContactAccountIntent2.CustomIntent -> TODO()
            CustomCreateContactAccountIntent2.CustomIntent2 -> TODO()
        }
    }
}