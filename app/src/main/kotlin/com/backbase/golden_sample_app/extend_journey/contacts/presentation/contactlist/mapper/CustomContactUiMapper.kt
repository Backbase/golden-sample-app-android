package com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.mapper

import com.backbase.android.retail.journey.contacts.Contact
import com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.model.ContactUiModel
import com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.ui.ColorUtils

class CustomContactUiMapper {

    fun mapToUi(domain: Contact): ContactUiModel {
        return ContactUiModel(
            id = domain.id,
            name = domain.name,
            number = domain.account.identifiers.firstOrNull()?.value ?: "",
            avatarName = domain.name.run { substring(0, if (length >= 2) 2 else 1) },
            color = ColorUtils.generateColor(domain.name, 20, 20, 20).toArgb()
        )
    }
}
