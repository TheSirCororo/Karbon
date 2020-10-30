@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.commons.memory

inline fun Float.reinterpretAsInt() = this.toBits()

inline fun Double.reinterpretAsLong() = this.toBits()

inline fun Int.reinterpretAsFloat() = Float.fromBits(this)

inline fun Long.reinterpretAsDouble() = Double.fromBits(this)

fun Int.mask(): Int = (1 shl this) - 1

fun Long.mask(): Long = (1L shl this.toInt()) - 1L

fun Int.insert(value: Int, offset: Int, count: Int): Int {
    val mask = count.mask()
    val clearValue = this and (mask shl offset).inv()
    return clearValue or ((value and mask) shl offset)
}

fun Int.insert(value: Boolean, offset: Int): Int = this.insert(if (value) 1 else 0, offset, 1)

infix fun Int.hasFlags(bits: Int): Boolean = (this and bits) == bits

fun bit(bit: Int) = 1 shl bit

fun Int.unsetBits(bits: Int) = this and bits.inv()

fun Int.setBits(bits: Int) = this or bits