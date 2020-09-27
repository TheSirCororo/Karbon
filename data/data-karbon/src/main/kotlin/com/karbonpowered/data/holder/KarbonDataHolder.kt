package com.karbonpowered.data.holder

import com.karbonpowered.data.DataHolder
import com.karbonpowered.data.DataProvider
import com.karbonpowered.data.Key
import com.karbonpowered.data.provider.DataProviderRegistry
import com.karbonpowered.data.value.Value

interface KarbonDataHolder : DataHolder {
    fun <V : Value<E>, E> getProviderFor(key: Key<V>): DataProvider<V, E> =
            DataProviderRegistry.getProvider(key, this.javaClass)

    fun delegateDataHolder(): DataHolder = this

    @Suppress("UNCHECKED_CAST")
    override fun supports(key: Key<*>): Boolean = getProviderFor(key as Key<Value<Any>>).isSupported(delegateDataHolder())

    override fun <E, V : Value<E>> get(key: Key<V>): E? = getProviderFor(key)[delegateDataHolder()]

    override fun <E, V : Value<E>> getValue(key: Key<V>): V? = getProviderFor(key).getValue(delegateDataHolder())

    val allProviders: Collection<DataProvider<*,*>>
            get() = DataProviderRegistry.getAllProviders(delegateDataHolder().javaClass)

    override val keys: Set<Key<*>>
        get() = allProviders.asSequence()
                .map { provider -> provider[delegateDataHolder()]?.let { provider.key } }
                .filterNotNull()
                .toSet()

    override val values: Set<Value.Immutable<*>>
        get() = allProviders.asSequence()
                .map { provider -> provider.getValue(delegateDataHolder())?.let { it.asImmutable() } }
                .filterNotNull()
                .toSet()
}