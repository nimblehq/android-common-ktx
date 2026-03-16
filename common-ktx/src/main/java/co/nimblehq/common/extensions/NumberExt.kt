package co.nimblehq.common.extensions

import java.math.BigDecimal

fun Int?.orZero(): Int = this ?: 0

fun Long?.orZero(): Long = this ?: 0L

fun Float?.orZero(): Float = this ?: 0f

fun Double?.orZero(): Double = this ?: 0.0

fun BigDecimal?.orZero(): BigDecimal = this ?: BigDecimal.ZERO

fun Int?.orDefault(defaultValue: Int): Int = this ?: defaultValue

fun Long?.orDefault(defaultValue: Long): Long = this ?: defaultValue

fun Float?.orDefault(defaultValue: Float): Float = this ?: defaultValue

fun Double?.orDefault(defaultValue: Double): Double = this ?: defaultValue

fun BigDecimal?.orDefault(defaultValue: BigDecimal): BigDecimal = this ?: defaultValue
