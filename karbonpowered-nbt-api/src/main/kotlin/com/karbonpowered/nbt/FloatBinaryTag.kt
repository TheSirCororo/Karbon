@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.nbt

import com.karbonpowered.commons.lang.loadService

interface FloatBinaryTag : NumberBinaryTag {
    override val type: BinaryTagType<out NumberBinaryTag>
        get() = BinaryTagTypes.FLOAT

    val value: Float

    interface Factory {
        fun of(value: Float): FloatBinaryTag
    }

    companion object {
        private val factory = loadService<Factory>()

        @JvmStatic
        fun of(value: Float): FloatBinaryTag = factory.of(value)
    }
}

inline fun FloatBinaryTag(value: Float): FloatBinaryTag = FloatBinaryTag.of(value)

inline fun Float.toBinaryTag(): FloatBinaryTag = FloatBinaryTag.of(this)