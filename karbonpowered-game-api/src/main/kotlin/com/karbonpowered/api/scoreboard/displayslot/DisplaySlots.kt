package com.karbonpowered.api.scoreboard.displayslot

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry

object DisplaySlots : Catalog<DisplaySlot> {
    override val type: Class<DisplaySlot>
        get() = DisplaySlot::class.java

    @JvmField
    val BELOW_NAME = CatalogRegistry.getProvider(DisplaySlot::class, "BELOW_NAME").get()

    @JvmField
    val LIST = CatalogRegistry.getProvider(DisplaySlot::class, "LIST").get()

    @JvmField
    val SIDEBAR = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR").get()

    @JvmField
    val SIDEBAR_TEAM_AQUA = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_AQUA").get()

    @JvmField
    val SIDEBAR_TEAM_BLACK = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_BLACK").get()

    @JvmField
    val SIDEBAR_TEAM_BLUE = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_BLUE").get()

    @JvmField
    val SIDEBAR_TEAM_DARK_AQUA = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_DARK_AQUA").get()

    @JvmField
    val SIDEBAR_TEAM_DARK_BLUE = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_DARK_BLUE").get()

    @JvmField
    val SIDEBAR_TEAM_DARK_GRAY = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_DARK_GRAY").get()

    @JvmField
    val SIDEBAR_TEAM_DARK_GREEN = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_DARK_GREEN").get()

    @JvmField
    val SIDEBAR_TEAM_DARK_PURPLE = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_DARK_PURPLE").get()

    @JvmField
    val SIDEBAR_TEAM_DARK_RED = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_DARK_RED").get()

    @JvmField
    val SIDEBAR_TEAM_GOLD = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_GOLD").get()

    @JvmField
    val SIDEBAR_TEAM_GRAY = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_GRAY").get()

    @JvmField
    val SIDEBAR_TEAM_GREEN = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_GREEN").get()

    @JvmField
    val SIDEBAR_TEAM_LIGHT_PURPLE = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_LIGHT_PURPLE").get()

    @JvmField
    val SIDEBAR_TEAM_RED = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_RED").get()

    @JvmField
    val SIDEBAR_TEAM_WHITE = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_WHITE").get()

    @JvmField
    val SIDEBAR_TEAM_YELLOW = CatalogRegistry.getProvider(DisplaySlot::class, "SIDEBAR_TEAM_YELLOW").get()

}