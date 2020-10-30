package com.karbonpowered.nbt

import com.karbonpowered.catalog.NamespacedKey

interface CompoundTagSetter<R> {
    fun put(key: String, tag: BinaryTag): R

    fun putBoolean(key: String, value: Boolean): R = put(key, ByteBinaryTag.of(value))
    fun putByte(key: String, value: Byte): R = put(key, ByteBinaryTag.of(value))
    fun putShort(key: String, value: Short): R = put(key, ShortBinaryTag.of(value))
    fun putInt(key: String, value: Int): R = put(key, IntBinaryTag.of(value))
    fun putLong(key: String, value: Long): R = put(key, LongBinaryTag.of(value))
    fun putFloat(key: String, value: Float): R = put(key, FloatBinaryTag.of(value))
    fun putDouble(key: String, value: Double): R = put(key, DoubleBinaryTag.of(value))

    fun putByteArray(key: String, value: ByteArray): R = put(key, ByteArrayBinaryTag.of(*value))
    fun putByteArray(key: String, value: Iterable<Byte>): R = put(key, ByteArrayBinaryTag.of(value))
    fun putByteArray(key: String, value: Iterator<Byte>): R = put(key, ByteArrayBinaryTag.of(value))
    fun putByteArray(key: String, value: Collection<Byte>): R = put(key, ByteArrayBinaryTag.of(value))

    fun putString(key: String, value: String): R = put(key, StringBinaryTag.Companion.of(value))
    fun putString(key: String, value: NamespacedKey): R = put(key, StringBinaryTag.Companion.of(value.formatted()))

    fun putIntArray(key: String, value: IntArray): R = put(key, IntArrayBinaryTag.of(*value))
    fun putIntArray(key: String, value: Iterable<Int>): R = put(key, IntArrayBinaryTag.of(value))
    fun putIntArray(key: String, value: Iterator<Int>): R = put(key, IntArrayBinaryTag.of(value))
    fun putIntArray(key: String, value: Collection<Int>): R = put(key, IntArrayBinaryTag.of(value))

    fun putLongArray(key: String, value: LongArray): R = put(key, LongArrayBinaryTag.of(*value))
    fun putLongArray(key: String, value: Iterable<Long>): R = put(key, LongArrayBinaryTag.of(value))
    fun putLongArray(key: String, value: Iterator<Long>): R = put(key, LongArrayBinaryTag.of(value))
    fun putLongArray(key: String, value: Collection<Long>): R = put(key, LongArrayBinaryTag.of(value))
}

operator fun <R> CompoundTagSetter<R>.set(key: String, tag: BinaryTag): R = put(key, tag)
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Boolean): R = putBoolean(key, value)
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Byte): R = putByte(key, value)
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Short): R = putShort(key, value)
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Int): R = putInt(key, value)
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Long): R = putLong(key, value)
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Float): R = putFloat(key, value)
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Double): R = putDouble(key, value)

operator fun <R> CompoundTagSetter<R>.set(key: String, value: ByteArray): R = putByteArray(key, value)

@JvmName("setByteArray")
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Iterable<Byte>): R = putByteArray(key, value)

@JvmName("setByteArray")
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Iterator<Byte>): R = putByteArray(key, value)

@JvmName("setByteArray")
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Collection<Byte>): R = putByteArray(key, value)


operator fun <R> CompoundTagSetter<R>.set(key: String, value: String): R = putString(key, value)
operator fun <R> CompoundTagSetter<R>.set(key: String, value: NamespacedKey): R = putString(key, value.formatted())

operator fun <R> CompoundTagSetter<R>.set(key: String, value: IntArray): R = putIntArray(key, value)

@JvmName("setIntArray")
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Iterable<Int>): R = putIntArray(key, value)

@JvmName("setIntArray")
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Iterator<Int>): R = putIntArray(key, value)

@JvmName("setIntArray")
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Collection<Int>): R = putIntArray(key, value)


operator fun <R> CompoundTagSetter<R>.set(key: String, value: LongArray): R = putLongArray(key, value)

@JvmName("setLongArray")
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Iterable<Long>): R = putLongArray(key, value)

@JvmName("setLongArray")
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Iterator<Long>): R = putLongArray(key, value)

@JvmName("setLongArray")
operator fun <R> CompoundTagSetter<R>.set(key: String, value: Collection<Long>): R = putLongArray(key, value)


