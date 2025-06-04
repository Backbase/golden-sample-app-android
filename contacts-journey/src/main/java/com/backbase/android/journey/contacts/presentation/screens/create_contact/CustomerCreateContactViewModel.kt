package com.backbase.android.journey.contacts.presentation.screens.create_contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.journey.contacts.ContactsRouting
import com.backbase.android.journey.contacts.data.repository.MockContactsRepository
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCaseImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateAccountNumber
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect.ToContactCreatedResult
import com.backbase.android.journey.contacts.presentation.util.FieldValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class CustomCreateContactStateExtension (
    val accountAlias: FieldValue<String> = FieldValue("")
)

class UpdateAlias(val accountAlias: String): CreateContactIntent

val updateAliasIntentHandler = IntentHandler<UpdateAlias, CreateContactState<CustomCreateContactStateExtension>, CreateContactViewEffect> {
    updateUiState { currentState ->
        val stateExtension = currentState.extension?: CustomCreateContactStateExtension()
        currentState.copy(extension = stateExtension.copy(accountAlias = FieldValue(intent.accountAlias)))
    }
}

@Composable
fun CustomCreateContactScreen(
    state: CreateContactState<CustomCreateContactStateExtension>,
    onIntent: (CreateContactIntent) -> Unit,
) {
    Column {
        TextField(
            value = state.extension?.accountAlias?.value ?: "",
            onValueChange = { onIntent(UpdateAlias(it)) },
            label = { Text("Account alias") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        CreateContactScreen(state, onIntent)
    }
}

@Preview
@Composable
fun CustomCreateContactScreenPreview() {
    CustomCreateContactScreen(
        state = CreateContactState<CustomCreateContactStateExtension>(),
        onIntent = {}
    )
}

class ExampleFragment : Fragment() {

    private val saveNewContactUseCase = SaveNewContactUseCaseImpl(
        contactsRepository = MockContactsRepository()
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = ComposeView(requireContext()).apply {
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = ContactsRouting.startDestination(routePrefix = "")
            ) {
                createContactNavigation(
                    content = { state, onIntent -> CustomCreateContactScreen(state, onIntent) },
                    viewModelFactory = CreateContactViewModelFactory(
                        // Backbase out-of-the-box implementation
                        updateNameIntentHandler = updateNameIntentHandler(),
                        updateEmailIntentHandler = updateEmailIntentHandler(),
                        // Custom IntentHandler for handling a out-of-the-box intent
                        updateAccountNumberIntentHandler = IntentHandler<UpdateAccountNumber, CreateContactState<CustomCreateContactStateExtension>, CreateContactViewEffect> {
                            launch(Dispatchers.IO) {
                                delay(timeMillis = 1000L)
                                updateUiState { currentState -> currentState.copy(name = currentState.accountNumber.copy(value = intent.value)) }
                            }
                        },
                        // Backbase out-of-the-box IntentHandler with a custom use case implementation
                        saveContactIntentHandler = saveContactIntentHandler(saveNewContactUseCase = saveNewContactUseCase),
                        // Custom IntentHandler for handling custom intents
                        intentHandlers = listOf(updateAliasIntentHandler)
                    ),
                    onEffect = { effect ->
                        when (effect) {
                            is ToContactCreatedResult -> navController.navigate(ContactsRouting.Create.route(routePrefix = ""))
                            null -> { /* no-op */ }
                        }
                    },
                )
            }
        }
    }
}
