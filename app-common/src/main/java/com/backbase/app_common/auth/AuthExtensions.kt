package com.backbase.app_common.auth

import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.client.BBIdentityAuthClientListener
import com.backbase.android.utils.net.response.Response

fun BBIdentityAuthClient.logOut() {
    invalidateTokens(object : BBIdentityAuthClientListener {
        override fun oAuth2AuthClientTokenInvalidated() {
            // Result ignored; fire and forget strategy
        }

        override fun oAuth2AuthClientAccessTokenRefreshed(p0: MutableMap<String, MutableList<String>>) {
            // Result ignored; fire and forget strategy
        }

        override fun oAuth2AuthClientAccessTokenRefreshFailed(p0: Response) {
            // Result ignored; fire and forget strategy
        }

        override fun oAuth2AuthClientTokenInvalidateFailed(p0: Response) {
            // Result ignored; fire and forget strategy
        }
    })
}
