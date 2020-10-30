package com.karbonpowered.protocol.java.data

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.NamespacedKey

enum class InteractAction : CatalogType {
    INTERACT,
    ATTACK,
    INTERACT_AT;

    override val key: NamespacedKey = NamespacedKey.karbon(name.toLowerCase())
}