package com.backbase.accounts_journey.presentation.mapper

import com.backbase.accounts_journey.R
import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.common.NoInternetException
import com.backbase.accounts_journey.common.NoResponseException

/**
 * A error mapper from Throwable to String Resource.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
fun Throwable.mapErrorToMessage(): Int {
    return when (this) {
        is FailedGetDataException -> R.string.failed_get_data
        is NoResponseException -> R.string.no_response
        is NoInternetException -> R.string.no_internet_connection
        else -> R.string.failed_unknown
    }
}
