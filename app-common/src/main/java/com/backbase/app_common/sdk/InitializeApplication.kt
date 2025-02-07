package com.backbase.app_common.sdk

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.listeners.SessionListener
import com.backbase.android.retail.journey.SessionEmitter
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

fun Application.startKoinIfNotStarted() {
    if (GlobalContext.getOrNull() == null) {
        startKoin { androidContext(applicationContext) }
    }
}

fun Application.initializeAuthClient(sessionEmitter: SessionEmitter) {
    val backbase = checkNotNull(Backbase.getInstance()) {
        "Backbase must be initialized before initializeAuthClient is called"
    }

    val authClient = BBIdentityAuthClient(get<Application>())
    backbase.registerAuthClient(authClient)

    check(sessionEmitter is SessionListener) {
        "Provided SessionEmitter should also implement SessionListener."
    }
    authClient.startSessionObserver(sessionEmitter)
}
