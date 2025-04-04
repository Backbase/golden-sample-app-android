package com.backbase.android.journey.contacts.presentation.screens.result

import androidx.lifecycle.ViewModel

class ContactCreateSuccessViewModel(
): ViewModel() {


    public fun handleIntent(intent: ContactCreateSuccessIntent) {
        when (intent) {
            ContactCreateSuccessIntent.BackToContacts -> TODO()
            ContactCreateSuccessIntent.CreateNewContact -> TODO()
        }
    }

}