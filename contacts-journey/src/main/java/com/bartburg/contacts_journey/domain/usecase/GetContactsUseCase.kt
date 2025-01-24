package com.bartburg.contacts_journey.domain.usecase

import com.bartburg.contacts_journey.domain.model.ContactModel
import com.bartburg.contacts_journey.domain.model.PaginatedResult
import com.bartburg.contacts_journey.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetContactsUseCase<T : ContactModel> {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int = DEFAULT_PAGE_SIZE
    ): Flow<PaginatedResult<T>>?

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }
}

class GetContactsUseCaseImpl @Inject constructor(
    private val contactsRepository: ContactsRepository
) : GetContactsUseCase<ContactModel>  {
    override suspend operator fun invoke(
        page: Int,
        pageSize: Int
    ): Flow<PaginatedResult<ContactModel>>? {
        return contactsRepository.getContacts(
            page = page,
            pageSize = pageSize
        ).map { contacts ->
                PaginatedResult(
                    items = contacts,
                    page = page,
                    pageSize = pageSize,
                    hasMore = contacts.size == pageSize
                )
        }
    }
}