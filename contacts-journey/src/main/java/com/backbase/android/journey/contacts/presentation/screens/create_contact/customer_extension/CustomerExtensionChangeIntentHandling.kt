package com.backbase.android.journey.contacts.presentation.screens.create_contact.customer_extension

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.backbase.android.journey.contacts.ContactsRouting
import com.backbase.android.journey.contacts.data.service.MockContactsService
import com.backbase.android.journey.contacts.domain.repository.DefaultContactsRepository
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCaseImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewModel
import com.backbase.android.journey.contacts.presentation.screens.create_contact.createContactNavigation
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.CreateContactIntentHandlers
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.SaveContactIntentHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.Unit
import kotlin.getValue


//Below is an example of using the screen out of the box, but overriding business logic for some intent.

//Create your own custom intent handling
class CustomSaveContactIntentHandler: SaveContactIntentHandler<Unit>{
    override fun invoke(
        stateFlow: MutableStateFlow<CreateContactState<Unit>>,
        effectFlow: MutableSharedFlow<CreateContactViewEffect>,
        scope: CoroutineScope
    ) {
        // Custom logic here
    }
}

//Create your own factory (of inject dependency in case there is a dependency injection library
class CustomContactViewModelFactory(
    private val saveNewContactUseCase: SaveNewContactUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateContactViewModel<Unit>(
                saveNewContactUseCase = saveNewContactUseCase,
                stateFlow = MutableStateFlow(CreateContactState<Unit>()),
                effectFlow = MutableSharedFlow(),
                createContactIntentHandlers = CreateContactIntentHandlers<Unit>(
                    saveNewContactUseCase = saveNewContactUseCase,
                    saveContactHandler = CustomSaveContactIntentHandler()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

//Example usage, note that CustomContactAccountViewModelFactory is used.
class ContactsJourneyFragment : Fragment() {
    private val contactsRepository = DefaultContactsRepository(MockContactsService())

    private val createContactViewModel: CreateContactViewModel<Unit> by viewModels {
        CustomContactViewModelFactory(
            saveNewContactUseCase = SaveNewContactUseCaseImpl(contactsRepository)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ContactsRouting.startDestination(
                        routePrefix = ""
                    )
                ) {
                    createContactNavigation(
                        createContactViewModel = createContactViewModel,
                        onNavigateAfterSuccess = {
                            //navigation to success screen
                        }
                    )
                }
            }
        }
    }
}
