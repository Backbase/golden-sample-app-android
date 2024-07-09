package com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.ui

import androidx.annotation.StringRes
import com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.model.ContactUiModel

/**
 * The UI state for the custom contacts screen
 *
 * Created by Backbase R&D B.V. on 05/06/2024.
 */
data class CustomContactsScreenState(
    val isLoading: Boolean = false,
    val contacts: List<ContactUiModel> = emptyList(),
    @StringRes val error: Int? = null
)
