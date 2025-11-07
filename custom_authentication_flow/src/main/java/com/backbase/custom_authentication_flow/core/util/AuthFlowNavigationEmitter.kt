package com.backbase.custom_authentication_flow.core.util

import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.journey.authentication.utils.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

public class CustomNavigationEmitter {
    public val navigationEvents: Flow<CustomNavigationEvent>
        get() = _navigationEventEmitter.asSharedFlow()

    private val _navigationEventEmitter: MutableSharedFlow<CustomNavigationEvent> =
        MutableSharedFlow()

    public suspend fun sendEvent(event: CustomNavigationEvent) {
        BBLogger.debug(TAG, "--> Sending navigation event $event to the Authentication Journey")
        _navigationEventEmitter.emit(event)
    }
}

public sealed class CustomNavigationEvent {
    public object ToTermsAndConditions : CustomNavigationEvent()
    public object ToOtpInput : CustomNavigationEvent()
}
