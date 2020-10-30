@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.nbt

import com.karbonpowered.commons.lang.loadService

interface LongArrayBinaryTag : BinaryTag {
    override val type: BinaryTagType<out BinaryTag>
        get() = BinaryTagTypes.LONG_ARRAY

    val value: LongArray

    interface Factory {
        fun of(vararg value: Long): LongArrayBinaryTag
        fun of(value: Iterable<Long>): LongArrayBinaryTag
        fun of(value: Iterator<Long>): LongArrayBinaryTag
        fun of(value: Collection<Long>): LongArrayBinaryTag
    }

    companion object {
        @JvmStatic
        fun of(vararg value: Long): LongArrayBinaryTag = loadService<Factory>().of(*value)

        @JvmStatic
        fun of(value: Iterable<Long>): LongArrayBinaryTag = loadService<Factory>().of(value)

        @JvmStatic
        fun of(value: Iterator<Long>): LongArrayBinaryTag = loadService<Factory>().of(value)

        @JvmStatic
        fun of(value: Collection<Long>): LongArrayBinaryTag = loadService<Factory>().of(value)
    }
}

inline fun LongArrayBinaryTag(vararg value: Long): LongArrayBinaryTag = LongArrayBinaryTag.of(*value)
inline fun LongArrayBinaryTag(value: Iterable<Long>): LongArrayBinaryTag = LongArrayBinaryTag.of(value)
inline fun LongArrayBinaryTag(value: Iterator<Long>): LongArrayBinaryTag = LongArrayBinaryTag.of(value)
inline fun LongArrayBinaryTag(value: Collection<Long>): LongArrayBinaryTag = LongArrayBinaryTag.of(value)

inline fun LongArray.toBinaryTag(): LongArrayBinaryTag = LongArrayBinaryTag.of(*this)
inline fun Iterable<Long>.toBinaryTag(): LongArrayBinaryTag = LongArrayBinaryTag.of(this)
inline fun Iterator<Long>.toBinaryTag(): LongArrayBinaryTag = LongArrayBinaryTag.of(this)
inline fun Collection<Long>.toBinaryTag(): LongArrayBinaryTag = LongArrayBinaryTag.of(this)