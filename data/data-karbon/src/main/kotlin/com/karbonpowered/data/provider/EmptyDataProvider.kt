package com.karbonpowered.data.provider

import com.karbonpowered.data.DataHolder
import com.karbonpowered.data.DataProvider
import com.karbonpowered.data.Key
import com.karbonpowered.data.transaction.DataTransactionResult
import com.karbonpowered.data.value.Value

class EmptyDataProvider<V: Value<E>, E>(
        override val key: Key<V>
) : DataProvider<V,E> {
    override fun allowsAsynchronousAccess(dataHolder: DataHolder): Boolean = false

    override fun get(dataHolder: DataHolder): E? = null

    override fun isSupported(dataHolder: DataHolder): Boolean = false

    override fun offer(dataHolder: DataHolder.Mutable, element: E): DataTransactionResult =
            DataTransactionResult.failResult(Value.immutableOf(key, element))

    override fun remove(dataHolder: DataHolder): DataTransactionResult = DataTransactionResult.failNoData()

    override fun <I : DataHolder.Immutable<I>> with(immutable: I, element: E): I? = null

    override fun <I : DataHolder.Immutable<I>> without(immutable: I): I? = immutable
}