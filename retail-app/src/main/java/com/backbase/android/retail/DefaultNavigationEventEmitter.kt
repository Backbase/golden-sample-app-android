package com.backbase.android.retail

import com.backbase.android.Backbase
import com.backbase.android.listeners.NavigationEventListener
import com.backbase.android.retail.journey.NavigationEventEmitter

class DefaultNavigationEventEmitter(
    private val backbase: Backbase
) : NavigationEventEmitter {
    override fun registerNavigationEventListener(listener: NavigationEventListener) =
        backbase.registerNavigationEventListener(listener)

    override fun unregisterNavigationEventListener(listener: NavigationEventListener) =
        backbase.unregisterNavigationEventListener(listener)
}
