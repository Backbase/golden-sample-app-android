package com.backbase.android.journey.contacts.domain.usecase

import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository

interface SaveNewAccountUseCase {
    suspend operator fun invoke(account: AccountModel): Result<Unit>
}

class SaveNewAccountUseCaseImpl (
    private val contactsRepository: ContactsRepository
) : SaveNewAccountUseCase {
    override suspend fun invoke(
        account: AccountModel
    ): Result<Unit> {
        contactsRepository.saveNewAccount(account)
    }
} 