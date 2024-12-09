package com.backbase.network

object UserAgentUtils {

    private const val APP_NAME = "Backbase"
    private const val Current_USER_AGENT = "http.agent"

    /**
     * Generates a custom user agent defined by the following parameters
     *
     * @param appName The name of the app that is using the SDK
     * @param appVersion The version of the app that is using the SDK
     * @param sdkVersion The SDK version
     * @param currentUserAgent Current user agent. The new user agent gets information from it.
     * @return A new custom user agent.
     */
    fun generate(
        appName: String = APP_NAME,
        appVersion: String = "1.0", // TODO: get the app version from somewhere
        sdkVersion: String = "1.0", // TODO: get the sdk version from somewhere
        currentUserAgent: String? = System.getProperty(Current_USER_AGENT)
    ): String {
        val userAgentBuilder = StringBuilder()
        userAgentBuilder
            .append("CxpMobile/")
            .append(sdkVersion)
            .append(' ')
        if (currentUserAgent != null) {
            userAgentBuilder
                .append(
                    currentUserAgent.substring(
                        currentUserAgent.indexOf('('),
                        currentUserAgent.indexOf(')') + 1
                    )
                ).append(' ')
        }
        userAgentBuilder
            .append(appName)
            .append('/')
            .append(appVersion)
        return userAgentBuilder.toString()
    }
}
