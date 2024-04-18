package com.backbase.golden_sample_app.presentation

import androidx.lifecycle.ViewModel
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.golden_sample_app.user.ProfileRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class MainViewModel(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val userInfo = flow {
        emit(value = userRepository.getUserInfo())
    }

    private val userProfile = flow {
        emit(value = profileRepository.getOwnUserProfile(forceUpdate = true))
    }

    val uiState = userInfo.combine(userProfile) { info, profile ->
        UiState(
            fullName = profile?.fullName.toString(),
            serviceAgreementName = info.serviceAgreementName ?: "",
            userInitials = profile?.fullName.toNameInitials()
        )
    }

    private fun String?.toNameInitials(): String {
        if (isNullOrBlank()) return ""

        return this
            .split(" ")
            .mapNotNull { it.firstOrNull()?.toString() }
            .reduce { acc, initial -> acc + initial }
    }

    class UiState(
        val fullName: String,
        val serviceAgreementName: String,
        val userInitials: String,
    )
}
