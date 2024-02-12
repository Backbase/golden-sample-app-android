package com.backbase.analytics

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.backbase.android.observability.Tracker
import com.backbase.android.observability.event.ScreenViewEvent
import com.backbase.android.observability.event.UserActionEvent

/**
 * Created by Backbase R&D B.V on 05/10/2021.
 * Extension functions for screens.
 */
fun Fragment.publishScreenViewEvent(tracker: Tracker, event: ScreenViewEvent) =
    tracker.publish(scope = lifecycleScope, event = event)

fun Fragment.publishUserActionEvent(tracker: Tracker, event: UserActionEvent) =
    tracker.publish(scope = lifecycleScope, event = event)
