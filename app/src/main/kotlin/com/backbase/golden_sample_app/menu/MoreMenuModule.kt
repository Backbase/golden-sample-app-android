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
import com.backbase.app_common.auth.session.SessionManager
import com.backbase.deferredresources.DeferredColor
import com.backbase.deferredresources.DeferredDrawable
import com.backbase.deferredresources.DeferredText
import com.backbase.golden_sample_app.R
import org.koin.core.definition.Definition
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by Backbase R&D B.V. on 23/07/2020.
 */
internal fun Module.moreMenuModule(block: MoreMenuDependenciesScope.() -> Unit) {
    val dependencies = MoreMenuDependenciesScope().apply(block)

    scope<MoreJourneyScope> {
        scoped(definition = dependencies.moreMenuJourneyConfiguration)
        factory<MoreRouter> {
            MoreMenuRouterImpl(get<NavController>())
        }

        scoped {
            demoMoreConfig(
                sessionManager = get(),
                navController = get<NavController>()
            )
        }
    }
}

internal fun moreMenuModule() = module {
    moreMenuModule {
        moreMenuJourneyConfiguration = {
            demoMoreConfig(sessionManager = get(), navController = get())
        }
    }
}

internal class MoreMenuDependenciesScope {
    /**
     * The definition for the [MoreConfiguration] to be used within the scope.
     */
    lateinit var moreMenuJourneyConfiguration: Definition<MoreConfiguration>
}

fun demoMoreConfig(
    sessionManager: SessionManager,
    navController: NavController
) = MoreConfiguration {
    showIcons = true
    contentDescription = DeferredText.Resource(R.string.more_menu_title)
    sections = MenuSections {
        +demoSection()
        +logOutSection(sessionManager, navController)
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
    sessionManager: SessionManager,
    navController: NavController
) = MenuSection {
    val switchIconColor =
        DeferredColor.Resource(com.backbase.android.design.R.color.bds_onDanger)
    +MenuItem(
        title = DeferredText.Resource(R.string.more_menu_log_out),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_logout) {
            setTint(switchIconColor.resolve(it))
        },
        iconBackgroundColor = DeferredColor.Resource(com.backbase.android.design.R.color.bds_primary),
        actionBlock = {
            sessionManager.logOut()
            navController.popBackStack(R.id.workspaces_selector, true)
            BackToAuth(AuthenticationJourney.LAUNCH_ACTION_END_SESSION)
        }
    )
    +MenuItem(
        title = DeferredText.Resource(R.string.more_menu_switch_user),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_person) {
            setTint(switchIconColor.resolve(it))
        },
        iconBackgroundColor = DeferredColor.Resource(com.backbase.android.design.R.color.bds_danger),
        actionBlock = {
            sessionManager.switchUser()
            navController.popBackStack(R.id.workspaces_selector, true)
            BackToAuth(AuthenticationJourney.LAUNCH_ACTION_LOG_OUT)
        }
    )
    title = DeferredText.Resource(R.string.more_menu_security_section_title)
}

internal class BackToAuth(arg: String) :
    NavigateTo(R.id.authenticationJourney, bundleOf(arg to true))
