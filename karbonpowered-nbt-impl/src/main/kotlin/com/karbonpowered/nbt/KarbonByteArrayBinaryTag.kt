package com.karbonpowered.nbt

data class KarbonByteArrayBinaryTag(override val value: ByteArray) : ByteArrayBinaryTag {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonByteArrayBinaryTag

        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }

    class Factory : ByteArrayBinaryTag.Factory {
        override fun of(vararg value: Byte): ByteArrayBinaryTag = KarbonByteArrayBinaryTag(value)
        override fun of(value: Iterable<Byte>): ByteArrayBinaryTag =
            KarbonByteArrayBinaryTag(value.toList().toByteArray())

        override fun of(value: Iterator<Byte>): ByteArrayBinaryTag =
            KarbonByteArrayBinaryTag(value.asSequence().toList().toByteArray())

        override fun of(value: Collection<Byte>): ByteArrayBinaryTag = KarbonByteArrayBinaryTag(value.toByteArray())
    }
}