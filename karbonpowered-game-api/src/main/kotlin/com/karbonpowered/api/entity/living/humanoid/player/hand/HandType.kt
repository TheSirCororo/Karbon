package com.karbonpowered.api.entity.living.humanoid.player.hand

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.annotation.CatalogedBy
import com.karbonpowered.translation.Translatable

@CatalogedBy(HandTypes::class)
interface HandType : CatalogType, Translatable {
    companion object
}