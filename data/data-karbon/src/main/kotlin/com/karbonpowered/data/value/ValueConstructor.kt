package com.karbonpowered.data.value

import com.karbonpowered.data.copy.CopyHelper

interface ValueConstructor<V : Value<E>, E> {
    fun getMutable(element: E): V

    fun getImmutable(element: E): V = getRawImmutable(CopyHelper.copy(element))

    fun getRawImmutable(element: E): V
}