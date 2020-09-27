package com.karbonpowered.data.value

import com.karbonpowered.data.Key

interface SetValue<E> : CollectionValue<E, Set<E>> {
    override val key: Key<SetValue<E>>

    override fun asMutable(): Mutable<E>

    override fun asMutableCopy(): Mutable<E>

    override fun asImmutable(): Immutable<E>

    interface Mutable<E> : SetValue<E>, CollectionValue.Mutable<E, Set<E>, Mutable<E>, Immutable<E>> {
        override fun asMutable(): Mutable<E> = this

        override fun asMutableCopy(): Mutable<E> = copy()

        override fun asImmutable(): Immutable<E>
    }

    interface Immutable<E> : SetValue<E>, CollectionValue.Immutable<E, Set<E>, Immutable<E>, Mutable<E>> {
        override fun asMutable(): Mutable<E>

        override fun asMutableCopy(): Mutable<E> = asMutable()

        override fun asImmutable(): Immutable<E> = this
    }

    companion object {
        @JvmStatic
        fun <V : SetValue<E>, E> mutableOf(key: Key<V>, element: Set<E>): Mutable<E> = Value.mutableOf(key, element)

        @JvmStatic
        fun <V : SetValue<E>, E> immutableOf(key: Key<V>, element: Set<E>): Immutable<E> = Value.immutableOf(key, element)
    }
}