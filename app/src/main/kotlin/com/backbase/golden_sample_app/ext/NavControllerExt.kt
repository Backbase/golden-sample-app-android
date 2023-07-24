package com.backbase.golden_sample_app.ext

import androidx.navigation.NavController

/**
 * Created by Backbase R&D B.V on 29/03/2022.
 * Extension for Navcontroller
 */
internal fun NavController.isFragmentInBackStack(destinationId: Int) =
    try {
        getBackStackEntry(destinationId)
        true
    } catch (e: Exception) {
        false
    }
