package com.backbase.golden_sample_app.presentation.header

import com.backbase.android.business.journey.common.user.UserRepository
import com.backbase.android.design.header.AvatarConfiguration
import com.backbase.android.design.header.TopBarConfiguration
import com.backbase.android.plugins.storage.StorageComponent
import com.backbase.golden_sample_app.router.getUsernameOrBlank

class TopBarConfigurationProvider(
    private val storageComponent: StorageComponent,
    private val userRepository: UserRepository,
) {

    fun config() = TopBarConfiguration {
        val userFullName = storageComponent.getUsernameOrBlank()

        avatar = AvatarConfiguration { initials = userFullName.toNameInitials() }
        title = userFullName
        subtitle = userRepository.getUserInfo().serviceAgreementName ?: ""
    }

    private fun String.toNameInitials(): String {
        if (isEmpty()) return this

        val subNames = split(" ")
        return if (subNames.size == 1) subNames.first().first().toString()
        else subNames.first().first().toString() + subNames.last().first().toString()
    }
}
