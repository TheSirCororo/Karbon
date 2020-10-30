package com.karbonpowered.nbt

data class KarbonFloatBinaryTag(override val value: Float) : FloatBinaryTag {
    override fun toByte(): Byte = value.toInt().toByte()
    override fun toDouble(): Double = value.toDouble()
    override fun toFloat(): Float = value
    override fun toInt(): Int = value.toInt()
    override fun toLong(): Long = value.toLong()
    override fun toShort(): Short = value.toInt().toShort()

    class Factory : FloatBinaryTag.Factory {
        override fun of(value: Float): FloatBinaryTag = KarbonFloatBinaryTag(value)
    }
}