package com.backbase.android.test_data

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

object NumberGenerator {

    fun randomFloat(min: Int = 1, max: Int = 10000): Float {
        return (min..max).random().toFloat() + Random.nextFloat().round(2)
    }

    fun Float.round(decimalPlace: Int): Float {
        var bigDecimal = BigDecimal(this.toString())
        bigDecimal = bigDecimal.setScale(decimalPlace, RoundingMode.HALF_EVEN)
        return bigDecimal.toFloat()
    }
}
