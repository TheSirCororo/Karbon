package com.karbonpowered.nbt

import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList

class KarbonListBinaryTag(
    override val listType: BinaryTagType<out BinaryTag>,
    val tags: MutableList<out BinaryTag>
) : ListBinaryTag {
    val hashCode by lazy { tags.hashCode() }
    override val size: Int
        get() = tags.size

    override fun get(index: Int): BinaryTag = tags[index]

    override fun set(index: Int, tag: BinaryTag): ListBinaryTag = edit(tag.type) {
        it[index] = tag
    }

    override fun remove(index: Int): ListBinaryTag = edit {
        it.removeAt(index)
    }

    override fun add(tag: BinaryTag): ListBinaryTag = edit(tag.type) {
        noAddEnd(tag)
        mustBeSameType(tag, type)
        it.add(tag)
    }

    fun edit(
        maybeType: BinaryTagType<out BinaryTag>? = null,
        consumer: Consumer<MutableList<BinaryTag>>
    ): KarbonListBinaryTag {
        val tags = ArrayList(tags)
        consumer.accept(tags)
        var type = type
        if (maybeType != null && type == BinaryTagTypes.END) {
            type = maybeType
        }
        return KarbonListBinaryTag(type, tags)
    }

    override fun iterator(): Iterator<BinaryTag> {
        val iterator = tags.iterator()
        return object : Iterator<BinaryTag> {
            override fun hasNext(): Boolean = iterator.hasNext()

            override fun next(): BinaryTag = iterator.next()

            override fun forEachRemaining(action: Consumer<in BinaryTag>) = iterator.forEachRemaining(action)
        }
    }

    override fun forEach(action: Consumer<in BinaryTag>?) = tags.forEach(action)

    override fun spliterator(): Spliterator<BinaryTag> =
        Spliterators.spliterator(tags, Spliterator.ORDERED or Spliterator.IMMUTABLE)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonListBinaryTag

        if (listType != other.listType) return false
        if (tags != other.tags) return false

        return true
    }

    override fun hashCode(): Int = hashCode

    class Builder<T : BinaryTag>(
        var type: BinaryTagType<out BinaryTag> = BinaryTagTypes.END
    ) : ListBinaryTag.Builder<T> {
        var tags: MutableList<BinaryTag>? = null

        override fun reset(): ListBinaryTag.Builder<T> = apply {
            tags?.clear()
        }

        override fun build(): ListBinaryTag {
            val tags = tags
            return KarbonListBinaryTag(type, tags?.toMutableList() ?: ArrayList<ListBinaryTag>())
        }

        override fun add(tag: T): ListBinaryTag.Builder<T> = apply {
            noAddEnd(tag)
            if (type == BinaryTagTypes.END) {
                type = tag.type
            }
            mustBeSameType(tag, type)
            if (tags == null) {
                tags = ArrayList()
            }
            tags?.add(tag)
        }
    }

    class Factory : ListBinaryTag.Factory {
        override fun <T : BinaryTag> builder(type: BinaryTagType<T>): ListBinaryTag.Builder<T> = Builder(type)

        override fun of(vararg tags: BinaryTag): ListBinaryTag = Builder<BinaryTag>().apply {
            tags.forEach { add(it) }
        }.build()

        override fun of(tags: Iterable<BinaryTag>): ListBinaryTag = Builder<BinaryTag>().apply {
            tags.forEach { add(it) }
        }.build()

        override fun of(tags: Iterator<BinaryTag>): ListBinaryTag = Builder<BinaryTag>().apply {
            tags.forEach { add(it) }
        }.build()

        override fun of(tags: Collection<BinaryTag>): ListBinaryTag = Builder<BinaryTag>().apply {
            tags.forEach { add(it) }
        }.build()

        override fun empty(): ListBinaryTag = Builder<BinaryTag>().build()
    }

    companion object {
        fun noAddEnd(tag: BinaryTag) {
            if (tag.type == BinaryTagTypes.END) {
                throw IllegalArgumentException("Cannot add ${BinaryTagTypes.END} to ${BinaryTagTypes.LIST}")
            }
        }

        fun mustBeSameType(tag: BinaryTag, type: BinaryTagType<out BinaryTag>) {
            if (tag.type != type) {
                throw IllegalArgumentException("Trying to add tag of type ${tag.type} to list of $type")
            }
        }
    }
}