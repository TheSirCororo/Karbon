@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.nbt

import com.karbonpowered.commons.lang.loadService

interface LongBinaryTag : NumberBinaryTag {
    override val type: BinaryTagType<out NumberBinaryTag>
        get() = BinaryTagTypes.LONG

    val value: Long

    interface Factory {
        fun of(value: Long): LongBinaryTag
    }

    companion object {
        @JvmStatic
        fun of(value: Long): LongBinaryTag = loadService<Factory>().of(value)
    }
}

inline fun LongBinaryTag(value: Long): LongBinaryTag = LongBinaryTag.of(value)

inline fun Long.toBinaryTag(): LongBinaryTag = LongBinaryTag.of(this)