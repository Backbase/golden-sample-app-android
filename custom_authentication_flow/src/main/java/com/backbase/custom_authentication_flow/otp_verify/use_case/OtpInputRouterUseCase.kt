package com.backbase.custom_authentication_flow.otp_verify.use_case

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
import com.backbase.android.utils.net.request.Request
import com.backbase.custom_authentication_flow.core.challenge.CustomChallengeContract
import com.backbase.custom_authentication_flow.core.util.CustomNavigationEmitter
import com.backbase.custom_authentication_flow.core.util.CustomNavigationEvent
import com.backbase.custom_authentication_flow.core.util.executeAsSuspendedRaw
import com.backbase.custom_authentication_flow.data.CustomIdentityApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.UUID

internal class OtpInputRouterUseCase(
    private val customIdentityApi: CustomIdentityApi,
    private val customNavigationEmitter: CustomNavigationEmitter,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) : RouterUseCase<OtpInputRouterUseCase.OtpInputRouterUseCaseEvent>, BBIdentityRouter {

    private lateinit var routerContract: CustomChallengeContract
    private lateinit var routerContext: BBIdentityRouterContext
    private lateinit var flowId: String
    private lateinit var referenceNumber: String
    private lateinit var originalRequest: Request

    private val _events: MutableSharedFlow<OtpInputRouterUseCaseEvent> = MutableSharedFlow()
    override val events: Flow<OtpInputRouterUseCaseEvent>
        get() = _events

    fun promptForOtp(
        referenceNumber: String,
        originalRequest: Request,
        flowId: String,
        contract: CustomChallengeContract,
        context: BBIdentityRouterContext
    ) {
        this.routerContract = contract
        this.routerContext = context
        this.referenceNumber = referenceNumber
        this.originalRequest = originalRequest
        this.flowId = flowId

        coroutineScope.launch {
            customNavigationEmitter.sendEvent(CustomNavigationEvent.ToOtpInput)
            _events.emit(OtpInputRouterUseCaseEvent.Prompt)
        }
    }

    suspend fun submit(otp: String) {
        val additionalRequestBody =
            mapOf(
                KEY_OTP_VALUE to otp,
                KEY_REFERENCE_NUMBER to referenceNumber,
                KEY_DATE_TIME to ""
            )
        execute(additionalRequestBody)
    }

    suspend fun resend() {
        val additionalRequestBody =
            mapOf(KEY_REFERENCE_NUMBER to referenceNumber)
        execute(additionalRequestBody)
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
                    OtpInputRouterUseCaseEvent.Error(
                        responseData = error.response.toResponseData()
                    )
                )
            }
        }
    }

    override fun onSuccess(
        routerContext: BBIdentityRouterContext,
        contract: BBIdentityRouterContract
    ) {
        coroutineScope.launch {
            _events.emit(OtpInputRouterUseCaseEvent.Success)
            routerContract.finish(routerContext)
        }
    }

    override fun cancel(instanceId: UUID, response: ResponseData?) {
        routerContract.cancel(routerContext)
    }

    override fun ongoingFlowType(instanceId: UUID): OngoingFlowType = OngoingFlowType.REGISTRATION

    private suspend fun execute(additionalRequestBody: Map<String, String>) {
        when (
            val result = customIdentityApi.postStateless(
                originalRequest,
                additionalRequestBody,
                flowId
            ).executeAsSuspendedRaw()
        ) {
            is CallResult.Success -> {
                BBLogger.debug(TAG, "OtpInputRouterUseCase success")
                routerContract.setChallengeResponse(result.data)
                onSuccess(routerContext, routerContract)
            }

            is CallResult.Error -> {
                BBLogger.debug(TAG, "OtpInputRouterUseCase error: ${result.errorResponse}")
                routerContract.setChallengeResponse(result.errorResponse)
                val routerError = BBIdentityRouterError(result.errorResponse)
                onError(routerError, routerContext, routerContract)
            }

            else -> Unit
        }
    }

    sealed class OtpInputRouterUseCaseEvent {

        public data class Error(val responseData: ResponseData) :
            OtpInputRouterUseCaseEvent()

        public data object Success : OtpInputRouterUseCaseEvent()

        public data object Prompt : OtpInputRouterUseCaseEvent()
    }

    companion object {
        private const val KEY_REFERENCE_NUMBER = "referenceNumber"
        private const val KEY_OTP_VALUE = "otp"
        private const val KEY_DATE_TIME = "dateTime"
    }
}