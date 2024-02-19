package com.backbase.golden_sample_app.koin

import androidx.navigation.NavController
import com.backbase.accounts_journey.router.AccountsRouter
import com.backbase.cards_journey.impl.CardsRouter
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.router.AppRouting
import org.koin.dsl.module

val navigationModule = module {
    single<AccountsRouter> {
        object : AccountsRouter {
            override fun exit(navController: NavController) {
                navController.navigate(
                    R.id.action_global_cardsJourney,
                )
            }
        }
    }
    factory<CardsRouter> {
        object : CardsRouter {
            override fun exit() {
                get<AppRouting>().getNavController()?.popBackStack()
            }
        }
    }
}