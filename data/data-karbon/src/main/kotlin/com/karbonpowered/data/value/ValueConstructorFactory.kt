package com.karbonpowered.data.value

import com.karbonpowered.data.key.KarbonKey
import com.karbonpowered.data.value.immutable.ImmutableKarbonValue
import com.karbonpowered.data.value.mutable.MutableKarbonListValue
import com.karbonpowered.data.value.mutable.MutableKarbonValue

object ValueConstructorFactory {
    @Suppress("UNCHECKED_CAST")
    fun <V : Value<E>, E> getConstructor(key: KarbonKey<V, E>): ValueConstructor<V, E> {
        val valueType = key.valueToken.rawType
        return when {
            ListValue::class.java.isAssignableFrom(valueType) -> SimpleValueConstructor(
                    key,
                    { k, e -> MutableKarbonListValue(k as KarbonKey<ListValue.Mutable<E>, List<E>>, e as List<E>) as V },
                    { k, e -> TODO() },
            )
            ListValue::class.java.isAssignableFrom(valueType) -> {
                TODO()
            }
            MapValue::class.java.isAssignableFrom(valueType) -> {
                TODO()
            }
            else -> {
                val valueConstructor = SimpleValueConstructor(
                        key,
                        { k, e -> MutableKarbonValue(k as KarbonKey<Value<E>, E>, e) as V },
                        { k, e -> ImmutableKarbonValue(k as KarbonKey<Value<E>, E>, e) as V },
                )
                val elementType = key.elementToken.rawType as Class<*>
                when {
                    Enum::class.java.isAssignableFrom(elementType) ->
                        CachedEnumValueConstructor(
                                valueConstructor as ValueConstructor<Value<Enum<*>>, Enum<*>>,
                                elementType as Class<Enum<*>>
                        )
                    elementType == Boolean::class.java -> {
                        CachedBooleanValueConstructor(
                                valueConstructor as ValueConstructor<Value<Boolean>, Boolean>
                        )
                    }
                    else -> valueConstructor
                }
            }
        } as ValueConstructor<V, E>
    }
}