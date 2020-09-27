package com.karbonpowered.data.provider

import com.google.common.collect.HashMultimap
import com.karbonpowered.data.DataProvider
import com.karbonpowered.data.Key
import com.karbonpowered.data.key.KarbonKey
import com.karbonpowered.data.value.Value
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Predicate
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
object DataProviderRegistry {
    val dataProviders = HashMultimap.create<Key<*>, DataProvider<*, *>>()
    val dataProviderCache = ConcurrentHashMap<LookupKey, DataProvider<*, *>>()
    val dataProviderLookupCache = ConcurrentHashMap<Class<*>, DataProviderLookup>()

    fun register(provider: DataProvider<*, *>) {
        dataProviders.put(provider.key, provider)
        dataProviderCache.clear()
        dataProviderLookupCache.clear()
    }

    @Suppress("UNCHECKED_CAST")
    fun <V : Value<E>, E> buildDelegate(key: Key<V>, predicate: Predicate<DataProvider<V, E>>): DataProvider<V, E> {
        val providers = dataProviders[key] as Collection<DataProvider<V, E>>
        println("providers: $providers")
        return buildDelegateProvider(key, providers.filter {
            val test = predicate.test(it)
            println("$predicate test: $test")
            test
        })
    }

    fun getProviderLookup(dataHolderType: Class<*>): DataProviderLookup = dataProviderLookupCache.getOrPut(dataHolderType) {
        val map = dataProviders.keySet().asSequence()
                .map { getProvider(it as Key<Value<Any>>, dataHolderType) }
                .filter { it !is EmptyDataProvider<*,*> }
                .map { it.key as Key<*> to it as DataProvider<*,*> }
                .toMap()
        DataProviderLookup(map)
    }

    fun <V : Value<E>, E> buildDelegateProvider(key: Key<V>, providers: List<DataProvider<V, E>>): DataProvider<V, E> {
        if (providers.isEmpty()) {
            (key as KarbonKey<V, E>).emptyDataProvider
        }
        if (providers.size == 1) {
            return providers.first()
        }
        return DelegateDataProvider(key, providers)
    }

    fun <V : Value<E>, E> getProvider(key: Key<V>, dataHolderType: KClass<*>): DataProvider<V, E> = getProvider(key, dataHolderType.java)
    fun <V : Value<E>, E> getProvider(key: Key<V>, dataHolderType: Class<*>): DataProvider<V, E> {
        val lookupKey = LookupKey(dataHolderType, key)
        return dataProviderCache.getOrPut(lookupKey) {
            buildDelegate(lookupKey.key as Key<Value<Any>>) { filterHolderType(it, lookupKey.holderType) }
        } as DataProvider<V, E>
    }

    private fun filterHolderType(provider: DataProvider<*, *>, holderType: Class<*>): Boolean {
        if (provider is AbstractDataProvider.KnownHolderType) {
            val foundHolderType = provider.holderType
            return foundHolderType.isAssignableFrom(holderType)
        }
        return true
    }

    fun getAllProviders(dataHolderType: Class<*>): Collection<DataProvider<*,*>> = getProviderLookup(dataHolderType).allProviders

    data class LookupKey(val holderType: Class<*>, val key: Key<*>)
}