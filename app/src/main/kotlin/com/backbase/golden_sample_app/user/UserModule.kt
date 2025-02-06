package com.backbase.golden_sample_app.user

import com.backbase.android.business.journey.common.user.User
import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.app_common.COMMON_MAIN_COROUTINE_SCOPE_QUALIFIER
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
            coroutineScope = get(qualifier = COMMON_MAIN_COROUTINE_SCOPE_QUALIFIER),
        )
    }

    factory {
        ProfileRepository(userProfileManagementApi = get())
    }
}
