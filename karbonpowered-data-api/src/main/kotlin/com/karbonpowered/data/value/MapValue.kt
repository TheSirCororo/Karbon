package com.karbonpowered.data.value

import com.karbonpowered.data.Key
import java.util.function.Function
import java.util.function.Predicate

interface MapValue<K, V> : Value<Map<K, V>> {
    override val key: Key<out MapValue<K, V>>

    val size: Int
    val keys: Set<K>
    val values: Collection<V>
    val entries: Set<Map.Entry<K, V>>

    fun isEmpty(): Boolean

    fun containsKey(key: K): Boolean

    fun containsValue(value: V): Boolean

    override fun asMutable(): Mutable<K, V>

    override fun asMutableCopy(): Mutable<K, V>

    override fun asImmutable(): Immutable<K, V>

    interface Mutable<K, V> : MapValue<K, V>, Value.Mutable<Map<K, V>> {
        operator fun set(key: K, value: V): Mutable<K, V>

        fun putAll(map: Map<K, V>): Mutable<K, V>

        fun remove(key: K): Mutable<K, V>

        fun removeAll(keys: Iterable<K>): Mutable<K, V>

        fun removeAll(predicate: Predicate<Map.Entry<K, V>>)

        override fun set(value: Map<K, V>): Mutable<K, V>

        override fun transform(function: Function<Map<K, V>, Map<K, V>>): Mutable<K, V>

        override fun copy(): Mutable<K, V>

        override fun asMutable(): Mutable<K, V> = this

        override fun asMutableCopy(): Mutable<K, V> = copy()

        override fun asImmutable(): Immutable<K, V>
    }

    interface Immutable<K, V> : MapValue<K, V>, Value.Immutable<Map<K, V>> {
        fun with(key: K, value: V): Immutable<K, V>

        fun withAll(map: Map<K, V>): Immutable<K, V>

        fun without(key: K): Immutable<K, V>

        fun withoutAll(keys: Iterable<K>): Immutable<K, V>

        fun withoutAll(predicate: Predicate<Map.Entry<K, V>>): Immutable<K, V>

        override fun with(value: Map<K, V>): Immutable<K, V>

        override fun transform(function: Function<Map<K, V>, Map<K, V>>): Immutable<K, V>

        override fun asMutable(): Mutable<K, V>

        override fun asMutableCopy(): Mutable<K, V> = asMutable()

        override fun asImmutable(): Immutable<K, V> = this
    }

    companion object {
        @JvmStatic
        fun <V : MapValue<K, E>, K, E> mutableOf(key: Key<V>, element: Map<K, E>): Mutable<K, E> =
            Value.mutableOf(key, element)

        @JvmStatic
        fun <V : MapValue<K, E>, K, E> immutableOf(key: Key<V>, element: Map<K, E>): Immutable<K, E> =
            Value.immutableOf(key, element)
    }
}