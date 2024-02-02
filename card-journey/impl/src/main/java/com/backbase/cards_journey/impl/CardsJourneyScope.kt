package com.backbase.cards_journey.impl

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.backbase.android.retail.journey.koin.KoinScopeViewModel
import com.backbase.android.retail.journey.koin.scoped
import com.backbase.android.retail.journey.koin.scopedViewModel
import com.backbase.cards_journey.impl.ui.details.CardDetailsViewModel
import com.backbase.cards_journey.impl.ui.list.CardListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module

interface CardsJourneyScope

@MainThread
internal inline fun <reified T : Any> Fragment.journeyScoped(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): Lazy<T> =
    scoped<CardsJourney, CardsJourneyScopeImpl, T>(qualifier, parameters)

@MainThread
internal inline fun <reified VM : ViewModel> Fragment.journeyScopedViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): Lazy<VM> =
    scopedViewModel<CardsJourney, CardsJourneyScopeImpl, VM>(
        qualifier,
        parameters
    )

class CardsJourneyScopeImpl :
    KoinScopeViewModel(CardsJourneyScope::class, module { }),
    CardsJourneyScope {

    private var scopeModule: Module? = null

    fun launchWithArguments() {
        if (scopeModule == null) {
            scopeModule = ScopedModule<CardsJourneyScope> {
                viewModel {
                    CardListViewModel(get())
                }
                viewModel { (id: String) ->
                    CardDetailsViewModel(cardId = id, get())
                }
            }.apply {
                loadKoinModules(this)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scopeModule?.let { unloadKoinModules(it) }
    }
}

