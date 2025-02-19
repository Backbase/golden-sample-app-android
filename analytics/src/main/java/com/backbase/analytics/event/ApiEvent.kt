package com.backbase.analytics.event

import java.net.URI

sealed class ApiEvent(val name: String, val value: String) {
    class ApiMethodEvent(method: String?) : ApiEvent("api_method", method ?: "No method")
    class ApiUrlEvent(url: URI?) : ApiEvent("api_url", url.toString())
    class ApiBodyEvent(body: String?) : ApiEvent("api_body", body ?: "No body")
    class ApiResponseCodeEvent(responseCode: Int) : ApiEvent("api_response_code", responseCode.toString())
    class ApiSuccessEvent(success: String?) : ApiEvent("api_success", success ?: "No payload")
    class ApiErrorEvent(error: String?) : ApiEvent("api_error", error ?: "No error")
}
