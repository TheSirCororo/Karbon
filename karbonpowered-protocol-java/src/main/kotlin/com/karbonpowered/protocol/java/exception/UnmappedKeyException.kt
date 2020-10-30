package com.karbonpowered.protocol.java.exception

import com.karbonpowered.catalog.CatalogType

class UnmappedKeyException(
    val key: CatalogType,
    val keyType: Class<*>
) : IllegalArgumentException("Key $key has no mapping for key class $keyType")