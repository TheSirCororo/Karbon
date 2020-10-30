package com.karbonpowered.data.value.immutable

import com.karbonpowered.data.key.KarbonKey
import com.karbonpowered.data.value.Value
import com.karbonpowered.data.value.mutable.MutableKarbonValue
import java.util.function.Function

class ImmutableKarbonValue<E>(
    override val key: KarbonKey<Value<E>, E>,
    override var element: E
) : AbstractImmutableKarbonValue<E>() {
    override fun with(value: E): Value.Immutable<E> = key.valueConstructor.getImmutable(value).asImmutable()

    override fun asMutable(): Value.Mutable<E> = MutableKarbonValue(key, get())

    override fun transform(function: Function<E, E>): Value.Immutable<E> = with(function.apply(get()))
}