package com.backbase.golden_sample_app.router

import androidx.core.os.bundleOf
import com.backbase.android.identity.journey.authentication.AuthenticationJourney
import com.backbase.android.identity.journey.authentication.passcode.change_passcode.ChangePasscodeRouter
import com.backbase.golden_sample_app.R

class ChangePasscodeRouterImpl(private val appRouting: AppRouting) : ChangePasscodeRouter {

    override fun onCompleted() {
        appRouting.getNavController()?.navigate(
            R.id.action_global_appNavHostFragment,
            args = bundleOf(AuthenticationJourney.LAUNCH_ACTION_CHANGE_PASSCODE to true)
        )
    }
}
