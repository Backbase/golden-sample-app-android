package com.backbase.android.journey.contacts.domain.usecase

import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository

interface GetContactDetailsUseCase<ContactExtension, AccountExtension> {
    suspend operator fun invoke(contactId: String): Result<ContactModel<ContactExtension, AccountExtension>>
}

class GetContactDetailsUseCaseImpl<ContactExtension, AccountExtension> (
    private val contactsRepository: ContactsRepository<ContactExtension, AccountExtension>
) : GetContactDetailsUseCase <ContactExtension, AccountExtension> {
    override suspend operator fun invoke(contactId: String): Result<ContactModel<ContactExtension, AccountExtension>> {
        return contactsRepository.getContactDetails(contactId)
    }
} 