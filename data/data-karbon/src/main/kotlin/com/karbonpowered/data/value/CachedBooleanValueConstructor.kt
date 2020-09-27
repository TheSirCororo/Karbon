package com.karbonpowered.data.value

class CachedBooleanValueConstructor(
        val original: ValueConstructor<Value<Boolean>, Boolean>
) : ValueConstructor<Value<Boolean>, Boolean> {
    val immutableValueFalse = original.getImmutable(false)
    val immutableValueTrue = original.getImmutable(true)

    override fun getMutable(element: Boolean): Value<Boolean> = original.getMutable(element)

    override fun getImmutable(element: Boolean): Value<Boolean> = if (element) immutableValueTrue else immutableValueFalse

    override fun getRawImmutable(element: Boolean): Value<Boolean> = getImmutable(element)
}