package com.backbase.accounts_journey.koin

import com.backbase.accounts_journey.common.DefaultDispatcherProvider
import com.backbase.accounts_journey.common.DispatcherProvider
import org.koin.dsl.module

/**
 * Created by Backbase R&D B.V on 19/09/2023.
 *
 * Dependency setup for generic classes.
 */
val commonModule = module {
    single<DispatcherProvider> { DefaultDispatcherProvider() }
}
