@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.nbt

import com.karbonpowered.commons.lang.loadService

interface DoubleBinaryTag : NumberBinaryTag {
    override val type: BinaryTagType<out NumberBinaryTag>
        get() = BinaryTagTypes.DOUBLE

    val value: Double

    interface Factory {
        fun of(value: Double): DoubleBinaryTag
    }

    companion object {
        @JvmStatic
        fun of(value: Double): DoubleBinaryTag = loadService<Factory>().of(value)
    }
}

inline fun DoubleBinaryTag(value: Double): DoubleBinaryTag = DoubleBinaryTag.of(value)

inline fun Double.toBinaryTag(): DoubleBinaryTag = DoubleBinaryTag.of(this)