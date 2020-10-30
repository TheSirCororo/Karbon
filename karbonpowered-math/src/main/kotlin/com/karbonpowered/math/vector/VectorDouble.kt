package com.karbonpowered.math.vector

interface VectorDouble : Vector {
    val minAxis: Int
    val maxAxis: Int

    operator fun times(int: Int): Vector3d
    operator fun times(float: Float): Vector3d
    operator fun times(double: Double): VectorDouble

    operator fun div(int: Int): Vector3d
    operator fun div(float: Float): Vector3d
    operator fun div(double: Double): VectorDouble

    fun length(): Double

    fun lengthSquared(): Double

    fun toArray(): DoubleArray

    fun toInt(): VectorInt
    fun toDouble(): VectorDouble
}