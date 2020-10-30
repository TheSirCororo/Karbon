package com.karbonpowered.nbt

data class KarbonByteBinaryTag(override val value: Byte) : ByteBinaryTag {
    override fun toByte(): Byte = value
    override fun toDouble(): Double = value.toDouble()
    override fun toFloat(): Float = value.toFloat()
    override fun toInt(): Int = value.toInt()
    override fun toLong(): Long = value.toLong()
    override fun toShort(): Short = value.toShort()

    class Factory : ByteBinaryTag.Factory {
        override fun of(value: Byte): ByteBinaryTag = KarbonByteBinaryTag(value)
        override fun of(value: Boolean): ByteBinaryTag = if (value) of(1) else of(0)
    }
}