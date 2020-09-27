package com.karbonpowered.data.value

import com.karbonpowered.data.key.KarbonKey

abstract class AbstractKarbonValue<E> : Value<E> {
    abstract override val key: KarbonKey<out Value<E>, E>
    abstract var element: E

    override fun get(): E = element

    override fun toString(): String = "KarbonValue(key=$key, element=$element)"
}