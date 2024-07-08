package com.backbase.golden_sample_app.extend_journey.contacts.presentation.ui

import android.graphics.Color
import androidx.core.graphics.toColor
import kotlin.math.abs
import kotlin.math.floor

object ColorUtils {

    fun getHashOfString(str: String): Int {
        var hash = 0
        str.forEachIndexed { index, _ ->
            hash = (str[index] + ((hash shl 5) - hash)).code
        }
        hash = abs(hash)
        return hash
    }

    fun normalizeHash(hash: Int, min: Int, max: Int): Int {
        return floor(
            ((hash % (max - min)) + min)
                .toDouble()
        ).toInt()
    }

    fun generateHsl(name: String, minH: Int = 0, minS: Int = 0, minL: Int = 0): IntArray {
        val hash = getHashOfString(name)
        val h = normalizeHash(hash, minH, 360)
        val s = normalizeHash(hash, minS, 100)
        val l = normalizeHash(hash, minL, 100)
        return intArrayOf(h, s, l)
    }

    fun generateColor(name: String, minH: Int = 0, minS: Int = 0, minL: Int = 0): Color {
        val hsl = generateHsl(name, minH, minS, minL)
        return Color.rgb(hsl[0], hsl[1], hsl[2]).toColor()
    }
}
