package com.github.karbonpowered.karbon.world.difficulty

import com.github.karbonpowered.api.catalog.CatalogKey
import com.github.karbonpowered.api.world.difficulty.Difficulty

data class KarbonDifficulty(
        override val key: CatalogKey
) : Difficulty {
    companion object {
        fun generate() = sequenceOf(
                create("EASY"),
                create("HARD"),
                create("NORMAL"),
                create("PEACEFUL"),
        )

        private fun create(key: String) =
                key.toUpperCase() to { KarbonDifficulty(CatalogKey.minecraft(key.toLowerCase())) }
    }
}