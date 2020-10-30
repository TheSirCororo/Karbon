package com.karbonpowered.catalog

import java.util.stream.Stream

interface Catalog<T : CatalogType> {
    val type: Class<T>

    fun stream(): Stream<T> = CatalogRegistry.getAllOf(type)

    fun sequence(): Sequence<T> = Sequence {
        stream().iterator()
    }

    fun valueOf(namespacedKey: NamespacedKey): T? = CatalogRegistry[type, namespacedKey]
}