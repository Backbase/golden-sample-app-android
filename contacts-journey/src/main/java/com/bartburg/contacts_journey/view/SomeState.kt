package com.bartburg.contacts_journey.view

interface SomeState {
    val isLoading: Boolean
    val data: String?
    val error: String?
}

data class DefaultState(
    override val isLoading: Boolean = false,
    override val data: String? = null,
    override val error: String? = null
) : SomeState