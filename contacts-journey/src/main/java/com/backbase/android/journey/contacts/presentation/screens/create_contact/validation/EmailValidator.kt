package com.backbase.android.journey.contacts.presentation.screens.create_contact.validation

object EmailValidator {

    fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
        return emailRegex.matches(email)
    }

}