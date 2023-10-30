package app_common

import com.backbase.configuration.AccountsJourneyConfiguration
import com.backbase.domain.data.AccountsUseCase
import com.backbase.fake_accounts_use_case.FakeAccountsUseCase
import com.backbase.koin.presentationModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import resources.TEST_ACCOUNTS

internal fun provideAccountsJourneyDependencies(
    configuration: AccountsJourneyConfiguration = AccountsJourneyConfiguration {},
    useCase: AccountsUseCase = FakeAccountsUseCase(TEST_ACCOUNTS)
) = loadKoinModules(
    listOf(
        module { factory { useCase } },
        presentationModule,
        module { factory { configuration } }
    )
)
