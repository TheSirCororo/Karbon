package com.karbonpowered.catalog

import com.github.karbonpowered.commons.builder.ResettableBuilder

interface CatalogBuilder<C : CatalogType, B : ResettableBuilder<C, B>> : ResettableBuilder<C, B> {
    var key: NamespacedKey?

    @Suppress("UNCHECKED_CAST")
    fun key(key: NamespacedKey): B = apply {
        this.key = key
    } as B

    override fun build(): C = build(requireNotNull(key))
    fun build(key: NamespacedKey): C
}