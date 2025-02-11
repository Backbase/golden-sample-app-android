package com.bartburg.contacts_journey.domain.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal


// Payment Model with extension
data class PaymentModel<PaymentModelExtension>(
    val accountNumber: String,
    val name: String,
    val amount: BigDecimal,
    val extension: PaymentModelExtension?
)

// Example base state
data class PaymentState<PaymentModelExtension, StateExtension>(
    val isProcessing: Boolean = false,
    val payment: PaymentModel<PaymentModelExtension>? = null,
    val extension: StateExtension?
)

// Contract for Reducer
interface PaymentReducer<PaymentModelExtension, StateExtension> {
    suspend operator fun invoke(state: MutableStateFlow<PaymentState<PaymentModelExtension, StateExtension>>, event: PaymentIntent)
}

class DefaultPaymentReducer: PaymentReducer<CustomPaymentExtension, CustomStateExtension> {
    override suspend operator fun invoke(
        stateFlow: MutableStateFlow<PaymentState<CustomPaymentExtension, CustomStateExtension>>,
        event: PaymentIntent
    ) {
        val state = stateFlow.value
        when (event) {
            is PaymentIntent.SubmitPayment -> {
                stateFlow.update { state.copy(isProcessing = true) }
                delay(1000L)
                stateFlow.update { state.copy(isProcessing = false) }
            }
            is PaymentIntent.UpdateAmount -> {
                //
            }
            else -> state //Else block potentially needed due to customisation on project.
        }
    }
}

// Payment intent is extendable due to sealed class.
sealed class PaymentIntent {
    object SubmitPayment : PaymentIntent() //Real select account
    data class UpdateAmount(val amount: BigDecimal) : PaymentIntent()
}

// Example ViewModel (not extendable)
class PaymentViewModel<PaymentModelExtension, StateExtension>(
    private val reducer: PaymentReducer<PaymentModelExtension, StateExtension>
) : ViewModel() {
    private val _state = MutableStateFlow(PaymentState<PaymentModelExtension, StateExtension>(extension = null))
    val state: StateFlow<PaymentState<PaymentModelExtension, StateExtension>> get() = _state

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

//Newly added on project
data class UpdateBankCode(val bankCode: String) : PaymentIntent()


val customViewModel = PaymentViewModel<CustomPaymentExtension, CustomStateExtension>(customReducer)