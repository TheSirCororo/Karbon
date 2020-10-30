package com.karbonpowered.commons.weighet

import kotlin.math.floor
import kotlin.random.Random

fun interface VariableAmount {
    fun getAmount(random: Random): Double

    fun getFlooredAmount(random: Random): Int = floor(getAmount(random)).toInt()

    data class Fixed(
        private val amount: Double
    ) : VariableAmount {
        override fun getAmount(random: Random): Double = amount
    }

    data class BaseAndVariance(
        val base: Double,
        val variance: VariableAmount
    ) : VariableAmount {
        override fun getAmount(random: Random): Double {
            val variance = variance.getAmount(random)
            return base + random.nextDouble() * variance * 2 - variance
        }
    }

    data class BaseAndAddition(
        val base: Double,
        val addition: VariableAmount
    ) : VariableAmount {
        override fun getAmount(random: Random): Double =
            base + (random.nextDouble() * addition.getAmount(random))
    }

    data class OptionalAmount(
        val chance: Double,
        val base: Double,
        val inner: VariableAmount
    ) : VariableAmount {
        override fun getAmount(random: Random): Double =
            if (random.nextDouble() < chance) inner.getAmount(random) else base
    }

    companion object {
        @JvmStatic
        fun fixed(value: Double): VariableAmount =
            Fixed(value)

        @JvmStatic
        fun range(min: Double, max: Double): VariableAmount =
            BaseAndAddition(min, fixed(max - min))

        @JvmStatic
        fun baseWithVariance(base: Double, variance: Double): VariableAmount =
            BaseAndVariance(base, fixed(variance))

        @JvmStatic
        fun baseWithVariance(base: Double, variance: VariableAmount): VariableAmount =
            BaseAndVariance(base, variance)

        @JvmStatic
        fun baseWithRandomAddition(base: Double, addition: Double): VariableAmount =
            BaseAndAddition(base, fixed(addition))

        @JvmStatic
        fun baseWithRandomAddition(base: Double, addition: VariableAmount): VariableAmount =
            BaseAndAddition(base, addition)

        @JvmStatic
        fun baseWithOptionalVariance(base: Double, variance: Double, chance: Double): VariableAmount =
            OptionalAmount(base, chance, baseWithVariance(base, variance))

        @JvmStatic
        fun baseWithOptionalVariance(base: Double, variance: VariableAmount, chance: Double): VariableAmount =
            OptionalAmount(base, chance, baseWithVariance(base, variance))

        @JvmStatic
        fun baseWithOptionalAddition(base: Double, addition: Double, chance: Double): VariableAmount =
            OptionalAmount(base, chance, baseWithRandomAddition(base, addition))

        @JvmStatic
        fun baseWithOptionalAddition(base: Double, addition: VariableAmount, chance: Double): VariableAmount =
            OptionalAmount(base, chance, baseWithRandomAddition(base, addition))
    }
}