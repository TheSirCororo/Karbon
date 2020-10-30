package com.karbonpowered.data.value

class CachedEnumValueConstructor<V : Value<E>, E : Enum<E>>(
    val original: ValueConstructor<V, E>,
    enumType: Class<E>
) : ValueConstructor<V, E> {
    private val values = enumType.enumConstants.map {
        original.getImmutable(it)
    }

    override fun getMutable(element: E): V = original.getMutable(element)

    override fun getImmutable(element: E): V = values[element.ordinal]

    override fun getRawImmutable(element: E): V = getImmutable(element)
}