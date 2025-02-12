package com.backbase.app_common.accounts

import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.configuration.accountlist.AccountListScreenConfiguration
import com.backbase.accounts_journey.configuration.icon.IconsConfiguration
import com.backbase.accounts_journey.data.usecase.AccountDetailUseCase
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.routing.AccountsRouting
import org.koin.core.definition.Definition
import org.koin.core.module.Module

fun Module.accountsModule(block: AccountsJourneyDependenciesScope.() -> Unit) {
    val dependencies = AccountsJourneyDependenciesScope().apply(block)

    factory(definition = dependencies.configuration)
    factory(definition = dependencies.accountsRouting)
    factory(definition = dependencies.accountSummaryUseCase)
    factory(definition = dependencies.accountDetailUseCase)
}

class AccountsJourneyDependenciesScope internal constructor() {
    var configuration: Definition<AccountsJourneyConfiguration> = {
        DefaultAccountsJourneyConfiguration()
    }

    lateinit var accountsRouting: Definition<AccountsRouting>

    lateinit var accountSummaryUseCase: Definition<AccountsUseCase>

    lateinit var accountDetailUseCase: Definition<AccountDetailUseCase>
}

@Suppress("FunctionName")
@JvmSynthetic
fun DefaultAccountsJourneyConfiguration(
    customizations: AccountsJourneyConfiguration.Builder.() -> Unit = {
    }
): AccountsJourneyConfiguration =
    AccountsJourneyConfiguration.Builder().apply {
        accountListScreenConfiguration = AccountListScreenConfiguration { }
        iconsConfiguration = IconsConfiguration { }
    }.apply(customizations).build()
