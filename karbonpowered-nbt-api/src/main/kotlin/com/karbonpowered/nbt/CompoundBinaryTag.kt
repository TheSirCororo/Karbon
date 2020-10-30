@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.nbt

import com.karbonpowered.commons.builder.ResettableBuilder
import com.karbonpowered.commons.lang.loadService

interface CompoundBinaryTag : BinaryTag, CompoundTagSetter<CompoundBinaryTag>, Iterable<Map.Entry<String, BinaryTag>> {
    override val type: BinaryTagType<out BinaryTag>
        get() = BinaryTagTypes.COMPOUND

    val keys: Set<String>

    operator fun get(key: String): BinaryTag?

    fun contains(key: String, type: BinaryTagType<*>): Boolean

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean

    fun getByte(key: String, defaultValue: Byte = 0): Byte

    fun getShort(key: String, defaultValue: Short = 0): Short

    fun getInt(key: String, defaultValue: Int = 0): Int

    fun getLong(key: String, defaultValue: Long = 0): Long

    fun getFloat(key: String, defaultValue: Float = 0f): Float

    fun getDouble(key: String, defaultValue: Double = 0.0): Double

    fun getByteArray(key: String, defaultValue: ByteArray = ByteArray(0)): ByteArray

    fun getString(key: String, defaultValue: String = ""): String

    fun getList(key: String, defaultValue: ListBinaryTag = ListBinaryTag.empty()): ListBinaryTag

    fun getList(
        key: String,
        expectedType: BinaryTagType<BinaryTag>,
        defaultValue: ListBinaryTag = ListBinaryTag.empty()
    ): ListBinaryTag

    fun getCompound(key: String, defaultValue: CompoundBinaryTag = empty()): CompoundBinaryTag

    fun getIntArray(key: String, defaultValue: IntArray = IntArray(0)): IntArray

    fun getLongArray(key: String, defaultValue: LongArray = LongArray(0)): LongArray

    interface Builder : ResettableBuilder<CompoundBinaryTag, Builder>, CompoundTagSetter<Builder>

    interface Factory {
        fun empty(): CompoundBinaryTag

        fun of(vararg tags: Pair<String, BinaryTag>): CompoundBinaryTag = builder().apply {
            tags.forEach {
                put(it.first, it.second)
            }
        }.build()

        fun of(tags: Map<String, BinaryTag>): CompoundBinaryTag = builder().apply {
            tags.forEach { (key, tag) ->
                put(key, tag)
            }
        }.build()
    }

    companion object {

        @JvmStatic
        fun builder(): Builder = loadService()

        @JvmStatic
        fun of(vararg tags: Pair<String, BinaryTag>): CompoundBinaryTag = loadService<Factory>().of(*tags)

        @JvmStatic
        fun of(tags: Map<String, BinaryTag>): CompoundBinaryTag = loadService<Factory>().of(tags)

        @JvmStatic
        fun empty(): CompoundBinaryTag = loadService<Factory>().empty()
    }
}

fun CompoundBinaryTag(builder: CompoundBinaryTag.Builder.() -> Unit): CompoundBinaryTag =
    CompoundBinaryTag.builder().apply(builder).build()

inline fun CompoundBinaryTag(vararg tags: Pair<String, BinaryTag>): CompoundBinaryTag = CompoundBinaryTag.of(*tags)
inline fun CompoundBinaryTag(tags: Map<String, BinaryTag>): CompoundBinaryTag = CompoundBinaryTag.of(tags)