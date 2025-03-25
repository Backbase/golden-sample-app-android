package com.backbase.android.journey.contacts.domain.usecase

import android.provider.ContactsContract
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository

interface DeleteContactUseCase {
    suspend operator fun invoke(contactId: String)
}

class DeleteContactUseCaseImpl(
    private val contactsRepository: ContactsRepository
) : DeleteContactUseCase {
    override suspend operator fun invoke(contactId: String) {
        contactsRepository.deleteContact(contactId)
    }
}