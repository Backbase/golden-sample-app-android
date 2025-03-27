package com.backbase.android.journey.contacts.presentation.screens.create_contact_account2.customer_extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account2.CreateContactAccountState
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account2.CreateContactAccountIntentExtensionHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account2.CreateContactAccountViewModel2
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account2.CreateContactAccountViewModelFactory
import com.backbase.android.journey.contacts.presentation.util.FieldValue
import kotlinx.coroutines.flow.MutableStateFlow


//Custom project implementation example below
sealed class CustomCreateContactAccountIntent2 {
    class UpdateAccountAlias(val value: String): CustomCreateContactAccountIntent2()
}

data class CustomCreateContactStateExtension (
    val accountAlias: FieldValue<String> = FieldValue("")
)

class CustomFragment: Fragment(){
    private val viewModel: CreateContactAccountViewModel2<CustomCreateContactStateExtension, CustomCreateContactAccountIntent2> by viewModels {
        CreateContactAccountViewModelFactory<CustomCreateContactStateExtension, CustomCreateContactAccountIntent2>(intentExtensionHandler = CustomIntentHandler)
    }
}

object CustomIntentHandler: CreateContactAccountIntentExtensionHandler<CustomCreateContactStateExtension, CustomCreateContactAccountIntent2>{
    override fun invoke(
        currentState: CreateContactAccountState<CustomCreateContactStateExtension>,
        intentExtension: CustomCreateContactAccountIntent2,
        stateFlow: MutableStateFlow<CreateContactAccountState<CustomCreateContactStateExtension>>,
    ) {
        when (intentExtension) {
            is CustomCreateContactAccountIntent2.UpdateAccountAlias -> {
                stateFlow.value = currentState.copy(
                    extension = currentState.extension?.copy(
                        accountAlias = FieldValue(intentExtension.value)
                    )
                )
            }
        }
    }
}