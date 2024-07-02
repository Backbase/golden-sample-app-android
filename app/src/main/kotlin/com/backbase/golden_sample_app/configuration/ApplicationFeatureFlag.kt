package com.backbase.golden_sample_app.configuration

sealed interface ApplicationFeatureFlag {
    data object ContactsJourneyFeatureFlag : ApplicationFeatureFlag
}

inline fun <reified T : ApplicationFeatureFlag> List<ApplicationFeatureFlag>.getFeatureFlagOrNull(): T? {
    return firstOrNull { it is T } as T?
}

inline fun <reified T : ApplicationFeatureFlag> List<ApplicationFeatureFlag>.hasFeatureFlag(): Boolean {
    return getFeatureFlagOrNull<T>() != null
}
