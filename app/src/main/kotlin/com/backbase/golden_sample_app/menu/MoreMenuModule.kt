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
import com.backbase.golden_sample_app.configuration.getFeatureFlagOrNull
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
    applicationConfiguration: ApplicationConfiguration,
) = module {
    scope<MoreJourneyScope> {
        factory<MoreRouter> {
            MoreMenuRouterImpl(navController)
        }

        scoped { demoMoreConfig(get(), get(), applicationConfiguration) }
    }
}

fun demoMoreConfig(
    sessionManager: SessionManager,
    userEntitlementsRepository: UserEntitlementsRepository,
    applicationConfiguration: ApplicationConfiguration,
) = MoreConfiguration {
    showIcons = true
    contentDescription = DeferredText.Resource(R.string.more_menu_title)
    sections = MenuSections {
        +moreSection(userEntitlementsRepository, applicationConfiguration)
        +logOutSection(sessionManager)
    }
}

private fun moreSection(
    userEntitlementsRepository: UserEntitlementsRepository,
    applicationConfiguration: ApplicationConfiguration
): MenuSection {
    return MenuSection {
        if (applicationConfiguration.applicationFeatureFlags.hasFeatureFlag<ApplicationFeatureFlag.ContactsJourneyFeatureFlag>()) {
            if (userEntitlementsRepository.entitlements.contains(UserEntitlements.Contact.view)) {
                +MenuItem(
                    title = DeferredText.Resource(R.string.more_menu_contacts),
                    icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_contacts)
                ) {
                    NavigateTo(R.id.action_more_to_contactsJourney)
                }
            }
        }
    }
}

private fun logOutSection(
    sessionManager: SessionManager
) = MenuSection {
    val switchUserBackgroundColor =
        DeferredColor.Resource(com.backbase.android.design.R.color.bds_danger)
    val switchUserIconColor =
        DeferredColor.Resource(com.backbase.android.design.R.color.bds_onDanger)
    +MenuItem(
        title = DeferredText.Resource(R.string.more_menu_log_out),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_logout)
    ) {
        sessionManager.logOut()
        BackToAuth
    }
    +MenuItem(
        title = DeferredText.Resource(R.string.more_menu_switch_user),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_person) {
            setTint(switchUserIconColor.resolve(it))
        },
        iconBackgroundColor = switchUserBackgroundColor
    ) {
        sessionManager.switchUser()
        BackToAuth
    }
    title = DeferredText.Resource(R.string.more_menu_security_section_title)
}

internal object BackToAuth : NavigateTo(R.id.authenticationJourney)
