package com.backbase.custom_authentication_flow.otp_verify.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.NavigationEventEmitter
import com.backbase.android.identity.journey.authentication.use_case.impl.NavigationEvent
import com.backbase.custom_authentication_flow.core.util.AuthFlowCompleteRouter
import com.backbase.custom_authentication_flow.otp_verify.use_case.OtpInputRouterUseCase
import com.backbase.custom_authentication_flow.otp_verify.use_case.OtpInputRouterUseCase.OtpInputRouterUseCaseEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class OtpInputViewModel(
    private val useCase: OtpInputRouterUseCase,
    private val navigationEventEmitter: NavigationEventEmitter,
    private val completeRouter: AuthFlowCompleteRouter
) : ViewModel() {

    private val _action = MutableStateFlow<Action>(Action.Idle)
    val action: StateFlow<Action> get() = _action

    init {
        viewModelScope.launch {
            useCase.events.collect {
                when (it) {
                    is OtpInputRouterUseCaseEvent.Success -> Unit
                    is OtpInputRouterUseCaseEvent.Error -> {
                        // Handle error if needed
                    }

                    is OtpInputRouterUseCaseEvent.Prompt -> {
                        // Show data from response if needed
                    }
                }
            }
        }

        viewModelScope.launch {
            navigationEventEmitter.navigationEvents.collect {
                when (it) {
                    is NavigationEvent.ToPasscode, is NavigationEvent.ToBiometric, is NavigationEvent.ToLoginSuccessAfterValidSession -> {
                        completeRouter.sendEvent(it)
                        _action.value = Action.PopBack
                    }

                    else -> Unit
                }
            }
        }
    }

    fun submit(otp: String) {
        viewModelScope.launch { useCase.submit(otp) }
    }

    fun resend() {
        viewModelScope.launch { useCase.resend() }
    }

    /**
     * Actions for the UI to observe
     * If more actions are needed, add them here and handle them in the Fragment
     */
    sealed class Action {
        data object PopBack : Action()
        data object Idle : Action()
    }
}