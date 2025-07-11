package app_common

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.koin.viewModelModule
import com.backbase.accounts_journey.presentation.accountlist.mapper.AccountUiMapper
import com.backbase.accounts_journey.routing.AccountsRouting
import com.backbase.accounts_journey.routing.AccountsRoutingImpl
import com.backbase.accounts_use_case.SuccessAccountsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import testCases.AccountsListTests.Companion.TEST_ACCOUNTS

@ExperimentalCoroutinesApi fun prepare(
    func: ModuleLoader.() -> Unit = { loadModule() }
) = ModuleLoader().apply { func() }

class ModuleLoader {

    @ExperimentalCoroutinesApi
    fun loadModule(
        accountsUseCase: SuccessAccountsUseCase = SuccessAccountsUseCase(TEST_ACCOUNTS),
        journeyConfiguration: AccountsJourneyConfiguration = AccountsJourneyConfiguration {},
    ) {
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
            loadKoinModules(
                listOf(
                    module {
                        single { androidContext().applicationContext as Application }
                        single<AccountsJourneyConfiguration> { journeyConfiguration }
                        single<AccountsUseCase> { accountsUseCase }
                        single<AccountUiMapper> { AccountUiMapper(get()) }
                        single<AccountsRouting> { AccountsRoutingImpl() }
                    },
                    viewModelModule
                )
            )
        }
    }
}
