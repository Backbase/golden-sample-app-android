package com.backbase.golden_sample_app.presentation

import android.content.Context
import com.backbase.golden_sample_app.presentation.header.TabListConfigurationProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Dependency setup on app level for the presentation layer.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
internal fun presentationModule(context: Context) = module {
    factory { TabListConfigurationProvider(context = context) }
    viewModel { MainViewModel(profileRepository = get(), userRepository = get()) }
}
