package com.karbonpowered.api.scoreboard

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry

object Visibilities : Catalog<Visibility> {
    override val type: Class<Visibility>
        get() = Visibility::class.java

    @JvmField
    val ALWAYS = CatalogRegistry.getProvider(Visibility::class, "ALWAYS").get()

    @JvmField
    val HIDE_FOR_OTHER_TEAMS = CatalogRegistry.getProvider(Visibility::class, "HIDE_FOR_OTHER_TEAMS").get()

    @JvmField
    val HIDE_FOR_OWN_TEAM = CatalogRegistry.getProvider(Visibility::class, "HIDE_FOR_OWN_TEAM").get()

    @JvmField
    val NEVER = CatalogRegistry.getProvider(Visibility::class, "NEVER").get()

}