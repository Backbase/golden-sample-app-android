package com.backbase.accounts_journey.presentation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.backbase.android.observability.Tracker
import kotlinx.coroutines.CoroutineScope

internal class ObservabilityLifecycleEventTracker(
    private val tracker: Tracker?,
    private val scope: CoroutineScope,
    private val screenName: ScreenName,
    private val addition: String? = null
) : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                tracker?.publish(
                    scope,
                    ScreenViewEvent(
                        screenName = screenName,
                        addition = addition
                    )
                )
            }
            else -> Unit
        }
    }
}
