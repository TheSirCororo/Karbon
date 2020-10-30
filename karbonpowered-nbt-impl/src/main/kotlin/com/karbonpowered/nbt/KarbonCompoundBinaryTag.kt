package com.karbonpowered.nbt

import java.util.function.Consumer

class KarbonCompoundBinaryTag(
    val tags: HashMap<String, BinaryTag>
) : CompoundBinaryTag {
    override val keys: Set<String>
        get() = tags.keys

    override fun get(key: String): BinaryTag? = tags[key]

    override fun contains(key: String, type: BinaryTagType<*>): Boolean {
        val tag = tags[key]
        return tag != null && type.test(tag.type)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return if (contains(key, BinaryTagTypes.BYTE)) {
            (tags[key] as? NumberBinaryTag)?.toByte() == 1.toByte()
        } else defaultValue
    }

    override fun getByte(key: String, defaultValue: Byte): Byte =
        (tags[key] as? NumberBinaryTag)?.toByte() ?: defaultValue

    override fun getShort(key: String, defaultValue: Short): Short =
        (tags[key] as? NumberBinaryTag)?.toShort() ?: defaultValue

    override fun getInt(key: String, defaultValue: Int): Int =
        (tags[key] as? NumberBinaryTag)?.toInt() ?: defaultValue

    override fun getLong(key: String, defaultValue: Long): Long =
        (tags[key] as? NumberBinaryTag)?.toLong() ?: defaultValue

    override fun getFloat(key: String, defaultValue: Float): Float =
        (tags[key] as? NumberBinaryTag)?.toFloat() ?: defaultValue

    override fun getDouble(key: String, defaultValue: Double): Double =
        (tags[key] as? NumberBinaryTag)?.toDouble() ?: defaultValue

    override fun getByteArray(key: String, defaultValue: ByteArray): ByteArray =
        (tags[key] as? ByteArrayBinaryTag)?.value ?: defaultValue

    override fun getString(key: String, defaultValue: String): String =
        (tags[key] as? StringBinaryTag)?.value ?: defaultValue

    override fun getList(key: String, defaultValue: ListBinaryTag): ListBinaryTag =
        (tags[key] as? ListBinaryTag) ?: defaultValue

    override fun getList(
        key: String,
        expectedType: BinaryTagType<BinaryTag>,
        defaultValue: ListBinaryTag
    ): ListBinaryTag =
        (tags[key] as? ListBinaryTag)?.let {
            if (expectedType.test(it.listType)) it else defaultValue
        } ?: defaultValue

    override fun getCompound(key: String, defaultValue: CompoundBinaryTag): CompoundBinaryTag =
        (tags[key] as? CompoundBinaryTag) ?: defaultValue

    override fun getIntArray(key: String, defaultValue: IntArray): IntArray =
        (tags[key] as? IntArrayBinaryTag)?.value ?: defaultValue

    override fun getLongArray(key: String, defaultValue: LongArray): LongArray =
        (tags[key] as? LongArrayBinaryTag)?.value ?: defaultValue

    override fun put(key: String, tag: BinaryTag): CompoundBinaryTag = edit { it[key] = tag }

    fun edit(consumer: Consumer<MutableMap<String, BinaryTag>>): KarbonCompoundBinaryTag {
        val tags = HashMap<String, BinaryTag>(tags)
        consumer.accept(tags)
        return KarbonCompoundBinaryTag(tags)
    }

    override fun iterator(): Iterator<Map.Entry<String, BinaryTag>> = tags.entries.iterator()

    class Factory : CompoundBinaryTag.Factory {
        override fun empty(): CompoundBinaryTag = EmptyCompoundBinaryTag
    }

    private object EmptyCompoundBinaryTag : CompoundBinaryTag {
        override val keys: Set<String> = emptySet()

        override fun get(key: String): BinaryTag? = null

        override fun contains(key: String, type: BinaryTagType<*>): Boolean = false

        override fun getBoolean(key: String, defaultValue: Boolean): Boolean = defaultValue

        override fun getByte(key: String, defaultValue: Byte): Byte = defaultValue

        override fun getShort(key: String, defaultValue: Short): Short = defaultValue

        override fun getInt(key: String, defaultValue: Int): Int = defaultValue

        override fun getLong(key: String, defaultValue: Long): Long = defaultValue

        override fun getFloat(key: String, defaultValue: Float): Float = defaultValue

        override fun getDouble(key: String, defaultValue: Double): Double = defaultValue

        override fun getByteArray(key: String, defaultValue: ByteArray): ByteArray = defaultValue

        override fun getString(key: String, defaultValue: String): String = defaultValue

        override fun getList(key: String, defaultValue: ListBinaryTag): ListBinaryTag = defaultValue

        override fun getList(
            key: String,
            expectedType: BinaryTagType<BinaryTag>,
            defaultValue: ListBinaryTag
        ): ListBinaryTag = defaultValue

        override fun getCompound(key: String, defaultValue: CompoundBinaryTag): CompoundBinaryTag = defaultValue

        override fun getIntArray(key: String, defaultValue: IntArray): IntArray = defaultValue

        override fun getLongArray(key: String, defaultValue: LongArray): LongArray = defaultValue

        override fun put(key: String, tag: BinaryTag): CompoundBinaryTag = this

        override fun iterator(): Iterator<Map.Entry<String, BinaryTag>> = EmptyIterator

        private object EmptyIterator : Iterator<Map.Entry<String, BinaryTag>> {
            override fun hasNext(): Boolean = false

            override fun next(): Map.Entry<String, BinaryTag> = throw NoSuchElementException()
        }
    }
}