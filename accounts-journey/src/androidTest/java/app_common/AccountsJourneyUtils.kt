package app_common

import com.backbase.accounts_journey.AccountsJourney
import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.fake_accounts_use_case.FakeContactsUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import resources.TEST_ACCOUNTS

internal fun provideAccountsJourneyDependencies(
    configuration: AccountsJourneyConfiguration = AccountsJourneyConfiguration {},
    useCase: AccountsUseCase = FakeContactsUseCase(TEST_ACCOUNTS)
) = loadKoinModules(
    listOf(
        module { factory { useCase } },
        AccountsJourney.create(configuration = configuration)
    )
)
