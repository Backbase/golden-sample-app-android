package com.backbase.android.test_data

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAssertion

fun hasItems(): ViewAssertion {
    return ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null) throw noViewFoundException
        val recyclerView = view as RecyclerView
        val itemCount = recyclerView.adapter?.itemCount ?: 0
        assert(itemCount > 0) {
            "RecyclerView has no items. Item count = $itemCount"
        }
    }
}
