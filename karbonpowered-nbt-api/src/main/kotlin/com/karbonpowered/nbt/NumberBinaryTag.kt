package com.karbonpowered.nbt

interface NumberBinaryTag : BinaryTag {
    override val type: BinaryTagType<out NumberBinaryTag>

    fun toByte(): Byte

    fun toDouble(): Double

    fun toFloat(): Float

    fun toInt(): Int

    fun toLong(): Long

    fun toShort(): Short
}