package com.backbase.golden_sample_app.koin

import com.backbase.android.Backbase
import com.backbase.android.business.journey.common.user.User
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.business.journey.common.user.UserRepositoryImpl
import com.backbase.android.client.accesscontrolclient2.api.UserContextApi
import com.backbase.android.client.accesscontrolclient2.api.UsersApi
import org.koin.dsl.module

internal val userModule = module {
    single { Backbase.requireInstance().getClient(UserContextApi::class.java) }
    single { Backbase.requireInstance().getClient(UsersApi::class.java) }

    single { User {} }

    factory<UserRepository> { UserRepositoryImpl(get()) }
}
