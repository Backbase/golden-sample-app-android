class MockContactsCreatorImpl : MockContactsCreator {
    private val random = Random(System.currentTimeMillis())

    override fun createRandomContact(): Contact {
        return Contact(
            id = random.nextLong(),
            // ... rest of the contact creation
        )
    }

    override fun createRandomContacts(count: Int): List<Contact> {
        return List(count) { createRandomContact() }
    }

    private fun generateRandomName(): String {
        val firstNameIndex = random.nextInt(firstNames.size)
        val lastNameIndex = random.nextInt(lastNames.size)
        // ... existing code ...
    }

    private fun generateRandomPhoneNumber(): String {
        return buildString {
            append("+1-")
            repeat(3) { append(random.nextInt(10)) }
            append("-")
            repeat(3) { append(random.nextInt(10)) }
            append("-")
            repeat(4) { append(random.nextInt(10)) }
        }
    }

    private fun generateRandomEmail(name: String): String {
        val domains = listOf("gmail.com", "yahoo.com", "hotmail.com", "outlook.com")
        return "${name.lowercase().replace(" ", ".")}@${domains[random.nextInt(domains.size)]}"
    }
} 