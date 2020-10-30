package com.karbonpowered.api.entity.living.humanoid.player.gamemode

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry

object GameModes : Catalog<GameMode> {
    override val type: Class<GameMode>
        get() = GameMode::class.java

    @JvmField
    val ADVENTURE = CatalogRegistry.getProvider(GameMode::class, "ADVENTURE").get()

    @JvmField
    val CREATIVE = CatalogRegistry.getProvider(GameMode::class, "CREATIVE").get()

    @JvmField
    val NOT_SET = CatalogRegistry.getProvider(GameMode::class, "NOT_SET").get()

    @JvmField
    val SPECTATOR = CatalogRegistry.getProvider(GameMode::class, "SPECTATOR").get()

    @JvmField
    val SURVIVAL = CatalogRegistry.getProvider(GameMode::class, "SURVIVAL").get()
}