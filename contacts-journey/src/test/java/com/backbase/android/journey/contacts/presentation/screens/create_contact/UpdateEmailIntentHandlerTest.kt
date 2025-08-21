package com.backbase.android.journey.contacts.presentation.screens.create_contact

import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateEmail
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Invalid
import com.backbase.android.journey.contacts.presentation.util.FieldValue
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.fail

class UpdateEmailIntentHandlerTest {

    @Test
    fun `when email is invalid should return invalid validation`() = runTest {
        val expectedStates = listOf<CreateContactState<Unit>>(
            CreateContactState(
                email = FieldValue(value = "matiasb@")
            ),
            CreateContactState(
                email = FieldValue(
                    value = "matiasb@",
                    fieldStatus = Invalid(R.string.contacts_create_field_email_empty_error)
                )
            )
        )
        val actualStates = mutableListOf<CreateContactState<Unit>>()
        val updateEmailIntentHandler = updateEmailIntentHandler<Unit>()

        updateEmailIntentHandler.runIn(
            IntentScope<UpdateEmail, CreateContactState<Unit>, CreateContactSideEffect>(
                coroutineContext = coroutineContext,
                currentUiState = { actualStates.lastOrNull() ?: CreateContactState() },
                intent = UpdateEmail(email = "matiasb@"),
                intentContext = IntentContext(
                    emitState = { actualStates.add(it) },
                    emitEffect = { fail("Should not emit effects") }
                )
            )
        )

        assertEquals(expectedStates, actualStates)
    }
}