package com.karbonpowered.nbt

data class KarbonIntBinaryTag(override val value: Int) : IntBinaryTag {
    override fun toByte(): Byte = value.toByte()
    override fun toDouble(): Double = value.toDouble()
    override fun toFloat(): Float = value.toFloat()
    override fun toInt(): Int = value
    override fun toLong(): Long = value.toLong()
    override fun toShort(): Short = value.toShort()

    class Factory : IntBinaryTag.Factory {
        override fun of(value: Int): IntBinaryTag = KarbonIntBinaryTag(value)
    }
}