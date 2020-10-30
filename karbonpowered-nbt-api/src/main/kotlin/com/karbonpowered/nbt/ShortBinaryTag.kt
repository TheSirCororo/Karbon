@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.nbt

import com.karbonpowered.commons.lang.loadService

interface ShortBinaryTag : NumberBinaryTag {
    override val type: BinaryTagType<out NumberBinaryTag>
        get() = BinaryTagTypes.SHORT

    val value: Short

    interface Factory {
        fun of(value: Short): ShortBinaryTag
    }

    companion object {
        fun of(value: Short): ShortBinaryTag = loadService<Factory>().of(value)
    }
}

inline fun ShortBinaryTag(value: Short): ShortBinaryTag = ShortBinaryTag.of(value)

inline fun Short.toBinaryTag(): ShortBinaryTag = ShortBinaryTag.of(this)