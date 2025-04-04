package com.backbase.android.journey.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.backbase.android.journey.contacts.domain.repository.DefaultContactsRepository
import com.backbase.android.journey.contacts.data.service.MockContactsService
import com.backbase.android.journey.contacts.presentation.screens.detail.ContactDetailsViewModel
import com.backbase.android.journey.contacts.presentation.screens.detail.ContactDetailsViewModelFactory
import com.backbase.android.journey.contacts.presentation.screens.list.ContactsListViewModel
import com.backbase.android.journey.contacts.presentation.screens.list.ContactsListViewModelFactory
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewModel
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewModelFactory
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCaseImpl

class ContactsJourneyFragment : Fragment() {
    private val contactsRepository = DefaultContactsRepository(MockContactsService())

    private val contactDetailsViewModel: ContactDetailsViewModel by viewModels{
        ContactDetailsViewModelFactory(contactsRepository)
    }

    private val createContactViewModel: CreateContactViewModel by viewModels {
        CreateContactViewModelFactory(
            SaveNewContactUseCaseImpl(contactsRepository)
        )
    }

    private val contactsListViewModel: ContactsListViewModel by viewModels {
        ContactsListViewModelFactory(contactsRepository)
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
                    startDestination = ContactsRouting.startDestination
                ) {
                    contactsJourneyNavigation(
                        navController = navController,
                        contactsListViewModel = contactsListViewModel,
                        createContactViewModel = createContactViewModel,
                        contactDetailsViewModel = contactDetailsViewModel
                    )
                }
            }
        }
    }
}