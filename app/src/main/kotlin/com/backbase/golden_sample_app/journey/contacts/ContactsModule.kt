package com.backbase.golden_sample_app.journey.contacts

import android.app.Application
import com.backbase.android.business.journey.contacts.contactsmanager_client_2.ContactsUseCaseImpl
import com.backbase.android.business.journey.contacts.usecase.ContactsUseCase
import com.backbase.android.clients.contactmanagerapiv2.api.ContactsApi
import com.backbase.app_common.apiRoot
import com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.mapper.CustomContactUiMapper
import com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.ui.CustomContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.net.URI

fun contactsModule() = module {
    single {
        ContactsApi(
            context = get<Application>(),
            moshi = get(),
            parser = get(),
            serverUri = URI("${apiRoot()}/contact-manager"),
            provider = get(),
            backbase = get()
        )
    }
    factory<ContactsUseCase> { ContactsUseCaseImpl(contactsApi = get()) }

    factory { CustomContactUiMapper() }
    viewModel { CustomContactsViewModel(useCase = get(), mapper = get()) }
}
