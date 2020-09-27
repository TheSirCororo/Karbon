package com.karbonpowered.data.value

import com.karbonpowered.data.Key
import com.karbonpowered.data.key.KarbonKey

class KarbonValueFactory : Value.Factory {
    override fun <V : Value<E>, E> mutableOf(key: Key<V>, element: E): V = (key as KarbonKey<V,E>).valueConstructor.getMutable(element)

    override fun <V : Value<E>, E> immutableOf(key: Key<V>, element: E): V = (key as KarbonKey<V,E>).valueConstructor.getImmutable(element)
}