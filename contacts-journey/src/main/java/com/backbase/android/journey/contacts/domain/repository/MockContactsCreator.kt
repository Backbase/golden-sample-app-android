package com.backbase.android.journey.contacts.domain.repository

import com.backbase.android.client.gen2.contactmanagerclient2.model.ContactGetResponseBody
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import java.util.UUID
import kotlin.random.Random

/**
 * Interface for generating mock contacts for testing or demonstration purposes.
 */
interface MockContactsCreator {

    /**
     * Creates a list of mock [ContactModel] instances.
     *
     * @param count Number of mock contacts to generate.
     * @return A list of [ContactModel] instances.
     */
    fun createMockContactModels(count: Int = 100): List<ContactModel>

    fun createMockContactGetResponseBody(count: Int = 100): List<ContactGetResponseBody>

    companion object {
        const val DEFAULT_SEED = 42L
    }
}

/**
 * Implementation of [MockContactsCreator] that generates mock contact data
 * using a seeded [Random] instance for deterministic behavior.
 *
 * Useful for testing, prototyping, and UI previews.
 *
 * @property seed The seed used to initialize the random generator.
 */
class MockContactsCreatorImpl(
    seed: Long = MockContactsCreator.DEFAULT_SEED
) : MockContactsCreator {

    private val random = Random(seed)

    override fun createMockContactGetResponseBody(
        count: Int
    ): List<ContactGetResponseBody> {
        return listOf(
            ContactGetResponseBody{
                id = "2"
                name = "Jane Smith"
                emailId = "jane.smith@example.com"
                phoneNumber = "+0987654321"
                accounts = emptyList()
            }
        )
    }

    override fun createMockContactModels(count: Int): List<ContactModel> {
        return (1..count).map { index -> createMockContact(index) }
    }

    private fun createMockContact(index: Int): ContactModel {
        val firstName = firstNames[random.nextInt(firstNames.size)]
        val lastName = lastNames[random.nextInt(lastNames.size)]

        return ContactModel(
            id = generateDeterministicUUID(index),
            name = "$firstName $lastName",
            alias = if (random.nextBoolean()) firstName else null,
            accounts = createMockAccounts(),
            streetAndNumber = "Street ${random.nextInt(1, 200)} ${random.nextInt(1, 999)}",
            postalCode = "${random.nextInt(1000, 9999)}${('A'..'Z').random(random)}${('A'..'Z').random(random)}",
            city = cities[random.nextInt(cities.size)],
            country = countries[random.nextInt(countries.size)],
            stateOrArea = null,
            additionalLine1 = if (random.nextBoolean()) "Additional Info $index" else null,
            additionalLine2 = null
        )
    }

    private fun createMockAccounts(): List<AccountModel> {
        val accountCount = random.nextInt(1, 4)
        return (1..accountCount).map { accountIndex ->
            val bankName = bankNames[random.nextInt(bankNames.size)]
            val country = "NL"

            AccountModel(
                bankCountry = country,
                accountName = "Account $accountIndex",
                alias = if (random.nextBoolean()) "My Account $accountIndex" else null,
                iban = generateDeterministicIBAN(),
                accountNumber = generateDeterministicNumber(10),
                bankBranchCode = generateDeterministicNumber(4),
                accountType = accountTypes[random.nextInt(accountTypes.size)],
                bankName = bankName
            )
        }
    }

    private fun generateDeterministicIBAN(): String {
        val country = "NL"
        val bankCode = bankCodes.values.toList()[random.nextInt(bankCodes.size)]
        val checkDigits = (10 + random.nextInt(90)).toString().padStart(2, '0')
        val accountNumber = generateDeterministicNumber(10)
        return "$country$checkDigits$bankCode$accountNumber"
    }

    private fun generateDeterministicNumber(length: Int): String {
        return (1..length)
            .map { random.nextInt(10) }
            .joinToString("")
    }

    private fun generateDeterministicUUID(index: Int): String {
        val msb = random.nextLong() xor index.toLong()
        val lsb = random.nextLong() xor index.toLong()
        return UUID(msb, lsb).toString()
    }

    companion object {
        private val firstNames = listOf(
            "John", "Emma", "Mohammed", "Sarah", "David", "Lisa", "Michael",
            "Anna", "Yuki", "Carlos", "Sophie", "Ahmed", "Maria", "Lars"
        )

        private val lastNames = listOf(
            "Smith", "Johnson", "Brown", "Davis", "Wilson", "Lee", "Khan",
            "Garcia", "Tanaka", "Patel", "Müller", "Anderson", "Silva", "Jensen"
        )

        private val cities = listOf(
            "Amsterdam", "Rotterdam", "The Hague", "Utrecht", "Eindhoven",
            "Groningen", "Tilburg", "Almere", "Breda", "Nijmegen"
        )

        private val countries = listOf("NL", "BE", "DE", "FR", "GB", "ES", "IT")

        private val bankNames = listOf(
            "ABN AMRO", "ING Bank", "Rabobank", "Bunq", "SNS Bank"
        )

        private val bankCodes = mapOf(
            "ABN AMRO" to "ABNA",
            "ING Bank" to "INGB",
            "Rabobank" to "RABO",
            "Bunq" to "BUNQ",
            "SNS Bank" to "SNSB"
        )

        private val accountTypes = listOf("CHECKING", "SAVINGS", "BUSINESS")
    }
}