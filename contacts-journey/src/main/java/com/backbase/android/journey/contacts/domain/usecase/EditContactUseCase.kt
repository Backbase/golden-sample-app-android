package com.backbase.android.journey.contacts.domain.usecase

import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository

interface EditContactUseCase<ContactExtension, AccountExtension> {
    suspend operator fun invoke(contact: ContactModel<ContactExtension, AccountExtension>)
}

class EditContactUseCaseImpl<ContactExtension, AccountExtension>(
    private val contactsRepository: ContactsRepository<ContactExtension, AccountExtension>
) : EditContactUseCase<ContactExtension, AccountExtension> {
    override suspend operator fun invoke(contact: ContactModel<ContactExtension, AccountExtension>) {
        contactsRepository.updateContact(contact)
    }
}