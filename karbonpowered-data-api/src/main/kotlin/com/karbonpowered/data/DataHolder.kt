package com.karbonpowered.data

import com.karbonpowered.data.transaction.DataTransactionResult
import com.karbonpowered.data.value.*
import java.util.function.Function
import javax.xml.crypto.Data

interface DataHolder : ValueContainer<Any?, Any?> {
    interface Mutable : DataHolder {
        fun <E> transform(key: Key<Value<E>>, function: Function<E, E>): DataTransactionResult {
            return if (supports(key)) {
                get(key)
                    ?.let { function.apply(it) }
                    ?.let { offer(key, it) }
                    ?: DataTransactionResult.failNoData()
            } else DataTransactionResult.failNoData()
        }

        operator fun <E, V : Value<E>> set(key: Key<V>, value: E): DataTransactionResult = offer(key, value)

        fun <E, V : Value<E>> offer(key: Key<V>, value: E): DataTransactionResult

        fun offer(value: Value<*>): DataTransactionResult

        fun <E> offerSingle(key: Key<CollectionValue<E, *>>, element: E): DataTransactionResult

        fun <K, V> offerSingle(key: Key<MapValue<K, V>>, mapKey: K, mapValue: V): DataTransactionResult

        fun <K, V> offerAll(key: Key<MapValue<K, V>>, map: Map<K, V>): DataTransactionResult

        fun offerAll(value: MapValue<*, *>): DataTransactionResult

        fun offerAll(value: CollectionValue<*, *>): DataTransactionResult

        fun <E> offerAll(key: Key<CollectionValue<E, *>>, elements: Collection<E>): DataTransactionResult

        fun <E> removeSingle(key: Key<CollectionValue<E, *>>, element: E): DataTransactionResult

        fun <K> removeKey(key: Key<MapValue<K, *>>, mapKey: K): DataTransactionResult

        fun removeAll(value: CollectionValue<*, *>): DataTransactionResult

        fun removeAll(value: MapValue<*, *>): DataTransactionResult

        fun <K, V> removeAll(key: Key<MapValue<K, V>>, map: Map<K, V>): DataTransactionResult

        fun <E> tryOffer(key: Key<Value<E>>, value: E): DataTransactionResult

        @Suppress("UNCHECKED_CAST")
        fun <E> tryOffer(value: Value<E>): DataTransactionResult {
            val result = offer(value.key as Key<Value<E>>, value.get())
            if (!result.isSuccessful()) {
                throw IllegalArgumentException("Failed offer transaction!")
            }
            return result
        }

        fun remove(value: Value<*>): DataTransactionResult = remove(value.key)

        fun remove(key: Key<*>): DataTransactionResult

        fun undo(result: Data): DataTransactionResult

        fun copyFrom(
            that: ValueContainer<Any?, Any?>,
            function: MergeFunction<Value<Any>, Any> = MergeFunction.replacementPreferred()
        )
    }

    interface Immutable<I : Immutable<I>> : DataHolder {
        fun <E> transform(key: Key<Value<E>>, function: Function<E, E>): I?

        fun <E> with(key: Key<Value<E>>, value: E): I?

        fun with(value: Value<*>): I?

        fun without(value: Value<*>): I? = without(value.key)

        fun without(value: Key<*>): I?

        fun mergeWith(that: I, function: MergeFunction<Value<Any>, Any> = MergeFunction.replacementPreferred())
    }
}