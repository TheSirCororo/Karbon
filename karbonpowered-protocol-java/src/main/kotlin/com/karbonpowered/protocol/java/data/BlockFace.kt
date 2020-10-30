package com.karbonpowered.protocol.java.data

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.NamespacedKey

enum class BlockFace : CatalogType {
    DOWN,
    UP,
    NORTH,
    SOUTH,
    WEST,
    EAST,
    SPECIAL;

    override val key: NamespacedKey = NamespacedKey.karbon(name.toLowerCase())
}