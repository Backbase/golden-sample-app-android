package com.backbase.golden_sample_app.user

import com.backbase.android.client.usermanagerclient2.api.UserProfileManagementApi
import com.backbase.android.client.usermanagerclient2.model.UserProfile
import com.backbase.android.clients.common.CallResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ProfileRepository(
    private val userProfileManagementApi: UserProfileManagementApi,
    private val inMemoryDataSource: InMemoryProfileDataSource = InMemoryProfileDataSource(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun getOwnUserProfile(forceUpdate: Boolean = false): UserProfile? =
        withContext(dispatcher) {
            if (forceUpdate) {
                inMemoryDataSource.upsert(userProfile = refreshFromApi())
            }
            inMemoryDataSource.getOwnUserProfileOrNull()
        }

    private suspend fun refreshFromApi(attempts: Int = 0): UserProfile {
        var remoteUserProfile = getOwnUserProfileOrNull()

        if (remoteUserProfile == null) {
            val totalAttempts = attempts + 1
            delay(timeMillis = 2000L * totalAttempts)

            remoteUserProfile = refreshFromApi(totalAttempts)
        }

        return remoteUserProfile
    }

    private suspend fun getOwnUserProfileOrNull(): UserProfile? {
        val callResult = withContext(dispatcher) {
            userProfileManagementApi.getOwnUserProfile().parseExecute()
        }
        return when (callResult) {
            is CallResult.Success -> callResult.data
            is CallResult.Error -> null
            is CallResult.None -> null
        }
    }
}

class InMemoryProfileDataSource(private var userProfile: UserProfile? = null) {

    fun upsert(userProfile: UserProfile) {
        this.userProfile = userProfile
    }

    fun getOwnUserProfileOrNull() = userProfile
}
