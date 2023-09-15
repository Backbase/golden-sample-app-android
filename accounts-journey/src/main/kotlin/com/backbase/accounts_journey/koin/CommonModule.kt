package com.backbase.accounts_journey.koin

import com.backbase.accounts_journey.common.DefaultDispatcherProvider
import com.backbase.accounts_journey.common.DispatcherProvider
import org.koin.dsl.module

val commonModule = module {
    single<DispatcherProvider> { DefaultDispatcherProvider() }
}