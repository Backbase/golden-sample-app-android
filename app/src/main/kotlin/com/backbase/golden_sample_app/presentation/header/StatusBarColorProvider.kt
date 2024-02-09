package com.backbase.golden_sample_app.presentation.header

import androidx.appcompat.app.AppCompatActivity
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.design.R
import com.backbase.golden_sample_app.presentation.bottom.repeatOnStarted
import com.google.android.material.color.MaterialColors
import kotlinx.coroutines.flow.Flow

fun AppCompatActivity.updateStatusBarColor(isInRootScreen: Flow<Boolean>) = repeatOnStarted {
    isInRootScreen.collect { isInRootScreen ->
        val context = this@updateStatusBarColor

        window.statusBarColor = MaterialColors
            .getColorOrNull(context, if (isInRootScreen) R.attr.tabHeaderStatusBarColor else R.attr.colorFoundation)
            ?: run {
                BBLogger.warning("", "Cannot update status bar color, please check that your theme is extending from Theme.Backbase.")
                return@collect
            }
    }
}
