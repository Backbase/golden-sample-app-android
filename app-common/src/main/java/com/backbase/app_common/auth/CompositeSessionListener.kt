package com.backbase.app_common.auth

import com.backbase.android.core.utils.BBLogger
import com.backbase.android.listeners.SessionListener
import com.backbase.android.modules.SessionState
import com.backbase.android.retail.journey.SessionEmitter
import com.backbase.android.utils.net.response.Response

/**
 * Session listener and emitter
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
object CompositeSessionListener : SessionListener, SessionEmitter {

    private val listeners: MutableCollection<SessionListener> = mutableSetOf()

    override fun registerSessionListener(listener: SessionListener) {
        val added = listeners.add(listener)
        if (!added) BBLogger.warning("", "$listener was already included in this CompositeSessionListener")
    }

    override fun unregisterSessionListener(listener: SessionListener) {
        val removed = listeners.remove(listener)
        if (!removed) BBLogger.warning("", "$listener was already not included in this CompositeSessionListener")
    }

    override fun onSessionStateChange(state: SessionState, errorResponse: Response?) {
        BBLogger.debug("", "Passing session state <$state> to ${listeners.size} listeners")
        listeners.forEach { it.onSessionStateChange(state, errorResponse) }
    }
}
