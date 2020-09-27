package com.karbonpowered.data

import com.karbonpowered.data.transaction.DataTransactionResult
import com.karbonpowered.data.value.Value

interface DataProvider<V : Value<E>, E> {
    val key: Key<V>

    fun allowsAsynchronousAccess(dataHolder: DataHolder): Boolean

    operator fun get(dataHolder: DataHolder): E?

    fun getValue(dataHolder: DataHolder) = get(dataHolder)?.let { Value.genericMutableOf(key, it) }

    fun isSupported(dataHolder: DataHolder): Boolean

    fun offer(dataHolder: DataHolder.Mutable, element: E): DataTransactionResult

    fun offerValue(dataHolder: DataHolder.Mutable, value: V): DataTransactionResult = offer(dataHolder, value.get())

    fun remove(dataHolder: DataHolder): DataTransactionResult

    fun <I : DataHolder.Immutable<I>> with(immutable: I, element: E): I?

    fun <I : DataHolder.Immutable<I>> withValue(immutable: I, value: V): I? = with(immutable, value.get())

    fun <I : DataHolder.Immutable<I>> without(immutable: I): I?
}