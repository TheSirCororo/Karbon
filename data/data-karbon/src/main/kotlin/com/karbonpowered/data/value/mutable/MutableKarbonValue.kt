package com.karbonpowered.data.value.mutable

import com.karbonpowered.data.copy.CopyHelper
import com.karbonpowered.data.key.KarbonKey
import com.karbonpowered.data.value.Value
import java.util.function.Function

class MutableKarbonValue<E>(
        override val key: KarbonKey<Value<E>, E>,
        override var element: E
) : AbstractMutableKarbonValue<E>() {
    override fun asImmutable(): Value.Immutable<E> = key.valueConstructor.getImmutable(element).asImmutable()

    override fun transform(function: Function<E, E>): Value.Mutable<E> = set(function.apply(get()))

    override fun copy(): Value.Mutable<E> = MutableKarbonValue(key, CopyHelper.copy(element))
}