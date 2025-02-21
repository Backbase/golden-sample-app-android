package com.backbase.android.journey.contacts.domain.model

data class PaginatedResult<T>(
    val items: List<T>,
    val page: Int,
    val pageSize: Int,
    val hasMore: Boolean
) 