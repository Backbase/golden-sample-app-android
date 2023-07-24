package com.backbase.golden_sample_app.authentication

import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.journey.oob_transaction.OutOfBandTransactionSigningRouter
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.ext.isFragmentInBackStack
import com.backbase.golden_sample_app.router.AppRouting

/**
 * Created by Backbase R&D B.V on 29/03/2022
 * Router for OOB-TS
 */
class OutOfBandTransactionSigningRouterImpl(
    private val appRouter: AppRouting
) : OutOfBandTransactionSigningRouter {

    override fun onTransactionSigningFinished() {
        BBLogger.debug("UniversalOutOfBandTransactionSigningRouter", "Out of band Transaction Signing Finished")
        if (appRouter.getNavController()?.isFragmentInBackStack(R.id.nav_host_container) == false)
            appRouter.getNavController()?.navigate(R.id.nav_host_container)
    }

    override fun onDeviceBlocked() {
        BBLogger.debug(
            "UniversalOutOfBandTransactionSigningRouter",
            "Device is blocked during Out of band transaction signing. Restarting the Auth Journey ..."
        )
    }
}
