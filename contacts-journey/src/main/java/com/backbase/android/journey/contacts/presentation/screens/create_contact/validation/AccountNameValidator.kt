package com.backbase.android.journey.contacts.presentation.screens.create_contact.validation

object AccountNameValidator {
    fun validate(name: String): NameValidationResult {
        return when {
            name.isBlank() -> NameValidationResult.Empty
            !validateLettersOnly(name) -> NameValidationResult.IllegalCharacters
            else -> NameValidationResult.Valid
        }
    }

    private fun validateLettersOnly(name: String): Boolean {
        return name.matches(Regex("^[A-Za-zÀ-ÖØ-öø-ÿ\\p{InArabic}\\s'-]+$"))
    }
}

sealed class NameValidationResult {
    object Valid : NameValidationResult()
    object IllegalCharacters : NameValidationResult()
    object Empty : NameValidationResult()
}