package com.backbase.android.journey.contacts.domain.usecase

import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository

interface SaveNewContactUseCase<ContactExtension, AccountExtension> {
    suspend operator fun invoke(contact: ContactModel<ContactExtension, AccountExtension>)
}

class SaveNewContactUseCaseImpl<ContactExtension, AccountExtension> (
    private val contactsRepository: ContactsRepository<ContactExtension, AccountExtension>
) : SaveNewContactUseCase<ContactExtension, AccountExtension> {
    override suspend fun invoke(
        contact: ContactModel<ContactExtension, AccountExtension>
    ) {
        contactsRepository.saveContact(contact)
    }
} 