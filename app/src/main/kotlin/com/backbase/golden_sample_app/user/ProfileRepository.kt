package com.backbase.golden_sample_app.user

import com.backbase.android.client.usermanagerclient2.api.UserProfileManagementApi
import com.backbase.android.client.usermanagerclient2.model.UserProfile
import com.backbase.android.clients.common.CallResult
import com.backbase.android.clients.common.coroutines.executeAsSuspended
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ProfileRepository(
    private val userProfileManagementApi: UserProfileManagementApi,
    private val inMemoryDataSource: InMemoryProfileDataSource = InMemoryProfileDataSource(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun getOwnUserProfile(forceUpdate: Boolean = false): UserProfile? = withContext(dispatcher) {
        if (forceUpdate) { inMemoryDataSource.upsert(userProfile = refreshFromApi()) }

        inMemoryDataSource.getOwnUserProfileOrNull()
    }

    private suspend fun refreshFromApi(attempts: Int = 0): UserProfile {
        var remoteUserProfile = userProfileManagementApi.getOwnUserProfileOrNull()

        if (remoteUserProfile == null) {
            val attempts = attempts + 1
            delay(timeMillis = 2000L * attempts)

            remoteUserProfile = refreshFromApi(attempts)
        }

        return remoteUserProfile
    }
}

class InMemoryProfileDataSource(private var userProfile: UserProfile? = null) {

    fun upsert(userProfile: UserProfile) {
        this.userProfile = userProfile
    }

    fun getOwnUserProfileOrNull() = userProfile
}

private suspend fun UserProfileManagementApi.getOwnUserProfileOrNull(): UserProfile? = getOwnUserProfile()
    .executeAsSuspended()
    .let { response -> if (response is CallResult.Success) response.data else null }
