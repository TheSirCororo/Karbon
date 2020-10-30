package com.karbonpowered.commons.store

import java.util.*
import java.util.function.Function
import java.util.function.IntFunction
import kotlin.collections.HashMap

class MemoryStore<K, V>(
    private val keyToValue: MutableMap<K, V>,
    private val valueToKey: MutableMap<V, K>
) : StoredMap<K, V> {
    override fun save(): Boolean = true

    override fun load(): Boolean = true

    override val keys: Collection<K> get() = keyToValue.keys

    override val values: Collection<V> get() = valueToKey.keys

    override val entrySet: Set<Map.Entry<K, V>> get() = keyToValue.entries

    override val size: Int get() = keyToValue.size

    override fun clear(): Boolean {
        keyToValue.clear()
        valueToKey.clear()
        return true
    }

    override fun get(key: K): V? = synchronized(keyToValue) {
        keyToValue[key]
    }

    override fun getKey(value: V): K = synchronized(valueToKey) {
        valueToKey[value]!!
    }

    override fun remove(key: K): V? {
        val value = keyToValue.remove(key)
        if (value != null) {
            valueToKey.remove(value)
        }
        return value
    }

    override fun set(key: K, value: V): V? {
        val oldValue = keyToValue.put(key, value)
        if (oldValue != null) {
            valueToKey.remove(oldValue)
        }
        valueToKey[value] = key
        return oldValue
    }

    override fun setIfAbsent(key: K, value: V): Boolean {
        if (keyToValue[key] != null) return false
        if (valueToKey[value] != null) return false
        set(key, value)
        return true
    }

    companion object {
        fun <K, V : Enum<V>> of(type: Class<V>, keyFunction: Function<in V, out K>): StoredMap<K, V> =
            of(type, keyFunction, *type.enumConstants)

        fun <K, V : Enum<V>> of(type: Class<V>, keyFunction: Function<in V, out K>, vararg values: V): StoredMap<K, V> =
            of(values.toList(), { EnumMap(type) }, keyFunction)

        fun <K, V> of(keyFunction: Function<in V, out K>, vararg values: V): StoredMap<K, V> =
            of(values.toList(), { HashMap(it) }, keyFunction)

        fun <K, V> of(keyFunction: Function<in V, out K>, constants: List<V>): StoredMap<K, V> =
            of(constants, { HashMap(it) }, keyFunction)

        private fun <K, V> of(
            values: Array<V>,
            valueToKeyFactory: IntFunction<MutableMap<V, K>>,
            keyFunction: Function<in V, out K>
        ): StoredMap<K, V> =
            of(values.toList(), valueToKeyFactory, keyFunction)

        private fun <K, V> of(
            values: Collection<V>,
            valueToKeyFactory: IntFunction<MutableMap<V, K>>,
            keyFunction: Function<in V, out K>
        ): StoredMap<K, V> {
            val length = values.size
            val keyToValue = HashMap<K, V>(length)
            val valueToKey = valueToKeyFactory.apply(length)
            values.forEach { value ->
                val key = keyFunction.apply(value)
                check(
                    keyToValue.putIfAbsent(
                        key,
                        value
                    ) == null
                ) { "Key $key already mapped to value ${keyToValue[key]}" }
                check(
                    valueToKey.putIfAbsent(
                        value,
                        key
                    ) == null
                ) { "Value $value already mapped to key ${valueToKey[value]}" }
            }
            return MemoryStore(Collections.unmodifiableMap(keyToValue), Collections.unmodifiableMap(valueToKey))
        }
    }
}