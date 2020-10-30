package com.karbonpowered.api.entity

import com.karbonpowered.api.entity.weather.LightningBolt
import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry
import com.karbonpowered.catalog.getProvider

object EntityTypes : Catalog<EntityType<*>> {
    override val type: Class<EntityType<*>>
        get() = EntityType::class.java

    @JvmField
    val LIGHTNING_BOLT = CatalogRegistry.getProvider<EntityType<*>, EntityType<LightningBolt>>("lightning_bolt").get()
}