package com.backbase.app_common.workspaces

import com.backbase.android.business.journey.workspaces.config.WorkspaceSelectorScreenConfiguration
import com.backbase.android.business.journey.workspaces.config.WorkspaceSwitcherScreenConfiguration
import com.backbase.android.business.journey.workspaces.config.WorkspacesJourneyConfiguration
import com.backbase.android.business.journey.workspaces.navigation.WorkspaceSelectorRouting
import com.backbase.android.business.journey.workspaces.navigation.WorkspaceSwitcherRouting
import com.backbase.android.business.journey.workspaces.usecase.WorkspacesUseCase
import org.koin.core.definition.Definition
import org.koin.core.module.Module

fun Module.workspacesModule(block: WorkspacesJourneyDependenciesScope.() -> Unit) {
    val dependencies = WorkspacesJourneyDependenciesScope().apply(block)

    factory(definition = dependencies.configuration)
    factory(definition = dependencies.workspaceSelectorRouting)
//    factory(definition = dependencies.workspaceSwitcherRouting)
    single(definition = dependencies.workspacesUseCase)
    single { WorkspaceUpdater }
}

/**
 * A scope class used to define dependencies for the Workspaces Journey module.
 *
 * This class allows you to specify the providers for configuration, routing,
 * and the use case required by the Workspaces Journey feature.
 */
class WorkspacesJourneyDependenciesScope internal constructor() {
    /**
     * The definition for the [WorkspacesJourneyConfiguration] to be used within the scope.
     */
    var configuration: Definition<WorkspacesJourneyConfiguration> = {
        DefaultWorkspacesJourneyConfiguration()
    }

    /**
     * The definition for the [WorkspaceSelectorRouting].
     */
    lateinit var workspaceSelectorRouting: Definition<WorkspaceSelectorRouting>

    /**
     * The definition for the [WorkspaceSwitcherRouting].
     */
//    lateinit var workspaceSwitcherRouting: Definition<WorkspaceSwitcherRouting>

    /**
     * The definition for the [WorkspacesUseCase].
     */
    lateinit var workspacesUseCase: Definition<WorkspacesUseCase>
}

/**
 * Default implementation of WorkspacesJourneyConfiguration, supports customization for providing
 * updated values.
 *
 * @param customizations Extra function to set over-riding or new values, than default ones.
 */
@Suppress("FunctionName")
@JvmSynthetic
fun DefaultWorkspacesJourneyConfiguration(
    customizations: WorkspacesJourneyConfiguration.Builder.() -> Unit = {}
): WorkspacesJourneyConfiguration = WorkspacesJourneyConfiguration.Builder().apply {
    workspacesSelectorScreenConfiguration = WorkspaceSelectorScreenConfiguration { }
    workspacesSwitcherScreenConfiguration = WorkspaceSwitcherScreenConfiguration { }
}.apply(customizations).build()
