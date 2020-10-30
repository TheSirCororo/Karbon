package com.karbonpowered.api.world.difficulty

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry

object Difficulties : Catalog<Difficulty> {
    override val type: Class<Difficulty>
        get() = Difficulty::class.java

    @JvmField
    val EASY = CatalogRegistry.getProvider(Difficulty::class, "EASY").get()

    @JvmField
    val HARD = CatalogRegistry.getProvider(Difficulty::class, "HARD").get()

    @JvmField
    val NORMAL = CatalogRegistry.getProvider(Difficulty::class, "NORMAL").get()

    @JvmField
    val PEACEFUL = CatalogRegistry.getProvider(Difficulty::class, "PEACEFUL").get()

}