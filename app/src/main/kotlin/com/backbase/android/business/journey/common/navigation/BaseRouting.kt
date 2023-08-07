package com.backbase.android.business.journey.common.navigation

import androidx.navigation.NavController

interface BaseRouting {

    /***
     * Get the exit point nav controller
     */
    fun exitNavController(): NavController? = null

    /**
     * Name of router for destination
     */
    fun destinationRouterIdentifier(): String? = null

    /**
     * Name of destination router for configuration
     */
    fun destinationRouterConfig(): String? = null
}
