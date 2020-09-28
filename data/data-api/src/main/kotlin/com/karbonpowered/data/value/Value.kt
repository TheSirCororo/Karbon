package com.karbonpowered.data.value

import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.data.Key
import java.util.function.Function

interface Value<E> {
    fun get(): E

    val key: Key<out Value<E>>

    fun asMutable(): Mutable<E>

    fun asMutableCopy(): Mutable<E>

    fun asImmutable(): Immutable<E>

    interface Mutable<E> : Value<E> {
        fun set(value: E): Mutable<E>

        fun transform(function: Function<E, E>): Mutable<E>

        override fun asImmutable(): Immutable<E>

        override fun asMutable(): Mutable<E> = this

        override fun asMutableCopy(): Mutable<E> = copy()

        fun copy(): Mutable<E>
    }

    interface Immutable<E> : Value<E> {
        fun with(value: E): Immutable<E>

        fun transform(function: Function<E, E>): Immutable<E>

        override fun asMutable(): Mutable<E>

        override fun asMutableCopy(): Mutable<E> = asMutable()

        override fun asImmutable(): Immutable<E> = this
    }

    interface Factory {
        fun <V : Value<E>, E> mutableOf(key: Key<V>, element: E): V

        fun <V : Value<E>, E> immutableOf(key: Key<V>, element: E): V
    }

    companion object {
        @JvmStatic
        fun <V : Value<E>, E> mutableOf(key: Key<V>, element: E): Mutable<E> =
                genericMutableOf(key, element).asMutable()

        @JvmStatic
        fun <V : Value<E>, E> immutableOf(key: Key<V>, element: E): Immutable<E> =
                genericImmutableOf(key, element).asImmutable()

        @JvmStatic
        fun <V : ListValue<E>, E> mutableOf(key: Key<V>, element: List<E>): ListValue.Mutable<E> =
                genericMutableOf(key, element).asMutable()

        @JvmStatic
        fun <V : ListValue<E>, E> immutableOf(key: Key<V>, element: List<E>): ListValue.Immutable<E> =
                genericImmutableOf(key, element).asImmutable()

        @JvmStatic
        fun <V : SetValue<E>, E> mutableOf(key: Key<V>, element: Set<E>): SetValue.Mutable<E> =
                genericMutableOf(key, element).asMutable()

        @JvmStatic
        fun <V : SetValue<E>, E> immutableOf(key: Key<V>, element: Set<E>): SetValue.Immutable<E> =
                genericImmutableOf(key, element).asImmutable()

        @JvmStatic
        fun <V : MapValue<K, E>, K, E> mutableOf(key: Key<V>, element: Map<K, E>): MapValue.Mutable<K, E> =
                genericMutableOf(key, element).asMutable()

        @JvmStatic
        fun <V : MapValue<K, E>, K, E> immutableOf(key: Key<V>, element: Map<K, E>): MapValue.Immutable<K, E> =
                genericImmutableOf(key, element).asImmutable()

        @JvmStatic
        fun <V : Value<E>, E> genericMutableOf(key: Key<V>, element: E): V =
                loadService<Factory>().mutableOf(key, element)

        @JvmStatic
        fun <V : Value<E>, E> genericImmutableOf(key: Key<V>, element: E): V =
                loadService<Factory>().immutableOf(key, element)
    }
}