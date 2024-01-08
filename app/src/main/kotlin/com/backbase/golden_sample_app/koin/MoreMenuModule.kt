package com.backbase.golden_sample_app.koin

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.backbase.android.retail.journey.more.MenuItem
import com.backbase.android.retail.journey.more.MenuSection
import com.backbase.android.retail.journey.more.MenuSections
import com.backbase.android.retail.journey.more.MoreConfiguration
import com.backbase.android.retail.journey.more.MoreJourneyScope
import com.backbase.android.retail.journey.more.MoreRouter
import com.backbase.android.retail.journey.more.OnActionComplete.NavigateTo
import com.backbase.deferredresources.DeferredColor
import com.backbase.deferredresources.DeferredDrawable
import com.backbase.deferredresources.DeferredText
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.router.MoreMenuRouterImpl
import org.koin.dsl.module

internal fun moreMenuModule(navController: NavController) = module {
    scope<MoreJourneyScope> {
        factory<MoreRouter> {
            MoreMenuRouterImpl(get(), navController)
        }

        scoped { demoMoreConfig }
    }
}

/**
 * Created by Backbase R&D B.V. on 23/07/2020.
 */

val demoMoreConfig = MoreConfiguration {
    showIcons = true
    contentDescription = DeferredText.Constant("More Menu")
    sections = MenuSections {
        +logOutSection()
    }
}

/**
 * A menu section configuration can also be written like this using builders.
 */
private fun logOutSection() = MenuSection {
    val logoutColor = DeferredColor.Resource(com.backbase.android.design.R.color.bds_danger)
    val iconColor = DeferredColor.Resource(com.backbase.android.design.R.color.bds_onDanger)
    +MenuItem(
        DeferredText.Constant("Log out"),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_logout) {
            setTint(iconColor.resolve(it))
        },
        iconBackgroundColor = logoutColor
    ) {
        EndSession
    }
    title = DeferredText.Constant("Security")
}

internal object EndSession : NavigateTo(R.id.authenticationJourney)
