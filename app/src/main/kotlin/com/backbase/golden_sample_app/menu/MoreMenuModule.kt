package com.backbase.golden_sample_app.menu

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.backbase.android.identity.journey.authentication.AuthenticationJourney
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
import com.backbase.golden_sample_app.session.SessionManager
import org.koin.dsl.module

/**
 * Created by Backbase R&D B.V. on 23/07/2020.
 */
internal fun moreMenuModule(
    navController: NavController,
) = module {
    scope<MoreJourneyScope> {
        factory<MoreRouter> {
            MoreMenuRouterImpl(navController)
        }

        scoped { demoMoreConfig(get()) }
    }
}

fun demoMoreConfig(
    sessionManager: SessionManager
) = MoreConfiguration {
    showIcons = true
    contentDescription = DeferredText.Resource(R.string.more_menu_title)
    sections = MenuSections {
        +demoSection()
        +logOutSection(sessionManager)
    }
}

private fun demoSection(): MenuSection {
    return MenuSection {
        val switchIconColor =
            DeferredColor.Resource(com.backbase.android.design.R.color.bds_onDanger)
        +MenuItem(
            title = DeferredText.Resource(R.string.more_menu_demo_title),
            icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_curtains_closed) {
                setTint(switchIconColor.resolve(it))
            },
            iconBackgroundColor = DeferredColor.Resource(com.backbase.android.design.R.color.bds_primary)
        ) {
            NavigateTo(R.id.upcoming_fragment)
        }
    }
}

private fun logOutSection(
    sessionManager: SessionManager
) = MenuSection {
    val switchIconColor =
        DeferredColor.Resource(com.backbase.android.design.R.color.bds_onDanger)
    +MenuItem(
        title = DeferredText.Resource(R.string.more_menu_log_out),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_logout) {
            setTint(switchIconColor.resolve(it))
        },
        iconBackgroundColor = DeferredColor.Resource(com.backbase.android.design.R.color.bds_primary)
    ) {
        sessionManager.logOut()
        BackToAuth(AuthenticationJourney.LAUNCH_ACTION_END_SESSION)
    }
    +MenuItem(
        title = DeferredText.Resource(R.string.more_menu_switch_user),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_person) {
            setTint(switchIconColor.resolve(it))
        },
        iconBackgroundColor = DeferredColor.Resource(com.backbase.android.design.R.color.bds_danger)
    ) {
        sessionManager.switchUser()
        BackToAuth(AuthenticationJourney.LAUNCH_ACTION_LOG_OUT)
    }
    title = DeferredText.Resource(R.string.more_menu_security_section_title)
}

internal class BackToAuth(arg: String) : NavigateTo(R.id.authenticationJourney, bundleOf(arg to true))
