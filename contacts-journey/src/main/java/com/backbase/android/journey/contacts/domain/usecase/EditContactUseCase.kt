package com.backbase.android.journey.contacts.domain.usecase

import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository

interface EditContactUseCase {
    suspend operator fun invoke(contact: ContactModel)
}

class EditContactUseCaseImpl(
    private val contactsRepository: ContactsRepository
) : EditContactUseCase {
    override suspend operator fun invoke(contact: ContactModel) {
        contactsRepository.updateContact(contact)
    }
}