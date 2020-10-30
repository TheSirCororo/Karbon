package com.karbonpowered.data.value

import com.karbonpowered.data.key.KarbonKey

class SimpleValueConstructor<V : Value<E>, E>(
    val key: KarbonKey<V, E>,
    val mutableConstructor: (KarbonKey<V, E>, E) -> V,
    val immutableConstructor: (KarbonKey<V, E>, E) -> V,
) : ValueConstructor<V, E> {
    override fun getMutable(element: E): V = mutableConstructor(key, element)

    override fun getRawImmutable(element: E): V = immutableConstructor(key, element)
}