package com.backbase.golden_sample_app.user

import com.backbase.android.client.usermanagerclient2.api.UserProfileManagementApi
import com.backbase.android.client.usermanagerclient2.model.UserProfile
import com.backbase.android.clients.common.CallResult.Success
import com.backbase.android.clients.common.coroutines.executeAsSuspended
import com.backbase.android.secure.storage.ReadValueResult
import com.backbase.android.secure.storage.SecureStorage
import com.backbase.golden_sample_app.Sdk

internal suspend fun UserProfileManagementApi.getOwnUserProfileOrNull(): UserProfile? = getOwnUserProfile()
    .executeAsSuspended()
    .let { response -> if (response is Success) response.data else null }

internal suspend fun SecureStorage.getOwnUserProfileOrNull(): UserProfile? {
    val readValueResult = readValue(OWN_USER_PROFILE_KEY)
    if (readValueResult !is ReadValueResult.Found) return null

    return Sdk
        .moshi
        .adapter(UserProfile::class.java)
        .fromJson(readValueResult.value)
}

internal suspend fun SecureStorage.upsert(userProfile: UserProfile) {
    val userProfileJson = Sdk
        .moshi
        .adapter(UserProfile::class.java)
        .toJson(userProfile)

    storeValue(OWN_USER_PROFILE_KEY, userProfileJson)
}

private const val OWN_USER_PROFILE_KEY = "own_user_profile"
