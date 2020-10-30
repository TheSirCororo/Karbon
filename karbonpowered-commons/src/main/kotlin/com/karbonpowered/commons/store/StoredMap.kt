package com.karbonpowered.commons.store

interface StoredMap<K, V> {
    fun save(): Boolean

    fun load(): Boolean

    val keys: Collection<K>

    val values: Collection<V>

    val entrySet: Set<Map.Entry<K, V>>

    val size: Int

    fun clear(): Boolean

    operator fun get(key: K): V?

    operator fun get(key: K, default: V): V = get(key) ?: default

    fun getKey(value: V): K

    fun remove(key: K): V?

    operator fun set(key: K, value: V): V?

    fun setIfAbsent(key: K, value: V): Boolean
}