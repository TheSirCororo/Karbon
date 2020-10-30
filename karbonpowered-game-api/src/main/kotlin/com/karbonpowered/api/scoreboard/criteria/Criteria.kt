package com.karbonpowered.api.scoreboard.criteria

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry

object Criteria : Catalog<Criterion> {
    override val type: Class<Criterion>
        get() = Criterion::class.java

    @JvmField
    val DEATHS = CatalogRegistry.getProvider(Criterion::class, "DEATHS").get()

    @JvmField
    val DUMMY = CatalogRegistry.getProvider(Criterion::class, "DUMMY").get()

    @JvmField
    val HEALTH = CatalogRegistry.getProvider(Criterion::class, "HEALTH").get()

    @JvmField
    val PLAYER_KILLS = CatalogRegistry.getProvider(Criterion::class, "PLAYER_KILLS").get()

    @JvmField
    val TOTAL_KILLS = CatalogRegistry.getProvider(Criterion::class, "TOTAL_KILLS").get()

    @JvmField
    val TRIGGER = CatalogRegistry.getProvider(Criterion::class, "TRIGGER").get()

}