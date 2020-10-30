package com.karbonpowered.nbt

import java.util.*

data class KarbonIntArrayBinaryTag(override val value: IntArray) : IntArrayBinaryTag {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonIntArrayBinaryTag

        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }

    class Factory : IntArrayBinaryTag.Factory {
        override fun of(vararg value: Int): IntArrayBinaryTag = KarbonIntArrayBinaryTag(value)
        override fun of(value: Iterable<Int>): IntArrayBinaryTag = KarbonIntArrayBinaryTag(value.toList().toIntArray())
        override fun of(value: Iterator<Int>): IntArrayBinaryTag =
            KarbonIntArrayBinaryTag(value.asSequence().toList().toIntArray())

        override fun of(value: Collection<Int>): IntArrayBinaryTag = KarbonIntArrayBinaryTag(value.toIntArray())
        override fun of(value: UUID): IntArrayBinaryTag {
            val uuidMost = value.mostSignificantBits
            val uuidLeast = value.leastSignificantBits
            return of((uuidMost shr 32).toInt(), uuidMost.toInt(), (uuidLeast shr 32).toInt(), uuidLeast.toInt())
        }

        override fun toUniqueId(value: IntArrayBinaryTag): UUID {
            val array = value.value
            return UUID(
                array[0].toLong() shl 32 or array[1].toLong() and 4294967295L,
                array[2].toLong() shl 32 or array[3].toLong() and 4294967295L
            )
        }
    }
}