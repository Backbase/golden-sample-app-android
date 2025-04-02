package com.backbase.android.journey.contacts.domain.usecase

import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository

interface SaveNewContactUseCase {
    suspend operator fun invoke(contact: ContactModel): Result<Unit>
}

class SaveNewContactUseCaseImpl (
    private val contactsRepository: ContactsRepository
) : SaveNewContactUseCase {
    override suspend fun invoke(
        contact: ContactModel
    ): Result<Unit> =
        contactsRepository.saveContact(contact)
} 