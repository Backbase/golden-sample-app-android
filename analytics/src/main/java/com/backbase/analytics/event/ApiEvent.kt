package com.backbase.analytics.event

data class ApiEvent(
    val uri: String,
    val requestMethod: String?,
    val body: String?,
    val requestHeaders: String?,
    val requestCode: Int,
    val responseHeaders: String?,
    val responseCode: Int,
    val byteResponse: String?,
    val statusText: String?,
    val errorMessage: String?
)
