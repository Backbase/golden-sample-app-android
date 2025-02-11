package com.bartburg.contacts_journey.view

sealed class SomeIntent {
    object LoadData : SomeIntent()
    data class SubmitData(val input: String) : SomeIntent()
    object Retry : SomeIntent()
}