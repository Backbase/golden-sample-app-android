package com.bartburg.contacts_journey.domain.model

data class PaginatedResult<T>(
    val items: List<T>,
    val page: Int,
    val pageSize: Int,
    val hasMore: Boolean
) 