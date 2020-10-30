package com.karbonpowered.api.entity.living.humanoid.player.hand

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry

object HandTypes : Catalog<HandType> {
    override val type: Class<HandType>
        get() = HandType::class.java

    @JvmField
    val MAIN_HAND = CatalogRegistry.getProvider(HandType::class, "MAIN_HAND").get()

    @JvmField
    val OFF_HAND = CatalogRegistry.getProvider(HandType::class, "OFF_HAND").get()

}