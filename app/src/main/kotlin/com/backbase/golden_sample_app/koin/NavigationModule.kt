package com.backbase.golden_sample_app.koin

import androidx.navigation.NavController
import com.backbase.accounts_journey.router.CardNavigationAction
import com.backbase.cards_journey.impl.CardExitNavigationAction
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.router.AppRouting
import org.koin.dsl.module

val navigationModule = module {
    single<CardNavigationAction> {
        object : CardNavigationAction {
            override fun navigateToCards(navController: NavController) {
                navController.navigate(
                    R.id.action_global_cardsJourney,
                )
            }
        }
    }
    factory<CardExitNavigationAction> {
        object : CardExitNavigationAction {
            override fun exit() {
                get<AppRouting>().getNavController()?.popBackStack()
            }
        }
    }
}