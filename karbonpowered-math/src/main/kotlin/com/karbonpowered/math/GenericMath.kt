@file:JvmName("GenericMath")

package com.karbonpowered.math

/**
 * Returns a fast estimate of the inverse square root of the value
 *
 * @param a The value
 * @return The estimate of the inverse square root
 */
fun inverseSqrt(a: Double): Double {
    var result = a
    val halfA = 0.5 * result
    result =
        java.lang.Double.longBitsToDouble(0x5FE6EB50C7B537AAL - (java.lang.Double.doubleToRawLongBits(result) shr 1))
    return result * (1.5 - halfA * result * result)
}

/**
 * Returns a fast estimate of the square root of the value
 *
 * @param a The value
 * @return The estimate of the square root
 */
fun fastSqrt(a: Double): Double {
    return a * inverseSqrt(a)
}

/**
 * Rounds 'a' down to the closest integer
 *
 * @param a The value to floor
 * @return The closest integer
 */
fun floor(a: Double): Int {
    val y = a.toInt()
    return if (a < y) {
        y - 1
    } else y
}

/**
 * Rounds 'a' down to the closest integer
 *
 * @param a The value to floor
 * @return The closest integer
 */
fun floor(a: Float): Int {
    val y = a.toInt()
    return if (a < y) {
        y - 1
    } else y
}

/**
 * Rounds 'a' down to the closest long
 *
 * @param a The value to floor
 * @return The closest long
 */
fun floorL(a: Double): Long {
    val y = a.toLong()
    return if (a < y) {
        y - 1
    } else y
}

/**
 * Rounds 'a' down to the closest long
 *
 * @param a The value to floor
 * @return The closest long
 */
fun floorL(a: Float): Long {
    val y = a.toLong()
    return if (a < y) {
        y - 1
    } else y
}

fun clamp(value: Int, min: Int, max: Int): Int = if (value < min) min else if (value > max) max else value
fun clamp(value: Long, min: Long, max: Long): Long = if (value < min) min else if (value > max) max else value
fun clamp(value: Float, min: Float, max: Float): Float = if (value < min) min else if (value > max) max else value
fun clamp(value: Double, min: Double, max: Double): Double = if (value < min) min else if (value > max) max else value