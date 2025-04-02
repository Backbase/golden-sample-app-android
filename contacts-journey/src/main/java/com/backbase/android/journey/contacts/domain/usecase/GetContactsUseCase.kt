package com.backbase.android.journey.contacts.domain.usecase

import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.model.PaginatedResult
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow

interface GetContactsUseCase {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int = DEFAULT_PAGE_SIZE,
        query: String? = null
    ): Flow<PaginatedResult<ContactModel>>?

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }
}

class GetContactsUseCaseImpl(
    private val contactsRepository: ContactsRepository
): GetContactsUseCase {
    override suspend operator fun invoke(
        page: Int,
        pageSize: Int,
        query: String?
    ): Flow<PaginatedResult<ContactModel>>? {
        contactsRepository.getContacts(
            page = page,
            pageSize = pageSize
        )
    }

}