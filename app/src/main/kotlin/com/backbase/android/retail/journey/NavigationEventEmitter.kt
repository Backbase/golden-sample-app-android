package com.backbase.android.retail.journey

import com.backbase.android.listeners.NavigationEventListener

// TODO: This is a workaround to get the identity journey started. NavigationEventEmitter can be found in the Retail Journey Common.
/**
 * Emits [com.backbase.android.navigation.NavigationEvent]s to registered [NavigationEventListener]s.
 */
interface NavigationEventEmitter {

    fun registerNavigationEventListener(listener: NavigationEventListener)

    fun unregisterNavigationEventListener(listener: NavigationEventListener)
}
