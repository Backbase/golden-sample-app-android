package com.backbase.android.journey.contacts.domain.model.util

/**
 * A simple container for a generic type. This class allows domain models to have a generic type
 * extension without requiring explicit declaration when interacting with the class.
 *
 * Consumers of the extension will need to type cast the value when accessing it.
 *
 * @param <T> the type of the extension
 * @property extension the generic extension value
 */
data class ModelExtension<T>(
    val extension: T
)