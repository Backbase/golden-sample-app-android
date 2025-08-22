package com.backbase.custom_authentication_flow.terms_and_conditions.use_case

import com.backbase.android.clients.common.CallResult
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.common.BBIdentityErrorCodes
import com.backbase.android.identity.common.routers.BBIdentityRouter
import com.backbase.android.identity.common.routers.BBIdentityRouterContext
import com.backbase.android.identity.common.routers.BBIdentityRouterContract
import com.backbase.android.identity.common.routers.BBIdentityRouterError
import com.backbase.android.identity.common.routers.isUserCancellation
import com.backbase.android.identity.journey.authentication.use_case.api.OngoingFlowType
import com.backbase.android.identity.journey.authentication.use_case.api.routers.RouterUseCase
import com.backbase.android.identity.journey.authentication.use_case.common.ResponseData
import com.backbase.android.identity.journey.authentication.use_case.common.ResponseData.Companion.toResponseData
import com.backbase.android.identity.journey.authentication.utils.TAG
import com.backbase.custom_authentication_flow.core.util.CustomNavigationEmitter
import com.backbase.custom_authentication_flow.core.util.CustomNavigationEvent
import com.backbase.custom_authentication_flow.core.challenge.CustomChallengeContract
import com.backbase.custom_authentication_flow.data.CustomIdentityApi
import com.backbase.custom_authentication_flow.data.request.TermsAndConditionsRequest
import com.backbase.custom_authentication_flow.core.util.executeAsSuspendedRaw
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.UUID

internal class TermsAndConditionUseCase(
    private val customIdentityApi: CustomIdentityApi,
    private val customNavigationEmitter: CustomNavigationEmitter,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) : RouterUseCase<TermsAndConditionUseCase.TermsAndConditionsEvent>, BBIdentityRouter {

    private lateinit var routerContract: CustomChallengeContract
    private lateinit var routerContext: BBIdentityRouterContext
    private lateinit var flowId: String
    private lateinit var actionUrl: String

    private val _events: MutableSharedFlow<TermsAndConditionsEvent> = MutableSharedFlow()
    override val events: Flow<TermsAndConditionsEvent>
        get() = _events

    fun promptScreen(
        actionUrl: String,
        flowId: String,
        contract: CustomChallengeContract,
        context: BBIdentityRouterContext
    ) {
        this.routerContract = contract
        this.routerContext = context
        this.flowId = flowId
        this.actionUrl = actionUrl

        coroutineScope.launch {
            customNavigationEmitter.sendEvent(CustomNavigationEvent.ToTermsAndConditions)
            _events.emit(TermsAndConditionsEvent.Prompt)
        }
    }

    suspend fun accept() {
        when (
            val result = customIdentityApi.postStateful<TermsAndConditionsRequest>(
                actionUrl,
                flowId,
                TermsAndConditionsRequest(true),
            ).executeAsSuspendedRaw()) {
            is CallResult.Success -> {
                BBLogger.debug(TAG, "TermsAndConditionUseCase success")
                routerContract.setChallengeResponse(result.data)
                onSuccess(routerContext, routerContract)
            }

            is CallResult.Error -> {
                BBLogger.debug(TAG, "TermsAndConditionUseCase error: ${result.errorResponse}")
                routerContract.setChallengeResponse(result.errorResponse)
                val routerError = BBIdentityRouterError(result.errorResponse)
                onError(routerError, routerContext, routerContract)
            }

            else -> Unit
        }
    }

    override fun onSuccess(
        routerContext: BBIdentityRouterContext,
        contract: BBIdentityRouterContract
    ) {
        coroutineScope.launch {
            _events.emit(TermsAndConditionsEvent.Success)
            routerContract.finish(routerContext)
        }
    }

    override fun onError(
        error: BBIdentityRouterError,
        routerContext: BBIdentityRouterContext,
        contract: BBIdentityRouterContract
    ) {
        if (error.response.isUserCancellation() || error.response.rootCause?.responseCode == BBIdentityErrorCodes.FATAL_ERROR) {
            routerContract.finish(routerContext)
        } else {
            coroutineScope.launch {
                _events.emit(
                    TermsAndConditionsEvent.Error(
                        responseData = error.response.toResponseData()
                    )
                )
            }
        }
    }

    override fun cancel(instanceId: UUID, response: ResponseData?) {
        routerContract.cancel(routerContext)
    }

    override fun ongoingFlowType(instanceId: UUID): OngoingFlowType = OngoingFlowType.REGISTRATION

    public sealed class TermsAndConditionsEvent {
        public object Success : TermsAndConditionsEvent()
        public data class Error(val responseData: ResponseData?) : TermsAndConditionsEvent()
        public object Prompt : TermsAndConditionsEvent()
    }
}