package com.karbonpowered.data.holder

import com.karbonpowered.data.DataHolder
import com.karbonpowered.data.Key
import com.karbonpowered.data.transaction.DataTransactionResult
import com.karbonpowered.data.value.*
import javax.xml.crypto.Data

@Suppress("UNCHECKED_CAST")
interface KarbonMutableDataHolder : KarbonDataHolder, DataHolder.Mutable {
    override fun delegateDataHolder(): DataHolder.Mutable = this

    override fun <E> tryOffer(key: Key<Value<E>>, value: E): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun <E, V : Value<E>> offer(key: Key<V>, value: E): DataTransactionResult =
        getProviderFor(key).offer(delegateDataHolder(), value)


    override fun offer(value: Value<*>): DataTransactionResult =
        getProviderFor(value.key as Key<Value<Any>>).offerValue(delegateDataHolder(), value as Value<Any>)

    override fun <E> offerSingle(key: Key<CollectionValue<E, *>>, element: E): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun <K, V> offerSingle(key: Key<MapValue<K, V>>, mapKey: K, mapValue: V): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun offerAll(value: CollectionValue<*, *>): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun <E> offerAll(key: Key<CollectionValue<E, *>>, elements: Collection<E>): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun offerAll(value: MapValue<*, *>): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun <K, V> offerAll(key: Key<MapValue<K, V>>, map: Map<K, V>): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun <E> removeSingle(key: Key<CollectionValue<E, *>>, element: E): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun removeAll(value: CollectionValue<*, *>): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun removeAll(value: MapValue<*, *>): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun <K, V> removeAll(key: Key<MapValue<K, V>>, map: Map<K, V>): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun <K> removeKey(key: Key<MapValue<K, *>>, mapKey: K): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun remove(key: Key<*>): DataTransactionResult {
        TODO("Not yet implemented")
    }

    override fun copyFrom(that: ValueContainer<Any?, Any?>, function: MergeFunction<Value<Any>, Any>) {
        TODO("Not yet implemented")
    }

    override fun undo(result: Data): DataTransactionResult {
        TODO("Not yet implemented")
    }
}