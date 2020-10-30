@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.nbt

import com.karbonpowered.commons.lang.loadService

interface ByteArrayBinaryTag : BinaryTag {
    override val type: BinaryTagType<out BinaryTag>
        get() = BinaryTagTypes.BYTE_ARRAY

    val value: ByteArray

    interface Factory {
        fun of(vararg value: Byte): ByteArrayBinaryTag
        fun of(value: Iterable<Byte>): ByteArrayBinaryTag
        fun of(value: Iterator<Byte>): ByteArrayBinaryTag
        fun of(value: Collection<Byte>): ByteArrayBinaryTag
    }

    companion object {
        @JvmStatic
        fun of(vararg value: Byte): ByteArrayBinaryTag = loadService<Factory>().of(*value)

        @JvmStatic
        fun of(value: Iterable<Byte>): ByteArrayBinaryTag = loadService<Factory>().of(value)

        @JvmStatic
        fun of(value: Iterator<Byte>): ByteArrayBinaryTag = loadService<Factory>().of(value)

        @JvmStatic
        fun of(value: Collection<Byte>): ByteArrayBinaryTag = loadService<Factory>().of(value)
    }
}

inline fun ByteArrayBinaryTag(vararg value: Byte): ByteArrayBinaryTag = ByteArrayBinaryTag.of(*value)
inline fun ByteArrayBinaryTag(value: Iterable<Byte>): ByteArrayBinaryTag = ByteArrayBinaryTag.of(value)
inline fun ByteArrayBinaryTag(value: Iterator<Byte>): ByteArrayBinaryTag = ByteArrayBinaryTag.of(value)
inline fun ByteArrayBinaryTag(value: Collection<Byte>): ByteArrayBinaryTag = ByteArrayBinaryTag.of(value)

inline fun ByteArray.toBinaryTag(): ByteArrayBinaryTag = ByteArrayBinaryTag.of(*this)
inline fun Iterable<Byte>.toBinaryTag(): ByteArrayBinaryTag = ByteArrayBinaryTag.of(this)
inline fun Iterator<Byte>.toBinaryTag(): ByteArrayBinaryTag = ByteArrayBinaryTag.of(this)
inline fun Collection<Byte>.toBinaryTag(): ByteArrayBinaryTag = ByteArrayBinaryTag.of(this)