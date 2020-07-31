package com.github.karbonpowered.karbon.catalog

import com.github.karbonpowered.api.catalog.CatalogKey

data class KarbonCatalogKey(
        override val namespace: String,
        override val value: String
) : CatalogKey {
    private val hashCode: Int by lazy {
        var result = namespace.hashCode()
        result = 31 * result + value.hashCode()
        result
    }

    override fun formatted(): String = "$namespace:$value"

    override fun compareTo(other: CatalogKey): Int = formatted().compareTo(other.formatted())

    override fun hashCode(): Int = hashCode

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonCatalogKey

        if (namespace != other.namespace) return false
        if (value != other.value) return false

        return true
    }

    class Builder(
            override var namespace: String = "",
            override var value: String = ""
    ) : CatalogKey.Builder {
        override fun build(): CatalogKey = KarbonCatalogKey(namespace, value)

        override fun reset(): CatalogKey.Builder = Builder()
    }
}