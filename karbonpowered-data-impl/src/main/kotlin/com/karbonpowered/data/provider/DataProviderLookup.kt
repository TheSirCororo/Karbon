package com.karbonpowered.data.provider

import com.karbonpowered.data.DataProvider
import com.karbonpowered.data.Key
import com.karbonpowered.data.key.KarbonKey
import com.karbonpowered.data.value.Value

class DataProviderLookup(
    val providerMap: Map<Key<*>, DataProvider<*, *>>
) {
    val allProviders: Collection<DataProvider<*, *>>
        get() = providerMap.values

    @Suppress("UNCHECKED_CAST")
    fun <V : Value<E>, E> getProvider(key: Key<V>): DataProvider<V, E> {
        return (providerMap[key] ?: (key as KarbonKey<V, E>).emptyDataProvider) as DataProvider<V, E>
    }
}