@file:JvmName("TrigMath")

package com.karbonpowered.math

import kotlin.math.sin
import kotlin.math.sqrt

// Constants
const val PI = Math.PI
const val SQUARED_PI = PI * PI
const val HALF_PI = PI / 2
const val QUARTER_PI = HALF_PI / 2
const val TWO_PI = 2 * PI
const val THREE_PI_HALVES = TWO_PI - HALF_PI
const val DEG_TO_RAD = PI / 180
const val HALF_DEG_TO_RAD = PI / 360
const val RAD_TO_DEG = 180 / PI
val SQRT_OF_TWO = sqrt(2.0)
val HALF_SQRT_OF_TWO = SQRT_OF_TWO / 2

// Trig
private const val SIN_BITS = 22
private const val SIN_SIZE = 1 shl SIN_BITS
private const val SIN_MASK = SIN_SIZE - 1
private val SIN_TABLE = FloatArray(SIN_SIZE) {
    sin((it * TWO_PI) / SIN_SIZE).toFloat()
}

private const val SIN_CONVERSION_FACTOR = SIN_SIZE / TWO_PI
private const val COS_OFFSET = SIN_SIZE / 4

// Arc trig
private const val sq2p1 = 2.414213562373095048802
private const val sq2m1 = 0.414213562373095048802
private const val p4 = 0.161536412982230228262E2
private const val p3 = 0.26842548195503973794141E3
private const val p2 = 0.11530293515404850115428136E4
private const val p1 = 0.178040631643319697105464587E4
private const val p0 = 0.89678597403663861959987488E3
private const val q4 = 0.5895697050844462222791E2
private const val q3 = 0.536265374031215315104235E3
private const val q2 = 0.16667838148816337184521798E4
private const val q1 = 0.207933497444540981287275926E4
private const val q0 = 0.89678597403663861962481162E3

private fun sinRaw(index: Int) = SIN_TABLE[index and SIN_MASK]
private fun cosRaw(index: Int) = SIN_TABLE[(index + COS_OFFSET) and SIN_MASK]

/**
 * Sine fast calculation using a table.
 *
 * **No interpolation is performed:** Accuracy is up to the 6th decimal place
 *
 * @param angle the angle in radians
 * @return the sine of the angle
 */
fun fastSin(angle: Double) = sinRaw(floor(angle * SIN_CONVERSION_FACTOR))

/**
 * Cosine fast calculation using a table.
 *
 * **No interpolation is performed:** Accuracy is up to the 6th decimal place
 *
 * @param angle the angle in radians
 * @return the cosine of the angle
 */
fun fastCos(angle: Double): Float = cosRaw(floor(angle * SIN_CONVERSION_FACTOR))

/**
 * Cosecant fast calculations using a table.
 *
 * *1 / sin(angle)*
 *
 * **No interpolation is performed:** Accuracy is up to the 6th decimal place
 *
 * @param angle the angle in radians
 * @return the cosecant of the angle
 */
fun fastCsc(angle: Double): Float = (1 / fastSin(angle))

/**
 * Secant fast calculations using a table:
 *
 * *1 / cos(angle)*
 *
 * **No interpolation is performed:** Accuracy is up to the 6th decimal place
 *
 * @param angle the angle in radians
 * @return the secant of the angle
 */
fun fastSec(angle: Double): Float = 1 / fastCos(angle)

/**
 * Tangent fast calculations using a table.
 *
 * *sin(angle) / cos(angle)*
 *
 * **No interpolation is performed:** Accuracy is up to the 6th decimal place
 *
 * @param angle in radians
 * @return the tangent of the angle
 */
fun fastTan(angle: Double): Float {
    val idx = floor(angle * SIN_CONVERSION_FACTOR)
    return sinRaw(idx) / cosRaw(idx)
}

/**
 * Cotangent fast calculations using a table.
 *
 * *cos(angle) / sin(angle)*
 *
 * **No interpolation is performed:** Accuracy is up to the 6th decimal place
 *
 * @param angle in radians
 * @return the cotangent of the angle
 */
fun fastCot(angle: Double): Float {
    val idx = floor(angle * SIN_CONVERSION_FACTOR)
    return cosRaw(idx) / sinRaw(idx)
}

/**
 * Calculates the arc sine of the value specified<br></br><br></br> Returns NaN if the input value is outside the sine range
 *
 * @param value of the sine
 * @return sine arc in radians
 */
fun asin(value: Double): Double {
    return if (value > 1) {
        Double.NaN
    } else if (value < 0) {
        -asin(-value)
    } else {
        val temp = Math.sqrt(1 - value * value)
        if (value > 0.7) {
            HALF_PI - msatan(temp / value)
        } else {
            msatan(value / temp)
        }
    }
}

/**
 * Calculates the arc cosine of the value specified<br></br><br></br> Returns NaN if the input value is outside the cosine range
 *
 * @param value of the cosine
 * @return cosine arc in radians
 */
fun acos(value: Double): Double {
    return if (value > 1 || value < -1) {
        Double.NaN
    } else {
        HALF_PI - asin(value)
    }
}

/**
 * Calculates the arc tangent of the value specified
 *
 * @param value of the tangent
 * @return tangent arc in radians
 */
fun atan(value: Double): Double {
    return if (value > 0) {
        msatan(value)
    } else {
        -msatan(-value)
    }
}

/**
 * Computes the phase theta by computing an arc tangent of y/x
 *
 * Gets the yaw rotation component in radians when looking into the direction specified
 *
 * @param y direction
 * @param x direction
 * @return tangent arc in radians
 */
fun fastAtan2(y: Double, x: Double): Double {
    var result = y
    if (result + x == result) {
        return if (result >= 0) HALF_PI else -HALF_PI
    }
    result = atan(result / x)
    return if (x < 0) {
        if (result <= 0) result + PI else result - PI
    } else result
}

/**
 * Calculates the arc cosecant of the value specified
 *
 * Returns NaN if the input value is outside the cosecant range
 *
 * @param value of the cosecant
 * @return cosecant arc in radians
 */
fun fastAcsc(value: Double): Double = if (value == 0.0) Double.NaN else asin(1 / value)

/**
 * Calculates the arc secant of the value specified
 *
 * Returns NaN if the input value is outside the secant range
 *
 * @param value of the secant
 * @return secant arc in radians
 */
fun fastAsec(value: Double): Double = if (value == 0.0) Double.NaN else acos(1 / value)

/**
 * Calculates the arc cotangent of the value specified
 *
 * Returns NaN if the input value is outside the cotangent range
 *
 * @param value of the cotangent
 * @return cotangent arc in radians
 */
fun fastAcot(value: Double): Double = when {
    value == 0.0 -> Double.NaN
    value > 0 -> atan(1 / value)
    else -> atan(1 / value) + PI
}

private fun mxatan(arg: Double): Double {
    val argsq = arg * arg
    var value = (((p4 * argsq + p3) * argsq + p2) * argsq + p1) * argsq + p0
    value /= ((((argsq + q4) * argsq + q3) * argsq + q2) * argsq + q1) * argsq + q0
    return value * arg
}

private fun msatan(arg: Double): Double {
    if (arg < sq2m1) {
        return mxatan(arg)
    }
    return if (arg > sq2p1) {
        HALF_PI - mxatan(1 / arg)
    } else HALF_PI / 2 + mxatan((arg - 1) / (arg + 1))
}