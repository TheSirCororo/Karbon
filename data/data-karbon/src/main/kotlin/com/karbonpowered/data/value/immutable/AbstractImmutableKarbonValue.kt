package com.karbonpowered.data.value.immutable

import com.karbonpowered.data.copy.CopyHelper
import com.karbonpowered.data.value.AbstractKarbonValue
import com.karbonpowered.data.value.Value

abstract class AbstractImmutableKarbonValue<E> : AbstractKarbonValue<E>(), Value.Immutable<E> {
    override fun get(): E = CopyHelper.copy(super.get())
}