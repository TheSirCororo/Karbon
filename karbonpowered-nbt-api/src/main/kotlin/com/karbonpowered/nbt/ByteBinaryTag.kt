@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.nbt

import com.karbonpowered.commons.lang.loadService

interface ByteBinaryTag : NumberBinaryTag {
    override val type: BinaryTagType<out NumberBinaryTag>
        get() = BinaryTagTypes.BYTE

    val value: Byte

    interface Factory {
        fun of(value: Byte): ByteBinaryTag
        fun of(value: Boolean): ByteBinaryTag
    }

    companion object {
        @JvmStatic
        fun of(value: Byte): ByteBinaryTag = loadService<Factory>().of(value)

        @JvmStatic
        fun of(value: Boolean): ByteBinaryTag = loadService<Factory>().of(value)
    }
}

inline fun ByteBinaryTag(value: Byte): ByteBinaryTag = ByteBinaryTag.of(value)
inline fun ByteBinaryTag(value: Boolean): ByteBinaryTag = ByteBinaryTag.of(value)

inline fun Byte.toBinaryTag(): ByteBinaryTag = ByteBinaryTag.of(this)
inline fun Boolean.toBinaryTag(): ByteBinaryTag = ByteBinaryTag.of(this)