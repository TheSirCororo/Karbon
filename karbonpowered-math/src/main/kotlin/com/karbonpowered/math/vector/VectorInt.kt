package com.karbonpowered.math.vector

interface VectorInt : Vector {
    val minAxis: Int
    val maxAxis: Int

    operator fun times(int: Int): VectorInt

    operator fun div(int: Int): VectorInt

    fun length(): Float

    fun lengthSquared(): Int

    fun toArray(): IntArray

    fun toInt(): VectorInt
    fun toDouble(): VectorDouble
}