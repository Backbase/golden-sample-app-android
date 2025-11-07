# 🛡️ Custom Authentication Flow Sample

[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue?style=flat-square)](https://kotlinlang.org/)

This sample application demonstrates the integration of a **custom authentication step** on top of Backbase Identity OOTB (Out-of-the-Box) Authentication Journey.

The key objective is to show how to register custom Identity SDK components (Resolvers, Challenge Handlers, and Routers) to intercept a backend challenge and present a specific UI before continuing or concluding the core authentication flow.

- **Stateless Flow (e.g., OTP Verification)**: The Router Use Case captures user input (like the OTP) and performs a full submission/API call (postStateless) to validate the challenge and determine the next step in the journey. The state is advanced by the successful validation payload.

- **Stateful Flow (e.g., Terms and Conditions)**: The Router Use Case captures user acknowledgment (e.g., "Accept") and performs a submission API call to postStateful. This call typically passes the `actionURI` returned from the previous challenge, and **does not require carrying the full original request body to the next request**, unlike the stateless approach.

## 🚀 Components and Flow

| Component | Responsibility | Layer |
| :--- | :--- | :--- |
| **Resolver & Challenge Handler** | Intercepts the custom BE challenge. | Identity SDK / Data Layer |
| **RouterUseCase** | Manages the authentication flow state and navigation request. | Domain Layer (Use Case) |
| **ViewModel/UI** | Manages UI state and delegates user actions. | Presentation Layer |
| **`initCustomAuthenticator`** | Registration of the custom flow components. | Application/Module Setup |

---

## 🛠️ Implementation Details: Custom OTP Step

This implementation focuses on integrating an **`otp-verify`** challenge.

### 1. Challenge Resolver and Handler

These classes live close to the Identity SDK configuration and are responsible for matching a backend challenge response to the local handling logic.

#### A. The Resolver (`OtpInputResolver`)

The resolver is registered with the Identity SDK's error/challenge handling mechanism. It identifies the challenge type and provides the corresponding handler.

```kotlin
// In the Identity/Data module

internal class OtpInputResolver(...) : CustomErrorResponseResolver() {

    // Matches the expected challenge type from the backend
    override val challenge: String
        get() = OtpInputChallengeHandler.Companion.CHALLENGE_TYPE_OTP_VERIFY 

    override fun challengeHandler(): BBIdentityChallengeHandler =
        OtpInputChallengeHandler(bbIdentityAuthenticatorsProvider, context)
}
```

#### B. The Challenge Handler (OtpInputChallengeHandler)

The handler extracts necessary data from the response and prompts the user for input via the Router.

```kotlin
// com.backbase.custom_authentication_flow.otp_verify.challenge.OtpInputChallengeHandler

internal class OtpInputChallengeHandler(...) : CustomBBIdentityChallengeHandler(...) {

    override fun handleChallenge(request: Request, response: Response) {
        super.handleChallenge(request, response)

        val responseJson = response.stringResponse?.toJsonObject() ?: JsonObject()
        val referenceNumber = responseJson.getChallengeValueString(REF_NUMBER)

        // 1. Extract necessary data (e.g., referenceNumber)
        // 2. Delegate to the RouterUseCase to show the UI
        requireRouter.promptForOtp(
            referenceNumber,
            originalRequest,
            request.flowId().toString(),
            contract,
            identityRouterContext
        )
    }

    override fun getChallenge(): String = CHALLENGE_TYPE_OTP_VERIFY

    companion object Companion {
        const val CHALLENGE_TYPE_OTP_VERIFY = "otp-verify"
        private const val REF_NUMBER = "referenceNumber"
    }
}
```

### 2. Create the Router Use Case

The RouterUseCase manages the state, navigation, and API submission for the custom step. It receives the prompt from the ChallengeHandler and reports success/error back to the Identity SDK flow.

```kotlin
// com.backbase.custom_authentication_flow.otp_verify.router.OtpInputRouterUseCase

internal class OtpInputRouterUseCase(...) : RouterUseCase<...>, BBIdentityRouter {

    fun promptForOtp( /* ... */ ) {
        // ... store context, referenceNumber, originalRequest ...
        coroutineScope.launch {
            customNavigationEmitter.sendEvent(CustomNavigationEvent.ToOtpInput) // Navigate to UI
            _events.emit(OtpInputRouterUseCaseEvent.Prompt)
        }
    }

    suspend fun submit(otp: String) {
        val additionalRequestBody =
            mapOf(KEY_OTP_VALUE to otp, KEY_REFERENCE_NUMBER to referenceNumber)
        // Send OTP/ReferenceNumber back to the backend
        execute(additionalRequestBody)
    }

    override fun onSuccess(...) {
        // When successful, inform the Identity flow to continue
        routerContract.finish(routerContext)
    }

    // ... onError, cancel implementations ...
}
```

### 3. Create the UI and ViewModel

The ViewModel connects the UI input (OTP value) to the RouterUseCase and handles navigation events.

```kotlin
// com.backbase.custom_authentication_flow.otp_verify.viewmodel.OtpInputViewModel

internal class OtpInputViewModel(private val useCase: OtpInputRouterUseCase, ...) : ViewModel() {

    init {
        // Collect events from the useCase (Prompt, Error, Success) to update UI state
        viewModelScope.launch { useCase.events.collect { /* ... handle events ... */ } }
    }

    fun submit(otp: String) {
        // Delegate user action to the RouterUseCase
        viewModelScope.launch { useCase.submit(otp) }
    }

    fun resend() {
        viewModelScope.launch { useCase.resend() }
    }
}
```

### 4. Initialize Custom Authenticators

Finally, the custom components must be registered with the Identity SDK during the application startup, ensuring the custom flow is recognized and ready to handle the challenge.

```kotlin
// Initializing logic in your Application

fun initCustomAuthenticator() {
    if (!isCustomRouterInitialized) {
        isCustomRouterInitialized = true

        val authUseCase = GlobalContext.get().get<AuthenticationFlowsUseCase>().authenticationUseCase
        val allRoutersUseCase = GlobalContext.get().get<AuthFlowUseCaseManager>()

        runCatching {
            with(authUseCase) {
                // Ensure the custom router is registered
                removeRouter(allRoutersUseCase.otpInputRouterUseCase.javaClass)
                addRouter(allRoutersUseCase.otpInputRouterUseCase)
            }
        }
        
        // This function must ensure the OtpInputResolver is registered
        initErrorResolvers()
    }
}
```