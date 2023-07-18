package com.backbase.golden_sample_app.authentication

import com.backbase.android.core.utils.BBLogger
import com.backbase.android.listeners.SessionListener
import com.backbase.android.modules.SessionState
import com.backbase.android.utils.net.response.Response

/**
 * Created by Backbase R&D B.V on 19/08/2021.
 *
 * Session listener and emitter
 */
internal class CompositeSessionListener : SessionListener {

    private val listeners: MutableCollection<SessionListener> = mutableSetOf()

    fun registerSessionListener(listener: SessionListener) {
        val added = listeners.add(listener)
        if (!added) BBLogger.warning("", "$listener was already included in this CompositeSessionListener")
    }

    fun unregisterSessionListener(listener: SessionListener) {
        val removed = listeners.remove(listener)
        if (!removed) BBLogger.warning("", "$listener was already not included in this CompositeSessionListener")
    }

    override fun onSessionStateChange(state: SessionState, errorResponse: Response?) {
        BBLogger.debug("", "Passing session state <$state> to ${listeners.size} listeners")
        listeners.forEach { it.onSessionStateChange(state, errorResponse) }
    }

    @Deprecated("Function cannot be removed because it was declared abstract")
    override fun onSessionStateChange(p0: SessionState) = Unit
}