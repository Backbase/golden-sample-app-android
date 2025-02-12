package app_common

import com.backbase.accounts_journey.AccountsJourney
import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.routing.AccountsRouting
import com.backbase.accounts_journey.routing.AccountsRoutingImpl
import com.backbase.android.observability.Tracker
import com.backbase.fake_accounts_use_case.FakeAccountsUseCase
import observability.observabilityModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import resources.TEST_ACCOUNTS

internal fun provideAccountsJourneyDependencies(
    configuration: AccountsJourneyConfiguration = AccountsJourneyConfiguration {},
    useCase: AccountsUseCase = FakeAccountsUseCase(TEST_ACCOUNTS),
    tracker: Tracker
) {
    startKoin {
        loadKoinModules(
            listOf(
                module {
                    factory<AccountsRouting> { AccountsRoutingImpl() }
                    factory { useCase }
                },
                AccountsJourney.create(configuration = configuration),
				observabilityModule(tracker),
            )
        )
    }
}
