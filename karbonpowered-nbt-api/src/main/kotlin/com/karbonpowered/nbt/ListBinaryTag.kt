@file:Suppress("NOTHING_TO_INLINE", "FunctionName")

package com.karbonpowered.nbt

import com.karbonpowered.commons.builder.ResettableBuilder
import com.karbonpowered.commons.lang.loadService

interface ListBinaryTag : ListTagSetter<ListBinaryTag, BinaryTag>, BinaryTag, Iterable<BinaryTag> {
    override val type: BinaryTagType<out BinaryTag>
        get() = BinaryTagTypes.LIST

    val listType: BinaryTagType<out BinaryTag>

    val size: Int

    operator fun get(index: Int): BinaryTag

    operator fun set(index: Int, tag: BinaryTag): ListBinaryTag

    fun remove(index: Int): ListBinaryTag

    fun getByte(index: Int, defaultValue: Byte = 0): Byte {
        val tag = get(index)
        return (tag as? NumberBinaryTag)?.toByte() ?: defaultValue
    }

    fun getShort(index: Int, defaultValue: Short = 0): Short {
        val tag = get(index)
        return (tag as? NumberBinaryTag)?.toShort() ?: defaultValue
    }

    fun getInt(index: Int, defaultValue: Int = 0): Int {
        val tag = get(index)
        return (tag as? NumberBinaryTag)?.toInt() ?: defaultValue
    }

    fun getLong(index: Int, defaultValue: Long = 0): Long {
        val tag = get(index)
        return (tag as? NumberBinaryTag)?.toLong() ?: defaultValue
    }

    fun getFloat(index: Int, defaultValue: Float = 0f): Float {
        val tag = get(index)
        return (tag as? NumberBinaryTag)?.toFloat() ?: defaultValue
    }

    fun getDouble(index: Int, defaultValue: Double = 0.0): Double {
        val tag = get(index)
        return (tag as? NumberBinaryTag)?.toDouble() ?: defaultValue
    }

    fun getByteArray(index: Int, defaultValue: ByteArray = ByteArray(0)): ByteArray {
        val tag = get(index)
        return (tag as? ByteArrayBinaryTag)?.value ?: defaultValue
    }

    fun geString(index: Int, defaultValue: String = ""): String {
        val tag = get(index)
        return (tag as? StringBinaryTag)?.value ?: defaultValue
    }

    fun getCompound(index: Int, defaultValue: CompoundBinaryTag = CompoundBinaryTag.empty()): CompoundBinaryTag {
        val tag = get(index)
        return tag as? CompoundBinaryTag ?: defaultValue
    }

    fun getIntArray(index: Int, defaultValue: IntArray = IntArray(0)): IntArray {
        val tag = get(index)
        return (tag as? IntArrayBinaryTag)?.value ?: defaultValue
    }

    fun getLongArray(index: Int, defaultValue: LongArray = LongArray(0)): LongArray {
        val tag = get(index)
        return (tag as? LongArrayBinaryTag)?.value ?: defaultValue
    }

    interface Builder<T : BinaryTag> : ResettableBuilder<ListBinaryTag, Builder<T>>, ListTagSetter<Builder<T>, T> {
        operator fun T.unaryPlus() = add(this)
    }

    interface Factory {
        fun <T : BinaryTag> builder(type: BinaryTagType<T>): Builder<T>

        fun of(vararg tags: BinaryTag): ListBinaryTag

        fun of(tags: Iterable<BinaryTag>): ListBinaryTag

        fun of(tags: Iterator<BinaryTag>): ListBinaryTag

        fun of(tags: Collection<BinaryTag>): ListBinaryTag

        fun empty(): ListBinaryTag
    }

    companion object {
        @JvmStatic
        fun builder(): Builder<BinaryTag> = loadService()

        @JvmStatic
        fun <T : BinaryTag> builder(type: BinaryTagType<T>): Builder<T> = loadService<Factory>().builder(type)

        @JvmStatic
        fun of(vararg tags: BinaryTag): ListBinaryTag = loadService<Factory>().of(* tags)

        @JvmStatic
        fun of(tags: Iterable<BinaryTag>): ListBinaryTag = loadService<Factory>().of(tags)

        @JvmStatic
        fun of(tags: Iterator<BinaryTag>): ListBinaryTag = loadService<Factory>().of(tags)

        @JvmStatic
        fun of(tags: Collection<BinaryTag>): ListBinaryTag = loadService<Factory>().of(tags)

        @JvmStatic
        fun empty(): ListBinaryTag = loadService<Factory>().empty()
    }
}

fun ListBinaryTag(builder: ListBinaryTag.Builder<BinaryTag>.() -> Unit): ListBinaryTag =
    ListBinaryTag.builder().apply(builder).build()

inline fun ListBinaryTag(vararg tags: BinaryTag): ListBinaryTag = ListBinaryTag.of(*tags)
inline fun ListBinaryTag(tags: Iterable<BinaryTag>): ListBinaryTag = ListBinaryTag.of(tags)
inline fun ListBinaryTag(tags: Iterator<BinaryTag>): ListBinaryTag = ListBinaryTag.of(tags)
inline fun ListBinaryTag(tags: Collection<BinaryTag>): ListBinaryTag = ListBinaryTag.of(tags)

inline fun Array<out BinaryTag>.toBinaryTag(): ListBinaryTag = ListBinaryTag.of(*this)
inline fun Iterable<BinaryTag>.toBinaryTag(): ListBinaryTag = ListBinaryTag.of(this)
inline fun Iterator<BinaryTag>.toBinaryTag(): ListBinaryTag = ListBinaryTag.of(this)
inline fun Collection<BinaryTag>.toBinaryTag(): ListBinaryTag = ListBinaryTag.of(this)