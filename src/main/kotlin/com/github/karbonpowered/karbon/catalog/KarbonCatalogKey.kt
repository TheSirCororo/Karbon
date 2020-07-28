package com.github.karbonpowered.karbon.catalog

import com.github.karbonpowered.api.catalog.CatalogKey

data class KarbonCatalogKey(
    override val namespace: String,
    override val value: String
) : CatalogKey {
    override fun formatted(): String = "$namespace:$value"

    override fun compareTo(other: CatalogKey): Int = formatted().compareTo(other.formatted())

    class Builder(
        override var namespace: String = "",
        override var value: String = ""
    ) : CatalogKey.Builder {
        override fun build(): CatalogKey = KarbonCatalogKey(namespace, value)

        override fun reset(): CatalogKey.Builder = Builder()
    }
}