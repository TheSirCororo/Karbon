package com.karbonpowered.nbt

data class KarbonLongBinaryTag(override val value: Long) : LongBinaryTag {
    override fun toByte(): Byte = value.toByte()
    override fun toDouble(): Double = value.toDouble()
    override fun toFloat(): Float = value.toFloat()
    override fun toInt(): Int = value.toInt()
    override fun toLong(): Long = value
    override fun toShort(): Short = value.toShort()

    class Factory : LongBinaryTag.Factory {
        override fun of(value: Long): LongBinaryTag = KarbonLongBinaryTag(value)
    }
}