package com.karbonpowered.api.scoreboard.objective.displaymode

import com.karbonpowered.api.scoreboard.displayslot.DisplaySlot
import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry

object ObjectiveDisplayModes : Catalog<DisplaySlot> {
    override val type: Class<DisplaySlot>
        get() = DisplaySlot::class.java

    @JvmField
    val HEARTS = CatalogRegistry.getProvider(DisplaySlot::class, "HEARTS").get()

    @JvmField
    val INTEGER = CatalogRegistry.getProvider(DisplaySlot::class, "INTEGER").get()

}