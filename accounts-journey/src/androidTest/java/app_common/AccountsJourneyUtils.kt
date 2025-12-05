package app_common

import com.backbase.accounts_journey.AccountsJourney
import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.routing.AccountsRouting
import com.backbase.accounts_journey.routing.AccountsRoutingImpl
import com.backbase.accounts_use_case.SuccessAccountsUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import testCases.AccountsListTests.Companion.TEST_ACCOUNTS

internal fun provideAccountsJourneyDependencies(
    configuration: AccountsJourneyConfiguration = AccountsJourneyConfiguration {},
    useCase: AccountsUseCase = SuccessAccountsUseCase(TEST_ACCOUNTS),
) {
    startKoin {
        loadKoinModules(
            listOf(
                module {
                    factory<AccountsRouting> { AccountsRoutingImpl() }
                    factory { useCase }
                },
                AccountsJourney.create(configuration = configuration),
            )
        )
    }
}
