package com.backbase.golden_sample_app.koin

import android.os.Bundle
import com.backbase.android.retail.journey.more.MoreConfiguration
import com.backbase.android.retail.journey.more.MoreJourneyScope
import com.backbase.android.retail.journey.more.MoreRouter
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import com.backbase.android.retail.journey.more.MenuItem
import com.backbase.android.retail.journey.more.MenuSection
import com.backbase.android.retail.journey.more.MenuSections
import com.backbase.android.retail.journey.more.MoreToolbarNavigationItem
import com.backbase.android.retail.journey.more.OnActionComplete.NavigateTo
import com.backbase.deferredresources.DeferredColor
import com.backbase.deferredresources.DeferredDrawable
import com.backbase.deferredresources.DeferredText
import com.backbase.golden_sample_app.R
import org.koin.dsl.module

internal fun moreMenuModule(
) = module {
    scope<MoreJourneyScope> {
        factory {
            object : MoreRouter {
                override fun onExit(navigationActionId: Int, args: Bundle?) {
                    TODO("Not yet implemented")
                }
            }
        }

        scoped { MoreConfiguration { } }
    }
}

/**
 * Created by Backbase R&D B.V. on 23/07/2020.
 */

val demoMoreConfig = MoreConfiguration {
    showIcons = true
    contentDescription = DeferredText.Constant("More Menu")
    sections = MenuSections {1
        +logOutSection()
    }
}

private fun makeMoreToolbarNavigationItem() = MoreToolbarNavigationItem {
    contentDescription = DeferredText.Constant("Press Back")
    icon = DeferredDrawable.Resource(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
    onMoreToolbarNavigationItemSelected = {
        it.findFragment<Fragment>().requireActivity().onBackPressed()
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
