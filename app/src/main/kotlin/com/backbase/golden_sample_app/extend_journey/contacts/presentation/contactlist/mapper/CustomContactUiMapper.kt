package com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.mapper

import com.backbase.android.business.journey.contacts.model.response.Contact
import com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.model.ContactUiModel
import com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.ui.ColorUtils

class CustomContactUiMapper {

    fun mapToUi(domain: Contact): ContactUiModel {
        val contactName = domain.name.orEmpty()
        val account = domain.accounts?.firstOrNull()
        val accountNumber = account?.accountNumber ?: account?.IBAN.orEmpty()

        return ContactUiModel(
            id = domain.id.orEmpty(),
            name = contactName,
            number = accountNumber,
            avatarName = contactName.run { substring(0, if (length >= 2) 2 else 1) },
            color = ColorUtils.generateColor(contactName, 20, 20, 20).toArgb()
        )
    }
}
