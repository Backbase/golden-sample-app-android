package com.backbase.android.journey.contacts.data.repository

import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository
import com.backbase.android.journey.contacts.domain.repository.MockContactsCreator
import com.backbase.android.journey.contacts.domain.repository.MockContactsCreatorImpl
import java.util.UUID
import kotlin.random.Random

class MockContactsRepository(
    private val mockContactsCreator: MockContactsCreator = MockContactsCreatorImpl()
) : ContactsRepository {
    private var cachedContacts: List<ContactModel>? = null
    private var lastCacheTime: Long = 0
    private val CACHE_DURATION = 5 * 60 * 1000 // 5 minutes in milliseconds

    override suspend fun getContacts(page: Int, pageSize: Int, query: String?): Result<List<ContactModel>> {
        return try {
            val contacts = getCachedContacts() ?: mockContactsCreator.createMockContactModels().also { cacheContacts(it) }
            val filteredContacts = contacts.filter { contact ->
                query?.let { q ->
                    contact.name.contains(q, ignoreCase = true) ||
                    contact.alias?.contains(q, ignoreCase = true) == true ||
                    contact.accounts.any { it.accountNumber?.contains(q, ignoreCase = true) == true }
                } ?: true
            }
            
            val startIndex = (page - 1) * pageSize
            val endIndex = minOf(startIndex + pageSize, filteredContacts.size)
            
            if (startIndex >= filteredContacts.size) {
                Result.success(emptyList())
            } else {
                Result.success(filteredContacts.subList(startIndex, endIndex))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getContactDetails(contactId: String): Result<ContactModel> {
        return try {
            val contact = getCachedContacts()?.find { it.id == contactId }
            contact?.let { 
                Result.success(it)
            } ?: Result.failure(NoSuchElementException("Contact not found"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveContact(contact: ContactModel): Result<Unit> {
        return try {
            val currentContacts = getCachedContacts()?.toMutableList() ?: mutableListOf()
            currentContacts.add(contact)
            cacheContacts(currentContacts)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateContact(contact: ContactModel): Result<Unit> {
        return try {
            val currentContacts = getCachedContacts()?.toMutableList() 
                ?: return Result.failure(Exception("Cache not initialized"))
            val index = currentContacts.indexOfFirst { it.id == contact.id }
            if (index != -1) {
                currentContacts[index] = contact
                cacheContacts(currentContacts)
                Result.success(Unit)
            } else {
                Result.failure(NoSuchElementException("Contact not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteContact(contactId: String): Result<Unit> {
        return try {
            val currentContacts = getCachedContacts()?.toMutableList() 
                ?: return Result.failure(Exception("Cache not initialized"))
            if (currentContacts.removeIf { it.id == contactId }) {
                cacheContacts(currentContacts)
                Result.success(Unit)
            } else {
                Result.failure(NoSuchElementException("Contact not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCachedContacts(): List<ContactModel>? = cachedContacts

    override fun cacheContacts(contacts: List<ContactModel>) {
        cachedContacts = contacts
        lastCacheTime = System.currentTimeMillis()
    }

    override fun isCacheExpired(): Boolean {
        return System.currentTimeMillis() - lastCacheTime > CACHE_DURATION
    }

    override fun clearCache() {
        cachedContacts = null
        lastCacheTime = 0
    }

    private fun generateInitialContacts(): List<ContactModel> {
        val firstNames = listOf("John", "Emma", "Mohammed", "Sarah", "David", "Lisa", "Michael", "Anna", "Yuki", "Carlos")
        val lastNames = listOf("Smith", "Johnson", "Brown", "Davis", "Wilson", "Lee", "Khan", "Garcia", "Tanaka", "Patel")
        val cities = listOf("Amsterdam", "Rotterdam", "The Hague", "Utrecht", "Eindhoven", "Groningen", "Tilburg")
        val countries = listOf("NL", "BE", "DE", "FR", "GB", "ES", "IT")
        val bankNames = listOf("ABN AMRO", "ING Bank", "Rabobank", "Bunq", "SNS Bank")
        
        return (1..100).map { 
            val firstName = firstNames.random()
            val lastName = lastNames.random()
            ContactModel(
                id = UUID.randomUUID().toString(),
                name = "$firstName $lastName",
                alias = if (Random.nextBoolean()) firstName else null,
                accounts = generateRandomAccounts(bankNames),
                streetAndNumber = "Street ${Random.nextInt(1, 200)} ${Random.nextInt(1, 999)}",
                postalCode = "${Random.nextInt(1000, 9999)}${('A'..'Z').random()}${('A'..'Z').random()}",
                city = cities.random(),
                country = countries.random(),
                stateOrArea = null,
                additionalLine1 = if (Random.nextBoolean()) "Additional Info" else null,
                additionalLine2 = null
            )
        }
    }

    private fun generateRandomAccounts(bankNames: List<String>): List<AccountModel> {
        return (1..Random.nextInt(1, 4)).map {
            val bankName = bankNames.random()
            val country = "NL"
            AccountModel(
                bankCountry = country,
                accountName = "Account $it",
                alias = if (Random.nextBoolean()) "My Account $it" else null,
                iban = generateMockIBAN(country),
                accountNumber = generateAccountNumber(),
                bankBranchCode = generateBranchCode(),
                accountType = listOf("CHECKING", "SAVINGS", "BUSINESS").random(),
                bankName = bankName
            )
        }
    }

    private fun generateMockIBAN(country: String): String {
        val bankCodes = mapOf(
            "ABN AMRO" to "ABNA",
            "ING Bank" to "INGB",
            "Rabobank" to "RABO",
            "Bunq" to "BUNQ",
            "SNS Bank" to "SNSB"
        )
        val bankCode = bankCodes.values.random()
        val checkDigits = (10..99).random()
        val accountNumber = (0..9).shuffled().take(10).joinToString("")
        return "$country$checkDigits$bankCode$accountNumber"
    }

    private fun generateAccountNumber(): String {
        return (0..9).shuffled().take(10).joinToString("")
    }

    private fun generateBranchCode(): String {
        return (0..9).shuffled().take(4).joinToString("")
    }

    companion object {
        @Volatile
        private var instance: MockContactsRepository? = null
        
        fun getInstance(): MockContactsRepository {
            return instance ?: synchronized(this) {
                instance ?: MockContactsRepository().also { instance = it }
            }
        }
    }
} 