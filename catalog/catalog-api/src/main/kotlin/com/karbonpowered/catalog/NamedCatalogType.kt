package com.karbonpowered.catalog

import com.github.karbonpowered.commons.Nameable

interface NamedCatalogType : CatalogType, Nameable {
    override val name: String
        get() = key.value
}