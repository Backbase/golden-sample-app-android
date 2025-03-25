package com.backbase.android.journey.contacts.domain.usecase

import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.model.PaginatedResult
import kotlinx.coroutines.flow.Flow

interface GetContactsUseCase {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int = DEFAULT_PAGE_SIZE
    ): Flow<PaginatedResult<ContactModel>>?

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }
}