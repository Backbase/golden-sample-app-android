package com.backbase.accounts_journey.koin

import com.backbase.accounts_journey.presentation.accountdetail.mapper.AccountDetailUiMapper
import com.backbase.accounts_journey.presentation.accountlist.mapper.AccountUiMapper
import org.koin.dsl.module

/**
 * Dependency setup for mappers.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
val mapperModule = module {
    factory { AccountUiMapper(accountsJourneyConfiguration = get()) }
    factory { AccountDetailUiMapper(accountsJourneyConfiguration = get()) }
}
