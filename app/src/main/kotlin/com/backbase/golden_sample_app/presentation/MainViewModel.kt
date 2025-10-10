package com.backbase.golden_sample_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.sdk.security.SecurityVerification
import com.backbase.android.sdk.security.violation.ViolationResult
import com.backbase.golden_sample_app.user.ProfileRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository,
    private val security: SecurityVerification,
) : ViewModel() {

    private val userInfo = flow {
        emit(value = userRepository.getUserInfo())
    }

    private val userProfile = flow {
        emit(value = profileRepository.getOwnUserProfile(forceUpdate = true))
    }

    private val _securityState = MutableStateFlow(SecurityState(ViolationState.Nothing))

    private var securityCheckJob: Job? = null

    val uiState = userInfo.combine(userProfile) { info, profile ->
        UiState(
            fullName = profile?.fullName.toString(),
            serviceAgreementName = info.serviceAgreementName ?: "",
            userInitials = profile?.fullName.toNameInitials()
        )
    }

    val securityState = _securityState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SecurityState(ViolationState.Nothing)
    )

    private fun String?.toNameInitials(): String {
        if (isNullOrBlank()) return ""

        return this
            .split(" ")
            .mapNotNull { it.firstOrNull()?.toString() }
            .reduce { acc, initial -> acc + initial }
    }

    fun runDebugCheck() {
        viewModelScope.launch {
            when (val result = security.isDebugged()) {
                is ViolationResult.Error -> _securityState.update {
                    SecurityState(
                        ViolationState.Error(
                            result.exception
                        )
                    )
                }

                ViolationResult.Genuine -> _securityState.update { SecurityState(ViolationState.Good) }
                is ViolationResult.Violated -> _securityState.update { SecurityState(ViolationState.Debugged) }
            }
        }
    }

    fun runEmulatorCheck() {
        viewModelScope.launch {
            when (val result = security.isEmulator()) {
                is ViolationResult.Error -> _securityState.update {
                    SecurityState(
                        ViolationState.Error(
                            result.exception
                        )
                    )
                }

                ViolationResult.Genuine -> _securityState.update { SecurityState(ViolationState.Good) }
                is ViolationResult.Violated -> _securityState.update { SecurityState(ViolationState.Emulator) }
            }
        }
    }

    fun runAllChecks() {
        viewModelScope.launch {
            when (val result = security.isViolated()) {
                is ViolationResult.Error -> _securityState.update {
                    SecurityState(
                        ViolationState.Error(
                            result.exception
                        )
                    )
                }

                ViolationResult.Genuine -> _securityState.update { SecurityState(ViolationState.Good) }
                is ViolationResult.Violated -> _securityState.update { SecurityState(ViolationState.Compromised) }
            }
        }
    }

    /**
     * Starts periodic security checks (debug and emulator detection)
     * @param intervalMs The interval between checks in milliseconds (default: 30 seconds)
     */
    fun startPeriodicSecurityChecks(intervalMs: Long = 5_000L) {
        stopPeriodicSecurityChecks() // Stop any existing job

        securityCheckJob = viewModelScope.launch {
            while (true) {
                runAllChecks()
//                runDebugCheck()
//                runEmulatorCheck()
                delay(intervalMs)
            }
        }
    }

    /**
     * Stops the periodic security checks
     */
    fun stopPeriodicSecurityChecks() {
        securityCheckJob?.cancel()
        securityCheckJob = null
    }

    class UiState(
        val fullName: String,
        val serviceAgreementName: String,
        val userInitials: String,
    )

    class SecurityState(val state: ViolationState)

    sealed interface ViolationState {
        data object Good : ViolationState
        data object Rooted : ViolationState
        data object Nothing : ViolationState
        data object Tampered : ViolationState
        data object Debugged : ViolationState
        data object Emulator : ViolationState
        data object Compromised : ViolationState
        data object AdditionalCheck : ViolationState
        class Error(val ex: Exception) : ViolationState
    }
}
