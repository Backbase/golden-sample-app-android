package com.backbase.golden_sample_app.koin

import android.app.Application
import com.backbase.android.client.contactmanagerclient2.api.ContactsApi
import com.backbase.android.retail.journey.contacts.ContactsConfiguration
import com.backbase.android.retail.journey.contacts.ContactsUseCase
import com.backbase.android.retail.journey.contacts.contactmanager_client_2.GenContactManagerClient2ContactsUseCase
import com.backbase.app_common.apiRoot
import org.koin.dsl.module
import java.net.URI

fun contactsModule() = module {
    single {
        ContactsApi(
            context = get<Application>(),
            moshi = get(),
            parser = get(),
            serverUri = URI("${apiRoot()}/contact-manager"),
            backbase = get()
        )
    }
    factory<ContactsUseCase> { GenContactManagerClient2ContactsUseCase(get()) }
    factory<ContactsConfiguration> { ContactsConfiguration {} }
}
