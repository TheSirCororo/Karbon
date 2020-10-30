package com.karbonpowered.protocol.java.exception

class UnmappedValueException(
    val value: Any,
    val keyType: Class<*>
) : IllegalArgumentException("Value $value has no mapping for key class $keyType")