package com.karbonpowered.nbt

data class KarbonShortBinaryTag(override val value: Short) : ShortBinaryTag {
    override fun toByte(): Byte = value.toByte()
    override fun toDouble(): Double = value.toDouble()
    override fun toFloat(): Float = value.toFloat()
    override fun toInt(): Int = value.toInt()
    override fun toLong(): Long = value.toLong()
    override fun toShort(): Short = value

    class Factory : ShortBinaryTag.Factory {
        override fun of(value: Short): ShortBinaryTag = KarbonShortBinaryTag(value)
    }
}