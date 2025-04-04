package com.backbase.android.journey.contacts.domain.model

/**
 * A container for paginated results, holding a list of items along with pagination metadata.
 *
 * @param <T> the type of items in the result set
 * @property items the list of items for the current page
 * @property page the current page number (starting from 1)
 * @property pageSize the number of items per page
 * @property hasMore indicates whether more pages are available
 */
data class PaginatedResult<T>(
    val items: List<T>,
    val page: Int,
    val pageSize: Int,
    val hasMore: Boolean
) 