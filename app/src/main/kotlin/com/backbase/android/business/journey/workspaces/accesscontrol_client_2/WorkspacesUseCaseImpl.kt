package com.backbase.android.business.journey.workspaces.accesscontrol_client_2

import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.business.journey.workspaces.usecase.CallState
import com.backbase.android.business.journey.workspaces.usecase.Workspace
import com.backbase.android.business.journey.workspaces.usecase.WorkspacesUseCase
import com.backbase.android.business.journey.workspaces.usecase.request.GetWorkspaceListRequest
import com.backbase.android.business.journey.workspaces.usecase.request.PaginationCursor
import com.backbase.android.business.journey.workspaces.usecase.request.PostUserContextRequest
import com.backbase.android.client.accesscontrolclient2.api.UserContextApi
import com.backbase.android.client.accesscontrolclient2.model.Serviceagreementpartialitem
import com.backbase.android.client.accesscontrolclient2.model.UserContextPOST
import com.backbase.android.clients.common.CallResult
import com.backbase.android.clients.common.coroutines.executeAsSuspended
import com.backbase.android.utils.net.response.Response

/**
 * Default [WorkspacesUseCase] implementation
 */
class WorkspacesUseCaseImpl(
    private val userContextApi: UserContextApi,
    private val userRepository: UserRepository
) : WorkspacesUseCase {

    override suspend fun getWorkspaceList(request: GetWorkspaceListRequest): CallState<out List<Workspace>> {
        val page = (request.cursor as? PaginationCursor.Index)?.index
        val call = userContextApi.getUserContextServiceAgreements(
            query = request.query,
            from = page,
            cursor = (request.cursor as? PaginationCursor.Cursor)?.uuid,
            size = request.maxItems
        ).executeAsSuspended()
        return when (call) {
            is CallResult.Success -> {
                userRepository.saveUserInfo(
                    userRepository.getUserInfo().apply {
                        // Reset service agreement size if request is for the first page
                        if (page == 0) serviceAgreementSize = 0

                        serviceAgreementSize += call.data.size
                    }
                )
                val list = call.data.map(Serviceagreementpartialitem::mapToWorkspace)
                if (list.isEmpty()) CallState.Empty else CallState.Success(list)
            }

            is CallResult.Error -> CallState.Error(call.errorResponse)
            is CallResult.None -> CallState.Error(Response(Exception("Unexpected response from UserContextApi")))
            else -> GENERIC_ERROR
        }
    }

    override suspend fun postUserContext(request: PostUserContextRequest): CallState<Nothing> =
        when (
            val call =
                userContextApi.postUserContext(request.mapToUserContext()).executeAsSuspended()
        ) {
            is CallResult.Success -> {
                val updatedUserInfo = userRepository.getUserInfo().apply {
                    serviceAgreementId = request.workspace.id
                    serviceAgreementName = request.workspace.name
                }
                userRepository.saveUserInfo(updatedUserInfo)
                CallState.Empty
            }

            is CallResult.Error -> CallState.Error(call.errorResponse)
            is CallResult.None -> CallState.Error(Response(Exception("Unexpected response from UserContextApi")))
            else -> GENERIC_ERROR
        }

    companion object {
        private val GENERIC_ERROR =
            CallState.Error(Response(Exception("Unexpected response from UserContextApi")))
    }
}

internal fun PostUserContextRequest.mapToUserContext() = let {
    UserContextPOST {
        serviceAgreementId = it.workspace.id
    }
}

internal fun Serviceagreementpartialitem.mapToWorkspace() = let {
    Workspace {
        id = it.id
        name = it.name
        description = it.description
        isMaster = it.isMaster
        additions = it.additions
        externalId = it.externalId
        validFromDate = it.validFromDate
        validFromTime = it.validFromTime
        validUntilDate = it.validUntilDate
        validUntilTime = it.validUntilTime
    }
}
