package com.backbase.android.journey.contacts.presentation.screens.create_contact_account.customer_extension

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
import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCase
import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCaseImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountState
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountViewEffect
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountViewModel
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.createContactAccountNavigation
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.handler.SaveAccountIntentHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.Unit
import kotlin.getValue

//Below is an example of using the screen out of the box, but overriding business logic for a single intent.

//Create your own custom intent handling
class CustomSaveAccountIntentHandler: SaveAccountIntentHandler<Unit>{
    override fun invoke(
        saveNewAccountUseCase: SaveNewAccountUseCase,
        stateFlow: MutableStateFlow<CreateContactAccountState<Unit>>,
        effectFlow: MutableSharedFlow<CreateContactAccountViewEffect>,
        scope: CoroutineScope){
        //Do something else
    }
}

//Create your own factory (of inject dependency in case there is a dependency injection library
class CustomContactAccountViewModelFactory(
    private val saveNewAccountUseCase: SaveNewAccountUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateContactAccountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateContactAccountViewModel<Unit>(
                saveNewAccountUseCase = saveNewAccountUseCase,
                saveAccountIntentHandler = CustomSaveAccountIntentHandler()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

//Example usage, note that CustomContactAccountViewModelFactory is used.
class ContactsJourneyFragment : Fragment() {
    private val contactsRepository = DefaultContactsRepository(MockContactsService())

    private val createContactViewModel: CreateContactAccountViewModel<Unit> by viewModels {
        CustomContactAccountViewModelFactory(
            saveNewAccountUseCase = SaveNewAccountUseCaseImpl(contactsRepository)
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
                    createContactAccountNavigation(
                        createContactAccountViewModel = createContactViewModel,
                    )
                }
            }
        }
    }
}
