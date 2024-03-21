package com.backbase.android.retail.authorization

import android.app.Activity
import com.backbase.android.identity.journey.authentication.AuthenticationRouter
import org.koin.android.ext.android.getKoin
import org.koin.dsl.module

fun Activity.authenticationActions(block: AuthenticationActionsScope.() -> Unit) {
    val scope = AuthenticationActionsScope().apply(block)
    val modules = listOf(
        module {
            factory<AuthenticationRouter> {
                AuthenticationRouter { scope.onSuccess!!.invoke() }
            }
        }
    )
    this.getKoin().loadModules(modules)
}

class AuthenticationActionsScope {
    var onSuccess: (() -> Unit)? = null
}
