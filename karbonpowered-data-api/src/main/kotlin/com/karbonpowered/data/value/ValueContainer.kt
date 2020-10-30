package com.karbonpowered.data.value

import com.karbonpowered.data.Key

interface ValueContainer<T, U> {
    val keys: Set<Key<*>>
    val values: Set<Value.Immutable<*>>

    operator fun <E, V : Value<E>> get(key: Key<V>): E?

    fun <E, V : Value<E>> require(key: Key<V>): E = get(key)
        ?: throw NoSuchElementException("Could not retrieve value for key '$key'")

    fun <E, V : Value<E>> getValue(key: Key<V>): V?

    fun <E, V : Value<E>> requireValue(key: Key<V>): V = getValue(key)
        ?: throw NoSuchElementException("Could not retrieve value for key '$key'")

    fun supports(key: Key<*>): Boolean

    fun supports(value: Value<*>): Boolean = supports(value.key)
}