package com.karbonpowered.api.entity.living.humanoid.player.modelpart

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.annotation.CatalogedBy
import com.karbonpowered.translation.Translatable

@CatalogedBy(SkinParts::class)
interface SkinPart : CatalogType, Translatable {
    companion object
}