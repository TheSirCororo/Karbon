package com.karbonpowered.data.provider

import com.karbonpowered.data.DataHolder
import com.karbonpowered.data.DataProvider
import com.karbonpowered.data.Key
import com.karbonpowered.data.transaction.DataTransactionResult
import com.karbonpowered.data.value.Value

class DelegateDataProvider<V : Value<E>, E>(
        override val key: Key<V>,
        val providers: List<DataProvider<V, E>>
) : DataProvider<V, E> {
    override fun allowsAsynchronousAccess(dataHolder: DataHolder): Boolean = providers.all { it.allowsAsynchronousAccess(dataHolder) }

    override fun get(dataHolder: DataHolder): E? = providers.asSequence().map { it[dataHolder] }.find { it != null }

    override fun isSupported(dataHolder: DataHolder): Boolean = providers.all { it.isSupported(dataHolder) }

    override fun offer(dataHolder: DataHolder.Mutable, element: E): DataTransactionResult = providers
            .asSequence()
            .map { it.offer(dataHolder, element) }
            .filter { it.type != DataTransactionResult.Type.FAILURE }
            .firstOrNull()
            ?: DataTransactionResult.failResult(Value.immutableOf(key, element!!))

    override fun remove(dataHolder: DataHolder): DataTransactionResult = providers
            .asSequence()
            .map { it.remove(dataHolder) }
            .filter { it.type != DataTransactionResult.Type.FAILURE }
            .firstOrNull()
            ?: DataTransactionResult.failNoData()

    override fun <I : DataHolder.Immutable<I>> with(immutable: I, element: E): I? = providers
            .asSequence()
            .map { it.with(immutable, element) }
            .filterNotNull()
            .firstOrNull()

    override fun <I : DataHolder.Immutable<I>> without(immutable: I): I? = providers
            .asSequence()
            .map { it.without(immutable) }
            .filterNotNull()
            .firstOrNull()
            ?: immutable


}