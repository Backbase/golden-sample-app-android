package com.backbase.golden_sample_app.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

fun AppCompatActivity.selectedTheme() = themePreferences.getInt(ThemeResIdKey, Int.MAX_VALUE)

fun AppCompatActivity.setDynamicThemeIfApply() {
    val themeResId = themePreferences.getInt(ThemeResIdKey, Int.MAX_VALUE)
    if (themeResId != Int.MAX_VALUE) setTheme(themeResId)
    observeThemeChanges()
}

fun AppCompatActivity.setThemeTo(@StyleRes themeResId: Int) {
    themePreferences.edit(commit = true) { putInt(ThemeResIdKey, themeResId) }
}

fun AppCompatActivity.setThemeToDefault() {
    themePreferences.edit(commit = true) { putInt(ThemeResIdKey, Int.MAX_VALUE) }
}

/* region helper functions */
private fun AppCompatActivity.observeThemeChanges() {
    lifecycleScope.launch { themePreferences.selectedThemeFlow.collect { recreate() } }
}

val AppCompatActivity.themePreferences: SharedPreferences
    get() = getSharedPreferences(ThemePreferencesKey, Context.MODE_PRIVATE)

private val SharedPreferences.selectedThemeFlow
    get() = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (ThemeResIdKey == key) trySend(getInt(key, /* defValue = */ Int.MAX_VALUE))
        }
        registerOnSharedPreferenceChangeListener(listener)
        awaitClose { unregisterOnSharedPreferenceChangeListener(listener) }
    }

private const val ThemePreferencesKey = "theme_preferences"
private const val ThemeResIdKey = "theme_id"
/* endregion helper functions */