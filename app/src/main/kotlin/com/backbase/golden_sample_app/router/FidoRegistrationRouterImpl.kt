package com.backbase.golden_sample_app.router

import com.backbase.android.identity.journey.authentication.FidoRegistrationRouter
import com.backbase.golden_sample_app.R

internal class FidoRegistrationRouterImpl(
    private val appRouting: AppRouting
) : FidoRegistrationRouter {

    override fun onFidoRegistrationCompleted() {
        appRouting.getNavController()?.navigate(
            R.id.action_global_appNavHostFragment
        )
    }
}
