package com.backbase.golden_sample_app.session

import androidx.navigation.NavController
import org.koin.dsl.module

internal fun sessionModule(navController: NavController) = module {
    single<SessionManager> { SessionManager(get(), navController, get(), get()) }
}
