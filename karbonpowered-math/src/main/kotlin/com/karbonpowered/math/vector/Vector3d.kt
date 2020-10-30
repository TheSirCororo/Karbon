package com.karbonpowered.math.vector

import com.karbonpowered.math.fastCos
import com.karbonpowered.math.fastSin
import com.karbonpowered.math.fastSqrt
import com.karbonpowered.math.floor
import java.io.Serializable
import java.util.*
import kotlin.math.sign

open class Vector3d(
    val x: Double,
    val y: Double,
    val z: Double
) : VectorDouble, Comparable<Vector3d>, Serializable {
    private val hashCode by lazy {
        var result = if (x != 0.0) java.lang.Double.hashCode(x) else 0
        result = 31 * result + if (y != 0.0) java.lang.Double.hashCode(y) else 0
        result = 31 * result + if (z != 0.0) java.lang.Double.hashCode(z) else 0
        result
    }

    override val minAxis: Int
        get() = if (x < y) if (x < z) 0 else 2 else if (y < z) 1 else 2
    override val maxAxis: Int
        get() = if (x < y) if (y < z) 2 else 1 else if (x < z) 2 else 0

    val floorX: Int by lazy { floor(x) }
    val floorY: Int by lazy { floor(y) }
    val floorZ: Int by lazy { floor(z) }

    constructor() : this(0.0, 0.0, 0.0)
    constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())
    constructor(x: Float, y: Float, z: Float) : this(x.toDouble(), y.toDouble(), z.toDouble())
    constructor(vector: Vector3i) : this(vector.x, vector.y, vector.z)
    constructor(vector: Vector3d) : this(vector.x, vector.y, vector.z)

    operator fun plus(vector: Vector3i): Vector3d = add(vector.x, vector.y, vector.z)
    operator fun plus(vector: Vector3d): Vector3d = add(vector.x, vector.y, vector.z)
    fun add(vector: Vector3i): Vector3d = plus(vector)
    fun add(vector: Vector3d): Vector3d = plus(vector)
    fun add(x: Int, y: Int, z: Int): Vector3d = add(x.toDouble(), y.toDouble(), z.toDouble())
    fun add(x: Float, y: Float, z: Float): Vector3d = add(x.toDouble(), y.toDouble(), z.toDouble())
    fun add(x: Double, y: Double, z: Double): Vector3d = Vector3d(this.x + x, this.y + y, this.z + z)

    operator fun minus(vector: Vector3i): Vector3d = add(vector.x, vector.y, vector.z)
    operator fun minus(vector: Vector3d): Vector3d = add(vector.x, vector.y, vector.z)
    fun subtract(vector: Vector3i): Vector3d = minus(vector)
    fun subtract(vector: Vector3d): Vector3d = minus(vector)
    fun subtract(x: Int, y: Int, z: Int): Vector3d = subtract(x.toDouble(), y.toDouble(), z.toDouble())
    fun subtract(x: Float, y: Float, z: Float): Vector3d = subtract(x.toDouble(), y.toDouble(), z.toDouble())
    fun subtract(x: Double, y: Double, z: Double): Vector3d = Vector3d(this.x - x, this.y - y, this.z - z)

    override operator fun times(int: Int): Vector3d = multiple(int)
    override operator fun times(float: Float): Vector3d = multiple(float)
    override operator fun times(double: Double): Vector3d = multiple(double)
    operator fun times(vector: Vector3i): Vector3d = multiple(vector.x, vector.y, vector.z)
    operator fun times(vector: Vector3d): Vector3d = multiple(vector.x, vector.y, vector.z)
    fun multiple(vector: Vector3d): Vector3d = multiple(vector.x, vector.y, vector.z)
    fun multiple(int: Int): Vector3d = multiple(int.toDouble())
    fun multiple(float: Float): Vector3d = multiple(float.toDouble())
    fun multiple(double: Double): Vector3d = multiple(double, double, double)
    fun multiple(x: Int, y: Int, z: Int): Vector3d = multiple(x.toDouble(), y.toDouble(), z.toDouble())
    fun multiple(x: Float, y: Float, z: Float): Vector3d = multiple(x.toDouble(), y.toDouble(), z.toDouble())
    fun multiple(x: Double, y: Double, z: Double): Vector3d = Vector3d(this.x * x, this.y * y, this.z * z)

    override operator fun div(int: Int): Vector3d = divide(int)
    override operator fun div(float: Float): Vector3d = divide(float)
    override operator fun div(double: Double): Vector3d = divide(double)
    operator fun div(vector: Vector3i): Vector3d = divide(vector.x, vector.y, vector.z)
    operator fun div(vector: Vector3d): Vector3d = divide(vector.x, vector.y, vector.z)
    fun divide(vector: Vector3d): Vector3d = divide(vector.x, vector.y, vector.z)
    fun divide(int: Int): Vector3d = divide(int.toDouble())
    fun divide(float: Float): Vector3d = divide(float.toDouble())
    fun divide(double: Double): Vector3d = divide(double, double, double)
    fun divide(x: Int, y: Int, z: Int): Vector3d = divide(x.toDouble(), y.toDouble(), z.toDouble())
    fun divide(x: Float, y: Float, z: Float): Vector3d = divide(x.toDouble(), y.toDouble(), z.toDouble())
    fun divide(x: Double, y: Double, z: Double): Vector3d = Vector3d(this.x / x, this.y / y, this.z / z)

    fun distanceSquared(v: Vector3i): Double = distanceSquared(v.x, v.y, v.z)
    fun distanceSquared(v: Vector3d): Double = distanceSquared(v.x, v.y, v.z)
    fun distanceSquared(x: Int, y: Int, z: Int): Double = distanceSquared(x.toDouble(), y.toDouble(), z.toDouble())
    fun distanceSquared(x: Float, y: Float, z: Float): Double =
        distanceSquared(x.toDouble(), y.toDouble(), z.toDouble())

    fun distanceSquared(x: Double, y: Double, z: Double): Double {
        val dx = this.x - x
        val dy = this.y - y
        val dz = this.z - z
        return dx * dx + dy * dy + dz * dz
    }

    fun distance(v: Vector3d): Double = distance(v.x, v.y, v.z)
    fun distance(v: Vector3i): Double = distance(v.x, v.y, v.z)
    fun distance(x: Int, y: Int, z: Int): Double = distance(x.toDouble(), y.toDouble(), z.toDouble())
    fun distance(x: Float, y: Float, z: Float): Double = distance(x.toDouble(), y.toDouble(), z.toDouble())
    fun distance(x: Double, y: Double, z: Double): Double = fastSqrt(distanceSquared(x, y, z))

    override fun length(): Double = fastSqrt(lengthSquared())

    override fun lengthSquared(): Double = x * x + y * y + z * z

    override fun toArray(): DoubleArray = doubleArrayOf(x, y, z)

    override fun toInt(): VectorInt = Vector3i(x, y, z)
    override fun toDouble(): VectorDouble = Vector3d(x, y, z)

    override fun compareTo(other: Vector3d): Int = sign(lengthSquared() - other.lengthSquared()).toInt()

    override fun hashCode(): Int = hashCode

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector3d

        if (x.compareTo(other.x) != 0) return false
        if (y.compareTo(other.y) != 0) return false
        if (z.compareTo(other.z) != 0) return false

        return true
    }

    override fun toString(): String = "($x, $y, $z)"

    companion object {
        @JvmStatic
        private val serialVersionUID = 2240979473679718008

        @JvmField
        val ZERO = Vector3d()

        @JvmField
        val ONE = Vector3d(1.0, 1.0, 1.0)

        @JvmField
        val UNIT_X = Vector3d(1.0, 0.0, 0.0)

        @JvmField
        val UNIT_Y = Vector3d(0.0, 1.0, 0.0)

        @JvmField
        val UNIT_Z = Vector3d(0.0, 0.0, 1.0)

        @JvmField
        val RIGHT = UNIT_X

        @JvmField
        val UP = UNIT_Y

        @JvmField
        val FORWARD = UNIT_Z

        fun from(n: Int): Vector3d = if (n == 0) ZERO else Vector3d(n, n, n)
        fun from(n: Float): Vector3d = if (n == 0.0f) ZERO else Vector3d(n, n, n)
        fun from(n: Double): Vector3d = if (n == 0.0) ZERO else Vector3d(n, n, n)

        fun from(x: Int, y: Int, z: Int) = if (x == 0 && y == 0 && z == 0) ZERO else Vector3d(x, y, z)
        fun from(x: Float, y: Float, z: Float) = if (x == 0.0f && y == 0.0f && z == 0.0f) ZERO else Vector3d(x, y, z)
        fun from(x: Double, y: Double, z: Double) = if (x == 0.0 && y == 0.0 && z == 0.0) ZERO else Vector3d(x, y, z)

        @JvmOverloads
        fun createRandomDirection(random: Random = Random()): Vector3d =
            createDirectionRad(random.nextDouble() * 6.283185307179586, random.nextDouble() * 6.283185307179586)

        fun createDirectionDeg(theta: Float, phi: Float): Vector3d =
            createDirectionDeg(theta.toDouble(), phi.toDouble())

        fun createDirectionDeg(theta: Double, phi: Double): Vector3d =
            createDirectionRad(Math.toRadians(theta), Math.toRadians(phi))

        fun createDirectionRad(theta: Float, phi: Float): Vector3d =
            createDirectionRad(theta.toDouble(), phi.toDouble())

        fun createDirectionRad(theta: Double, phi: Double): Vector3d {
            val f = fastSin(phi).toDouble()
            return Vector3d(f * fastCos(theta).toDouble(), f * fastSin(theta).toDouble(), fastCos(phi).toDouble())
        }
    }
}