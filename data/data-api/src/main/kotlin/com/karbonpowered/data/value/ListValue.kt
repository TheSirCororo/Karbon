package com.karbonpowered.data.value

import com.karbonpowered.data.Key

interface ListValue<E> : CollectionValue<E, List<E>> {
    override val key: Key<out ListValue<E>>

    operator fun get(index: Int): E

    fun indexOf(element: E): Int

    override fun asMutable(): Mutable<E>

    override fun asImmutable(): Immutable<E>

    interface Mutable<E> : ListValue<E>, CollectionValue.Mutable<E, List<E>, Mutable<E>, Immutable<E>> {
        fun add(index: Int, value: E): Mutable<E>

        fun add(index: Int, values: Iterable<E>): Mutable<E>

        fun remove(index: Int): Mutable<E>

        operator fun set(index: Int, element: E): Mutable<E>

        override fun asMutable(): Mutable<E> = this

        override fun asImmutable(): Immutable<E>
    }

    interface Immutable<E> : ListValue<E>, CollectionValue.Immutable<E, List<E>, Immutable<E>, Mutable<E>> {
        fun with(index: Int, value: E): Immutable<E>

        fun with(index: Int, values: Iterable<E>): Immutable<E>

        fun without(index: Int): Immutable<E>

        operator fun set(index: Int, element: E): Immutable<E>

        override fun asMutable(): Mutable<E>

        override fun asImmutable(): Immutable<E> = this
    }

    companion object {
        @JvmStatic
        fun <V : ListValue<E>, E> mutableOf(key: Key<V>, element: List<E>): Mutable<E> = Value.mutableOf(key, element)

        @JvmStatic
        fun <V : ListValue<E>, E> immutableOf(key: Key<V>, element: List<E>): Immutable<E> = Value.immutableOf(key, element)
    }
}