package com.backbase.golden_sample_app.koin

import com.backbase.android.business.journey.common.user.User
import com.backbase.android.business.journey.common.user.UserRepository
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
internal fun userModule() = module {
    single { User() }
    single<UserEntitlementsRepository> { UserEntitlementsRepositoryImpl() }

    factory<UserRepository> {
        UserRepositoryImpl(
            secureStorage = get(),
            coroutineScope = get(),
        )
    }

    factory {
        ProfileRepository(userProfileManagementApi = get())
    }
}
