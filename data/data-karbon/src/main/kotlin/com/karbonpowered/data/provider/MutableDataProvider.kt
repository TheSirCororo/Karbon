package com.karbonpowered.data.provider

import com.karbonpowered.data.DataHolder
import com.karbonpowered.data.DataProvider
import com.karbonpowered.data.value.Value

abstract class MutableDataProvider<V : Value<E>, E> : AbstractDataProvider<V, E>(), DataProvider<V,E> {
    override fun allowsAsynchronousAccess(dataHolder: DataHolder): Boolean = false

    override fun <I : DataHolder.Immutable<I>> with(immutable: I, element: E): I? =null
    override fun <I : DataHolder.Immutable<I>> withValue(immutable: I, value: V): I? = null
    override fun <I : DataHolder.Immutable<I>> without(immutable: I): I? = null
}