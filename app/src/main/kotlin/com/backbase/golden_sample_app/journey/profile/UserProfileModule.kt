package com.backbase.golden_sample_app.journey.profile

import android.app.Application
import com.backbase.android.client.usermanagerclient2.api.UserProfileManagementApi
import com.backbase.app_common.apiRoot
import org.koin.core.module.Module
import org.koin.dsl.module
import java.net.URI

internal fun Module.userProfileModule() {

    factory {
        UserProfileManagementApi(
            context = get<Application>(),
            moshi = get(),
            parser = get(),
            serverUri = URI("${apiRoot()}/user-manager"),
            backbase = get(),
        )
    }
}

internal fun userProfileModule(): Module = module {
    userProfileModule()
}
