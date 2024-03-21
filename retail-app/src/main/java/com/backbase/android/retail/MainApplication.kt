package com.backbase.android.retail

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.client.contactmanagerclient2.api.ContactsApi
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.fido.FidoUafFacetUtils
import com.backbase.android.identity.journey.authentication.initAuthenticationJourney
import com.backbase.android.retail.contacts.contactsJourneyDependencies
import com.backbase.android.retail.journey.contacts.contactmanager_client_2.GenContactManagerClient2ContactsUseCase
import org.koin.android.ext.android.getKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            BBLogger.setLogLevel(BBLogger.LogLevel.DEBUG)
            BBLogger.debug("network", "Facet ID: <${FidoUafFacetUtils.getFacetID(this)}>")
        }
        initializeBackbase()
        setupContacts()
        setupUserContextApi()
        setupAuthClient()
        setupHttpHeaders()
        startKoinForApplication()
        initAuthenticationJourney()
        setupUserApi()
        setupJourneyDependencies()
    }

    private fun setupJourneyDependencies() {
        contactsJourneyDependencies {
            contactsUseCaseProvider = {
                GenContactManagerClient2ContactsUseCase(getKoin().get())
            }
            contactsApi = {
                Backbase.requireInstance().getClient(ContactsApi::class.java)
            }
        }
    }
}
