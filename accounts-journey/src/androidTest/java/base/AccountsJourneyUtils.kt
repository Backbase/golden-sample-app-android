package base

import com.backbase.accounts_journey.AccountsJourney
import com.backbase.accounts_journey.configuration.accountlist.AccountListScreenConfiguration
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.android.retail.journey.test.koin.provideInScope
import com.backbase.fake_accounts_use_case.FakeContactsUseCase
import testdata.TEST_ACCOUNTS

internal fun provideContactsJourneyDependencies(
    configuration: AccountListScreenConfiguration = AccountListScreenConfiguration {},
    useCase: AccountsUseCase = FakeContactsUseCase(TEST_ACCOUNTS)
) = provideInScope<AccountsJourney> {
    scoped { configuration }
    scoped { useCase }
}