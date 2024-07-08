package com.backbase.golden_sample_app.koin

import com.backbase.android.Backbase
import com.backbase.android.client.contactmanagerclient2.api.ContactsApi
import com.backbase.android.retail.journey.contacts.ContactsConfiguration
import com.backbase.android.retail.journey.contacts.ContactsUseCase
import com.backbase.android.retail.journey.contacts.contactmanager_client_2.GenContactManagerClient2ContactsUseCase
import com.backbase.golden_sample_app.extend_journey.contacts.presentation.mapper.CustomContactUiMapper
import com.backbase.golden_sample_app.extend_journey.contacts.presentation.ui.CustomContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun contactsModule() = module {
    factory<ContactsUseCase> { GenContactManagerClient2ContactsUseCase(get()) }
    factory<ContactsConfiguration> { ContactsConfiguration {} }
    single { Backbase.requireInstance().getClient(ContactsApi::class.java) }

    factory { CustomContactUiMapper() }
    viewModel { CustomContactsViewModel(useCase = get(), mapper = get()) }
}
