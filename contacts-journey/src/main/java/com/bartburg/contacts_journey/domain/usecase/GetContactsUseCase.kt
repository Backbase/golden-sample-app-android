package com.bartburg.contacts_journey.domain.usecase

import com.bartburg.contacts_journey.domain.model.ContactModel
import com.bartburg.contacts_journey.domain.model.PaginatedResult
import kotlinx.coroutines.flow.Flow

interface GetContactsUseCase<T : ContactModel> {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int = DEFAULT_PAGE_SIZE
    ): Flow<PaginatedResult<T>>?

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }
}


sealed class ResourceState<out T>(){
    data class Idle<T>(val data: T?) : ResourceState<T>()
    data class Loading<T>(val data: T?) : ResourceState<T>()
    data class Success<T>(val data: T) : ResourceState<T>()
    data class Error<T>(val data: T?, val message: String) : ResourceState<T>()
}

sealed class ResourceListState<out T>(){
    data class Idle<T>(val data: List<T>?) : ResourceState<T>()
    data class Loading<T>(val data: List<T>?) : ResourceState<T>()
    data class Success<T>(val data: List<T>) : ResourceState<T>()
    data class Error<T>(val data: List<T>?, val message: String) : ResourceState<Nothing>()
}

fun test(){
    val idleState = ResourceState.Idle<ContactModel>(data = null)
    idleState.data?.country
}