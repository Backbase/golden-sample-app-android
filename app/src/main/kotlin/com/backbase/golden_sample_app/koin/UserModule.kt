package com.backbase.golden_sample_app.koin

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.business.journey.common.user.User
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.client.gen2.accesscontrolclient3.api.UserContextApi
import com.backbase.android.client.gen2.accesscontrolclient3.api.UsersApi
import com.backbase.golden_sample_app.Sdk
import com.backbase.golden_sample_app.user.ProfileRepository
import com.backbase.golden_sample_app.user.UserEntitlementsRepository
import com.backbase.golden_sample_app.user.UserEntitlementsRepositoryImpl
import com.backbase.golden_sample_app.user.UserRepositoryImpl
import org.koin.dsl.module

/**
 * Dependency setup for user information.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
internal fun userModule(context: Context) = module {
    single { Backbase.requireInstance().getClient(UserContextApi::class.java) }
    single { Backbase.requireInstance().getClient(UsersApi::class.java) }

    single { User() }
    single<UserEntitlementsRepository> { UserEntitlementsRepositoryImpl() }

    factory<UserRepository> {
        UserRepositoryImpl(
            secureStorage = get(),
            coroutineScope = get(),
        )
    }

    factory {
        ProfileRepository(userProfileManagementApi = Sdk.getUsersProfileApi(context))
    }
}
