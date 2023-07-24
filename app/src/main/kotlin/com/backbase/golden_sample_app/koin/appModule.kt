package com.backbase.golden_sample_app.koin

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.clients.auth.AuthClientListener
import com.backbase.android.identity.journey.authentication.AuthenticationDeregistrationListener
import com.backbase.android.identity.journey.authentication.AuthenticationRouter
import com.backbase.android.identity.journey.authentication.FidoRegistrationRouter
import com.backbase.android.identity.journey.authentication.passcode.change_passcode.ChangePasscodeRouter
import com.backbase.android.modules.SessionState
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.android.plugins.storage.persistent.EncryptedStorage
import com.backbase.android.utils.net.response.Response
import com.backbase.golden_sample_app.common.user.User
import com.backbase.golden_sample_app.common.user.UserRepository
import com.backbase.golden_sample_app.common.user.UserRepositoryImpl
import com.backbase.golden_sample_app.router.AppRouter
import com.backbase.golden_sample_app.router.AppRouting
import com.backbase.golden_sample_app.router.AuthenticationDeregistrationListenerImpl
import com.backbase.golden_sample_app.router.AuthenticationRouterImpl
import com.backbase.golden_sample_app.router.ChangePasscodeRouterImpl
import com.backbase.golden_sample_app.router.FidoRegistrationRouterImpl
import com.backbase.mobilenotifications.core.component.AppStateProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

internal fun appModule(context: Context) = module {
    factory { CoroutineScope(Dispatchers.IO + SupervisorJob()) }

    single { User {} }

    // Single instance AppNavigator
    single<AppRouting> { AppRouter() }

    factory<StorageComponent> { get<Backbase>().getRegisteredPlugin(EncryptedStorage::class.java)!!.storageComponent }

    factory<UserRepository> { UserRepositoryImpl(get()) }

    factory<AuthenticationRouter> {
        AuthenticationRouterImpl(get(), get(), get())
    }

    factory<FidoRegistrationRouter> { FidoRegistrationRouterImpl(get()) }

    factory<ChangePasscodeRouter> { ChangePasscodeRouterImpl(get()) }

    factory<AuthenticationDeregistrationListener> {
        AuthenticationDeregistrationListenerImpl(get(), get())
    }
}

internal class NotificationsAppStateProvider : AppStateProvider, AuthClientListener {
    var loggedIn: Boolean = false

    override fun canDisplayInApp(): Boolean {
        Backbase.requireInstance().authClient.checkSessionValidity(this)
        return loggedIn
    }

    override fun checkSessionState(state: SessionState, errorResponse: Response?) {
        loggedIn = state == SessionState.VALID
    }

    @Deprecated("Function cannot be removed because it was declared abstract")
    override fun checkSessionState(p0: SessionState) = Unit
}
