package com.backbase.android.journey.contacts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.backbase.android.journey.contacts.data.repository.DefaultContactsRepository
import com.backbase.android.journey.contacts.data.service.MockContactsService
import com.backbase.android.journey.contacts.presentation.screens.detail.ContactDetailsIntent
import com.backbase.android.journey.contacts.presentation.screens.detail.ContactDetailsScreen
import com.backbase.android.journey.contacts.presentation.screens.detail.ContactDetailsViewModel
import com.backbase.android.journey.contacts.presentation.screens.detail.ContactDetailsViewModelFactory
import com.backbase.android.journey.contacts.presentation.screens.list.ContactListScreen
import com.backbase.android.journey.contacts.presentation.screens.list.ContactsListViewModel
import com.backbase.android.journey.contacts.presentation.screens.list.ContactsListViewModelFactory
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactScreen
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewModel
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewModelFactory
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCaseImpl

class ContactsJourneyFragment : Fragment() {
    private val contactsRepository = DefaultContactsRepository(MockContactsService())

    private val contactDetailsViewModel: ContactDetailsViewModel<Unit, Unit> by viewModels{
        ContactDetailsViewModelFactory<Unit, Unit>(contactsRepository)
    }

    private val createContactViewModel: CreateContactViewModel<Unit, Unit> by viewModels {
        CreateContactViewModelFactory(
            SaveNewContactUseCaseImpl(contactsRepository)
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
                    startDestination = "contacts"
                ) {
                    composable(ContactsRouting.List.ROUTE) {
                         val contactsListViewModel: ContactsListViewModel<Unit, Unit> by viewModels {
                            ContactsListViewModelFactory<Unit, Unit>(contactsRepository)
                        }
                        ContactListScreen(
                            viewModel = contactsListViewModel,
                            onNavigateToDetails = { contact ->
                                navController.navigate(
                                    ContactsRouting.Details.detailsUrl(contact.id)
                                )
                            },
                            onNavigateToCreate = {
                                navController.navigate(ContactsRouting.Create.ROUTE)
                            }
                        )
                    }
                    
                    composable(ContactsRouting.Details.ROUTE, arguments = listOf(
                        navArgument(ContactsRouting.Details.NAVARG_ID) { type = NavType.StringType }
                    )) { backStackEntry ->
                        contactDetailsViewModel.handleIntent(
                            ContactDetailsIntent.LoadContact(backStackEntry.arguments?.getString(ContactsRouting.Details.NAVARG_ID) ?: "")
                        )
                        ContactDetailsScreen(
                            viewModel = contactDetailsViewModel
                        )
                    }

                    composable(ContactsRouting.Create.ROUTE) {
                        CreateContactScreen(
                            viewModel = createContactViewModel,
                            onNavigateBack = {
                                navController.popBackStack()
                            },
                            onNavigateAfterSuccess = {
                                navController.navigate(ContactsRouting.List.ROUTE) {
                                    popUpTo(ContactsRouting.List.ROUTE) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}