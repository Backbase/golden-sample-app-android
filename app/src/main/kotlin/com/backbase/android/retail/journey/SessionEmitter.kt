package com.backbase.android.retail.journey

import com.backbase.android.listeners.SessionListener

// TODO: This is a workaround to get the identity journey started. NavigationEventEmitter can be found in the Retail Journey Common.
/**
 * Emits [com.backbase.android.modules.SessionState] updates to registered [SessionListener]s.
 */
interface SessionEmitter {
    /**
     * Register [listener] to receive [com.backbase.android.modules.SessionState] updates.
     */
    fun registerSessionListener(listener: SessionListener)

    /**
     * Unregister [listener] to stop receiving [com.backbase.android.modules.SessionState] updates.
     */
    fun unregisterSessionListener(listener: SessionListener)
}