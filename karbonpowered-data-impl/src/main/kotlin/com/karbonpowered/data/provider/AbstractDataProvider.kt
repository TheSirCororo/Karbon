package com.karbonpowered.data.provider

import com.karbonpowered.data.DataHolder
import com.karbonpowered.data.DataProvider
import com.karbonpowered.data.Key
import com.karbonpowered.data.value.Value

abstract class AbstractDataProvider<V : Value<E>, E> : DataProvider<V, E> {
    abstract override val key: Key<V>

    override fun allowsAsynchronousAccess(dataHolder: DataHolder): Boolean = false

    interface KnownHolderType {
        val holderType: Class<*>
    }
}