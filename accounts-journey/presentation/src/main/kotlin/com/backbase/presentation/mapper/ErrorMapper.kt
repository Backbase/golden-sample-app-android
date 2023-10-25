package com.backbase.presentation.mapper

import com.backbase.presentation.R
import com.backbase.common.FailedGetDataException
import com.backbase.common.NoInternetException
import com.backbase.common.NoResponseException

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
