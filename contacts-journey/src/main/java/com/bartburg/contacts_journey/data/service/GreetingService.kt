package com.bartburg.contacts_journey.data.service

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class GreetingService(
    private val client: HttpClient = HttpClient()
) {
    suspend fun greeting(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }
}