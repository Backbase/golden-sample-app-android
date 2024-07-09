package com.backbase.golden_sample_app.menu

import androidx.navigation.NavController
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
import com.backbase.golden_sample_app.configuration.ApplicationConfiguration
import com.backbase.golden_sample_app.configuration.ApplicationFeatureFlag
import com.backbase.golden_sample_app.configuration.hasFeatureFlag
import com.backbase.golden_sample_app.router.MoreMenuRouterImpl
import com.backbase.golden_sample_app.session.SessionManager
import com.backbase.golden_sample_app.user.UserEntitlements
import com.backbase.golden_sample_app.user.UserEntitlementsRepository
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

        scoped { demoMoreConfig(get(), get(), navController) }
    }
}

fun demoMoreConfig(
    sessionManager: SessionManager,
    userEntitlementsRepository: UserEntitlementsRepository,
    navController: NavController
) = MoreConfiguration {
    showIcons = true
    contentDescription = DeferredText.Resource(R.string.more_menu_title)
    sections = MenuSections {
        +demoSection()
        +contactsSection(userEntitlementsRepository)
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
        ) {
            NavigateTo(R.id.upcoming_fragment)
        }
    }
}

private fun contactsSection(
    userEntitlementsRepository: UserEntitlementsRepository,
): MenuSection {
    return MenuSection {
        if (ApplicationConfiguration.applicationFeatureFlags.hasFeatureFlag<ApplicationFeatureFlag.ContactsJourneyFeatureFlag>()) {
            if (userEntitlementsRepository.entitlements.contains(UserEntitlements.Contact.view)) {
                val switchIconColor =
                    DeferredColor.Resource(com.backbase.android.design.R.color.bds_onDanger)
                +MenuItem(
                    title = DeferredText.Resource(R.string.more_menu_contacts),
                    icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_contacts) {
                        setTint(switchIconColor.resolve(it))
                    },
                ) {
                    NavigateTo(R.id.customContactsFragment)
                }
            }
        }
    }
}

private fun logOutSection(
    sessionManager: SessionManager,
    navController: NavController
) = MenuSection {
    val switchBackgroundColor =
        DeferredColor.Resource(com.backbase.android.design.R.color.bds_danger)
    val switchIconColor =
        DeferredColor.Resource(com.backbase.android.design.R.color.bds_onDanger)
    +MenuItem(
        title = DeferredText.Resource(R.string.more_menu_log_out),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_logout) {
            setTint(switchIconColor.resolve(it))
        }
    ) {
        sessionManager.logOut()
        navController.popBackStack(R.id.workspaces_selector, true)
        BackToAuth
    }
    +MenuItem(
        title = DeferredText.Resource(R.string.more_menu_switch_user),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_person) {
            setTint(switchIconColor.resolve(it))
        },
        iconBackgroundColor = switchBackgroundColor
    ) {
        sessionManager.switchUser()
        navController.popBackStack(R.id.workspaces_selector, true)
        BackToAuth
    }
    title = DeferredText.Resource(R.string.more_menu_security_section_title)
}

internal object BackToAuth : NavigateTo(R.id.authenticationJourney)
