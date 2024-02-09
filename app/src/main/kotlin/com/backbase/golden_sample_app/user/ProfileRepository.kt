package com.backbase.golden_sample_app.user

import com.backbase.android.client.usermanagerclient2.api.UserProfileManagementApi
import com.backbase.android.client.usermanagerclient2.model.UserProfile
import com.backbase.android.secure.storage.SecureStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ProfileRepository(
    private val remoteSource: UserProfileManagementApi,
    private val localSource: SecureStorage,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun getOwnUserProfile(forceUpdate: Boolean = false): UserProfile? = withContext(dispatcher) {
        if (forceUpdate) { localSource.upsert(userProfile = refreshFromApi()) }

        localSource.getOwnUserProfileOrNull()
    }

    private suspend fun refreshFromApi(attempts: Int = 0): UserProfile {
        var remoteUserProfile = remoteSource.getOwnUserProfileOrNull()

        if (remoteUserProfile == null) {
            val attempts = attempts + 1
            delay(timeMillis = 2000L * attempts)

            remoteUserProfile = refreshFromApi(attempts)
        }

        return remoteUserProfile
    }
}
