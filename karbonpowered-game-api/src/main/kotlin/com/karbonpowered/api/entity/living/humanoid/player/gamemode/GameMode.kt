package com.karbonpowered.api.entity.living.humanoid.player.gamemode

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.annotation.CatalogedBy
import com.karbonpowered.translation.Translatable

@CatalogedBy(GameModes::class)
interface GameMode : CatalogType, Translatable {
    companion object
}