package com.karbonpowered.nbt

data class KarbonLongArrayBinaryTag(override val value: LongArray) : LongArrayBinaryTag {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonLongArrayBinaryTag

        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }

    class Factory : LongArrayBinaryTag.Factory {
        override fun of(vararg value: Long): LongArrayBinaryTag = KarbonLongArrayBinaryTag(value)
        override fun of(value: Iterable<Long>): LongArrayBinaryTag =
            KarbonLongArrayBinaryTag(value.toList().toLongArray())

        override fun of(value: Iterator<Long>): LongArrayBinaryTag =
            KarbonLongArrayBinaryTag(value.asSequence().toList().toLongArray())

        override fun of(value: Collection<Long>): LongArrayBinaryTag = KarbonLongArrayBinaryTag(value.toLongArray())
    }
}