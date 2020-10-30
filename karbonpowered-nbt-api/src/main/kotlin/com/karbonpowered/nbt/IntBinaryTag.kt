@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.nbt

import com.karbonpowered.commons.lang.loadService

interface IntBinaryTag : NumberBinaryTag {
    override val type: BinaryTagType<out NumberBinaryTag>
        get() = BinaryTagTypes.INT

    val value: Int

    interface Factory {
        fun of(value: Int): IntBinaryTag
    }

    companion object {
        @JvmStatic
        fun of(value: Int): IntBinaryTag = loadService<Factory>().of(value)
    }
}

inline fun IntBinaryTag(value: Int): IntBinaryTag = IntBinaryTag.of(value)

inline fun Int.toBinaryTag(): IntBinaryTag = IntBinaryTag.of(this)