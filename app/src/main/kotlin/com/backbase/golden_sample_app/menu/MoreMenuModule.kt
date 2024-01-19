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
import com.backbase.golden_sample_app.router.MoreMenuRouterImpl
import com.backbase.golden_sample_app.session.SessionManager
import com.backbase.golden_sample_app.user.UserEntitlementsRepository
import com.backbase.golden_sample_app.user.UserEntitlements
import org.koin.dsl.module

internal fun moreMenuModule(
    navController: NavController
) = module {

    scope<MoreJourneyScope> {
        factory<MoreRouter> {
            MoreMenuRouterImpl(navController)
        }

        scoped { demoMoreConfig(get(), get()) }
    }
}

/**
 * Created by Backbase R&D B.V. on 23/07/2020.
 */

fun demoMoreConfig(
    sessionManager: SessionManager,
    userEntitlementsRepository: UserEntitlementsRepository
) = MoreConfiguration {
    showIcons = true
    contentDescription = DeferredText.Constant("More Menu")
    sections = MenuSections {
        +moreSection(userEntitlementsRepository)
        +logOutSection(sessionManager)
    }
}

private fun moreSection(userEntitlementsRepository: UserEntitlementsRepository): MenuSection {
    return MenuSection {
        if (userEntitlementsRepository.entitlements.contains(UserEntitlements.Contact.view)) {
            +MenuItem(
                DeferredText.Constant("Contacts"),
                icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_contacts)
            ) {
                NavigateTo(R.id.action_more_to_contactsJourney)
            }
        }
    }
}


/**
 * A menu section configuration can also be written like this using builders.
 */
private fun logOutSection(
    sessionManager: SessionManager
) = MenuSection {
    val switchUserBackgroundColor =
        DeferredColor.Resource(com.backbase.android.design.R.color.bds_danger)
    val switchUserIconColor =
        DeferredColor.Resource(com.backbase.android.design.R.color.bds_onDanger)
    +MenuItem(
        DeferredText.Constant("Log out"),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_logout)
    ) {
        sessionManager.logOut()
        BackToAuth
    }
    +MenuItem(
        DeferredText.Constant("Switch user"),
        icon = DeferredDrawable.Resource(com.backbase.android.design.R.drawable.backbase_ic_person) {
            setTint(switchUserIconColor.resolve(it))
        },
        iconBackgroundColor = switchUserBackgroundColor
    ) {
        sessionManager.switchUser()
        BackToAuth
    }
    title = DeferredText.Constant("Security")
}

internal object BackToAuth : NavigateTo(R.id.authenticationJourney)
