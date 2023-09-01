package com.backbase.accounts_journey.domain.model.account.savings

import dev.drewhamilton.poko.Poko

@Poko
class SavingsAccount internal constructor(
    val id: String?,
    val name: String?,
    val additions: Map<String, String>?,
)