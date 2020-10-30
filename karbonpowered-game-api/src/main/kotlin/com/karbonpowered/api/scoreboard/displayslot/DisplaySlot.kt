package com.karbonpowered.api.scoreboard.displayslot

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.annotation.CatalogedBy
import com.karbonpowered.text.format.TextColor

@CatalogedBy(DisplaySlots::class)
interface DisplaySlot : CatalogType {
    val teamColor: TextColor?

    fun withTeamColor(color: TextColor): DisplaySlot
}