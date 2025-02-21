package com.backbase.android.journey.contacts.domain.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal


// Payment Model is a Domain model that only contains data the journey needs to work. If a customer needs additional data, they can extend it with PaymentModelExtension.
data class PaymentModel<PaymentModelExtension>(
    val accountNumber: String,
    val name: String,
    val amount: BigDecimal,
    val paymentType: String, //Would be enum
    val extension: PaymentModelExtension?
)

//region Intent handling extension
// If the domain model does not have all the field the Back-end has by default, it needs to be able to be extended. That also means that the state and the intent handling needs the ability to be extended. If we separate the intent handling part of the ViewModel to a separate instance, we can make it Kotlin Multiplatform compatible.
interface PaymentListIntentHandler<PaymentModelExtension> {
    suspend operator fun invoke(state: MutableStateFlow<PaymentState<PaymentModelExtension>>, event: PaymentIntent)
}

sealed class PaymentIntent2<Extension> {
    object SubmitPayment : PaymentIntent2<Unit>() //Real select account
    data class UpdateAmount(val amount: BigDecimal) : PaymentIntent2<Unit>()
    class Extension(val extension: Extension): PaymentIntent2<Extension>()
}
//endregion

//region Intent handling interface
interface PaymentIntent {
    object SubmitPayment : PaymentIntent //Real select account
    data class UpdateAmount(val amount: BigDecimal) : PaymentIntent
}

sealed class PaymentIntentExtension: PaymentIntent {
    data class UpdateBankCode(val bankCode: String)  //Example adding custom intent
}
//endregion

//region intent handling ViewModel
class PaymentViewModel2<PaymentModelExtension, StateExtension>(
    private val reducer: PaymentListIntentHandler<PaymentModelExtension,>
) : ViewModel() {
    private val _state = MutableStateFlow(PaymentState<PaymentModelExtension>())
    val state: StateFlow<PaymentState<PaymentModelExtension,>> get() = _state

    fun dispatch(event: PaymentIntent) {
        //get viewmodel scope
        viewModelScope.launch{
            reducer.invoke(_state, event)
        }
    }
}
//endregion

// Example state
data class PaymentState<PaymentModelExtension>(
    val isProcessing: Boolean = false,
    val payment: PaymentModel<PaymentModelExtension>? = null
)

// A default typealias can be created in case a customer does not need an extension.
typealias  DefaultPaymentState = PaymentState<Any>

//An example of the domain model with child models and extensions. The domain models should be as flat as possible. However if there is a one to many relation, th child models must have extension fields.
data class BaseModel<Extension, ChildExtension>(
    val id: String,
    val name: String,
    val children: List<ChildModel<ChildExtension>>,
    val extension: Extension?
)

data class ChildModel<Extension>(
    val id: String,
    val name: String,
    val extension: Extension?
)

class DefaultPaymentListIntentHandler: PaymentListIntentHandler<CustomPaymentExtension> {
    override suspend operator fun invoke(
        stateFlow: MutableStateFlow<PaymentState<CustomPaymentExtension>>,
        event: PaymentIntent
    ) {
        when (event) {
            is PaymentIntent.SubmitPayment -> {
                stateFlow.value = stateFlow.value.copy(isProcessing = true)
                delay(1000L)
                stateFlow.value = stateFlow.value.copy(isProcessing = false)
            }
            is PaymentIntent.UpdateAmount -> {
                //
            }
        }
    }
}


sealed class DefaultPaymentIntent: PaymentIntent

// Example ViewModel (not extendable)
class PaymentViewModel<PaymentModelExtension, StateExtension>(
    private val reducer: PaymentListIntentHandler<PaymentModelExtension,>
) : ViewModel() {
    private val _state = MutableStateFlow(PaymentState<PaymentModelExtension>())
    val state: StateFlow<PaymentState<PaymentModelExtension,>> get() = _state

    fun dispatch(event: PaymentIntent) {
        //get viewmodel scope
        viewModelScope.launch{
            reducer.invoke(_state, event)
        }
    }
}

data class CustomPaymentExtension(
    val bankCode: String
)

data class CustomStateExtension(
    val bankCode: String
)