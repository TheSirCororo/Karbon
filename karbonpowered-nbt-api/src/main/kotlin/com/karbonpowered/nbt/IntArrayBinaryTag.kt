@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.nbt

import com.karbonpowered.commons.lang.loadService
import java.util.*

interface IntArrayBinaryTag : BinaryTag {
    override val type: BinaryTagType<out BinaryTag>
        get() = BinaryTagTypes.INT_ARRAY

    val value: IntArray

    interface Factory {
        fun of(vararg value: Int): IntArrayBinaryTag
        fun of(value: Iterable<Int>): IntArrayBinaryTag
        fun of(value: Iterator<Int>): IntArrayBinaryTag
        fun of(value: Collection<Int>): IntArrayBinaryTag
        fun of(value: UUID): IntArrayBinaryTag
        fun toUniqueId(value: IntArrayBinaryTag): UUID
    }

    companion object {
        @JvmStatic
        fun of(vararg value: Int): IntArrayBinaryTag = loadService<Factory>().of(*value)

        @JvmStatic
        fun of(value: Iterable<Int>): IntArrayBinaryTag = loadService<Factory>().of(value)

        @JvmStatic
        fun of(value: Iterator<Int>): IntArrayBinaryTag = loadService<Factory>().of(value)

        @JvmStatic
        fun of(value: Collection<Int>): IntArrayBinaryTag = loadService<Factory>().of(value)

        @JvmStatic
        fun of(value: UUID): IntArrayBinaryTag = loadService<Factory>().of(value)

        @JvmStatic
        fun toUniqueId(value: IntArrayBinaryTag) = loadService<Factory>().toUniqueId(value)
    }
}

inline fun IntArrayBinaryTag(vararg value: Int): IntArrayBinaryTag = IntArrayBinaryTag.of(*value)
inline fun IntArrayBinaryTag(value: Iterable<Int>): IntArrayBinaryTag = IntArrayBinaryTag.of(value)
inline fun IntArrayBinaryTag(value: Iterator<Int>): IntArrayBinaryTag = IntArrayBinaryTag.of(value)
inline fun IntArrayBinaryTag(value: Collection<Int>): IntArrayBinaryTag = IntArrayBinaryTag.of(value)
inline fun IntArrayBinaryTag(value: UUID): IntArrayBinaryTag = IntArrayBinaryTag.of(value)

inline fun IntArray.toBinaryTag(): IntArrayBinaryTag = IntArrayBinaryTag.of(*this)
inline fun Iterable<Int>.toBinaryTag(): IntArrayBinaryTag = IntArrayBinaryTag.of(this)
inline fun Iterator<Int>.toBinaryTag(): IntArrayBinaryTag = IntArrayBinaryTag.of(this)
inline fun Collection<Int>.toBinaryTag(): IntArrayBinaryTag = IntArrayBinaryTag.of(this)
inline fun UUID.toBinaryTag(): IntArrayBinaryTag = IntArrayBinaryTag.of(this)
inline fun IntArrayBinaryTag.toUniqueId(): UUID = IntArrayBinaryTag.toUniqueId(this)