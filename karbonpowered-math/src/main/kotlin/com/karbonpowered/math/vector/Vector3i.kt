package com.karbonpowered.math.vector

import com.karbonpowered.math.fastSqrt
import com.karbonpowered.math.floor
import java.io.Serializable

open class Vector3i(
    val x: Int,
    val y: Int,
    val z: Int
) : VectorInt, Comparable<Vector3i>, Serializable {
    private val hashCode by lazy {
        (Integer.hashCode(x) * 211 + Integer.hashCode(y)) * 97 + Integer.hashCode(z)
    }

    override val minAxis: Int
        get() = if (x < y) if (x < z) 0 else 2 else if (y < z) 1 else 2
    override val maxAxis: Int
        get() = if (x < y) if (y < z) 2 else 1 else if (x < z) 2 else 0

    constructor() : this(0, 0, 0)
    constructor(x: Double, y: Double, z: Double) : this(floor(x), floor(y), floor(z))
    constructor(vector: Vector3i) : this(vector.x, vector.y, vector.z)

    operator fun plus(vector: Vector3i): Vector3i = add(vector.x, vector.y, vector.z)
    fun add(vector: Vector3i): Vector3i = plus(vector)
    fun add(x: Double, y: Double, z: Double): Vector3i = add(floor(x), floor(y), floor(z))
    fun add(x: Int, y: Int, z: Int): Vector3i = Vector3i(this.x + x, this.y + y, this.z + z)

    operator fun minus(vector: Vector3i): Vector3i = add(vector.x, vector.y, vector.z)
    fun subtract(vector: Vector3i): Vector3i = plus(vector)
    fun subtract(x: Double, y: Double, z: Double): Vector3i = add(floor(x), floor(y), floor(z))
    fun subtract(x: Int, y: Int, z: Int): Vector3i = Vector3i(this.x - x, this.y - y, this.z - z)

    override operator fun times(int: Int): Vector3i = multiple(int)
    operator fun times(vector: Vector3i): Vector3i = multiple(vector.x, vector.y, vector.z)
    fun multiple(vector: Vector3i): Vector3i = multiple(vector.x, vector.y, vector.z)
    fun multiple(int: Int): Vector3i = multiple(int, int, int)
    fun multiple(x: Double, y: Double, z: Double): Vector3i = multiple(floor(x), floor(y), floor(z))
    fun multiple(x: Int, y: Int, z: Int): Vector3i = Vector3i(this.x * x, this.y * y, this.z * z)

    override operator fun div(int: Int): Vector3i = divide(int)
    operator fun div(vector: Vector3i): Vector3i = divide(vector.x, vector.y, vector.z)
    fun divide(vector: Vector3i): Vector3i = divide(vector.x, vector.y, vector.z)
    fun divide(int: Int): Vector3i = divide(int, int, int)
    fun divide(x: Double, y: Double, z: Double): Vector3i = divide(floor(x), floor(y), floor(z))
    fun divide(x: Int, y: Int, z: Int): Vector3i = Vector3i(this.x / x, this.y / y, this.z / z)

    fun distanceSquared(v: Vector3i): Int = distanceSquared(v.x, v.y, v.z)
    fun distanceSquared(x: Double, y: Double, z: Double): Int = distanceSquared(floor(x), floor(y), floor(z))
    fun distanceSquared(x: Int, y: Int, z: Int): Int {
        val dx = this.x - x
        val dy = this.y - y
        val dz = this.z - z
        return dx * dx + dy * dy + dz * dz
    }

    fun distance(v: Vector3i): Float = distance(v.x, v.y, v.z)
    fun distance(x: Double, y: Double, z: Double): Float = distance(floor(x), floor(y), floor(z))
    fun distance(x: Int, y: Int, z: Int): Float = fastSqrt(distanceSquared(x, y, z).toDouble()).toFloat()


    override fun length(): Float = fastSqrt(lengthSquared().toDouble()).toFloat()

    override fun lengthSquared(): Int = x * x + y * y + z * z

    override fun toArray(): IntArray = intArrayOf(x, y, z)

    override fun toInt(): VectorInt = Vector3i(x, y, z)
    override fun toDouble(): VectorDouble = Vector3d(x, y, z)

    override fun compareTo(other: Vector3i): Int = lengthSquared() - other.lengthSquared()

    override fun hashCode(): Int = hashCode

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector3i

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun toString(): String = "($x, $y, $z)"

    companion object {
        @JvmStatic
        private val serialVersionUID = 2043308035466846128L

        @JvmField
        val ZERO = Vector3i()

        @JvmField
        val ONE = Vector3i(1, 1, 1)

        @JvmField
        val UNIT_X = Vector3i(1, 0, 0)

        @JvmField
        val UNIT_Y = Vector3i(0, 1, 0)

        @JvmField
        val UNIT_Z = Vector3i(0, 0, 1)

        @JvmField
        val RIGHT = UNIT_X

        @JvmField
        val UP = UNIT_Y

        @JvmField
        val FORWARD = UNIT_Z

        fun from(n: Int): Vector3i = if (n == 0) ZERO else Vector3i(n, n, n)

        fun from(x: Int, y: Int, z: Int) = if (x == 0 && y == 0 && z == 0) ZERO else Vector3i(x, y, z)
    }
}