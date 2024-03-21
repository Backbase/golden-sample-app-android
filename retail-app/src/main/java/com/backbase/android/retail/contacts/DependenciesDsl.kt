package com.backbase.android.retail.contacts

import com.backbase.android.client.contactmanagerclient2.api.ContactsApi
import com.backbase.android.retail.journey.contacts.ContactsUseCase
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin

// provides a clear set of dependencies required for the journey
// encapsulates details of used di for journey
fun contactsJourneyDependencies(block: ContactJourneyDependenciesScope.() -> Unit) {
    val dependencies = ContactJourneyDependenciesScope().apply(block)
    val module = module {
        factory<ContactsUseCase> {
            dependencies.contactsUseCaseProvider!!.invoke()
        }
        factory<ContactsApi> {
            dependencies.contactsApi!!.invoke()
        }
    }
    getKoin().loadModules(listOf(module))
}

class ContactJourneyDependenciesScope {
    var contactsUseCaseProvider: (() -> ContactsUseCase)? = null
    var contactsApi: (() -> ContactsApi)? = null
}
