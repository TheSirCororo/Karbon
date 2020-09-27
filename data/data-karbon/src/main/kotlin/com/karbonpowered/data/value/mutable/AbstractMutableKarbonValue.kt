package com.karbonpowered.data.value.mutable

import com.karbonpowered.data.value.AbstractKarbonValue
import com.karbonpowered.data.value.Value

abstract class AbstractMutableKarbonValue<E> : AbstractKarbonValue<E>(), Value.Mutable<E> {
    override fun set(value: E): Value.Mutable<E> = apply {
        this.element = value
    }
}