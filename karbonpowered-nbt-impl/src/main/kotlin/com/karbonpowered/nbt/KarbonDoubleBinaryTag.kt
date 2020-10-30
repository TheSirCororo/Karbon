package com.karbonpowered.nbt

data class KarbonDoubleBinaryTag(override val value: Double) : DoubleBinaryTag {
    override fun toByte(): Byte = value.toInt().toByte()
    override fun toDouble(): Double = value
    override fun toFloat(): Float = value.toFloat()
    override fun toInt(): Int = value.toInt()
    override fun toLong(): Long = value.toLong()
    override fun toShort(): Short = value.toInt().toShort()

    class Factory : DoubleBinaryTag.Factory {
        override fun of(value: Double): DoubleBinaryTag = KarbonDoubleBinaryTag(value)
    }
}