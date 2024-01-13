package com.backbase.golden_sample_app.koin

import com.backbase.android.Backbase
import com.backbase.android.client.contactmanagerclient2.api.ContactsApi
import com.backbase.android.retail.journey.contacts.ContactsConfiguration
import com.backbase.android.retail.journey.contacts.ContactsUseCase
import com.backbase.android.retail.journey.contacts.contactmanager_client_2.GenContactManagerClient2ContactsUseCase
import org.koin.dsl.module


fun contactsModule(backbase: Backbase) = module {
    factory<ContactsUseCase> { GenContactManagerClient2ContactsUseCase(get()) }
    factory<ContactsConfiguration> { ContactsConfiguration {} }
    single { backbase.getClient(ContactsApi::class.java) }
}
