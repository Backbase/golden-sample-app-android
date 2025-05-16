package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.journey.contacts.ContactsRouting
import com.backbase.android.journey.contacts.data.repository.MockContactsRepository
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCaseImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateAccountNumber
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect.ToContactCreateResult
import com.backbase.android.journey.contacts.presentation.util.FieldValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class CustomCreateContactStateExtension (
    val accountAlias: FieldValue<String> = FieldValue("")
)

object CustomCreateContactViewEffect: CreateContactViewEffect

class UpdateAlias(val accountAlias: String): CreateContactIntent

class CustomCreateContactViewModelFactory() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = CreateContactViewModel<CustomCreateContactStateExtension>(
        // If a client wants to use the default implementation from backbase
        updateNameIntentHandler = updateNameIntentHandler(),
        updateEmailIntentHandler = updateEmailIntentHandler(),
        // If a client wants to override the default implementation
        updateAccountNumberIntentHandler = IntentHandler<UpdateAccountNumber, CreateContactState<CustomCreateContactStateExtension>, CreateContactViewEffect> {
            launch(Dispatchers.IO) {
                delay(timeMillis = 1000L)
                updateUiState { currentState -> currentState.copy(name = currentState.accountNumber.copy(value = intent.value)) }
            }
        },
        // If a client wants to keep the default implementation but override the async operation
        saveContactIntentHandler = saveContactIntentHandler(saveNewContactUseCase = SaveNewContactUseCaseImpl(contactsRepository = MockContactsRepository())),
        // If a client wants to handle a custom intent
        intentHandlers = arrayOf(
            IntentHandler<UpdateAlias, CreateContactState<CustomCreateContactStateExtension>, CreateContactViewEffect> {
                updateUiState { currentState ->
                    currentState.copy(extension = (currentState.extension?: CustomCreateContactStateExtension()).copy(accountAlias = FieldValue(intent.accountAlias)))
                }
                launch { launchEffect(effect = CustomCreateContactViewEffect) }
            }
        ),
    ) as T
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

@Suppress("UNCHECKED_CAST")
fun NavGraphBuilder.createCustomContactNavigation(
    onNavigateAfterSuccess: () -> Unit,
    routePrefix: String = ""
){
    composable(routePrefix + ContactsRouting.Create.route(routePrefix)) {
        val viewModel = CustomCreateContactViewModelFactory()
            .create(CreateContactViewModel::class.java) as CreateContactViewModel<CustomCreateContactStateExtension>
        val state by viewModel.uiState.collectAsState()
        val effect by viewModel.effects.collectAsState(initial = null)

        LaunchedEffect(effect) {
            when (effect) {
                is ToContactCreateResult -> onNavigateAfterSuccess()
                is CustomCreateContactViewEffect -> { /* no-op */ }
                null -> { /* no-op */ }
            }
        }

        CustomCreateContactScreen(state) { intent -> viewModel.handle(intent) }
    }
}