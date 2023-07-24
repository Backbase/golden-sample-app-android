package com.backbase.golden_sample_app.authentication

/**
 * Created by Backbase R&D B.V on 19/08/2021.
 * End session router, triggering session end when refresh auth token fails
 */
interface SessionEndedRouter {

    /**
     * Invoked when refresh token fails
     */
    fun onSessionEnded()
}
