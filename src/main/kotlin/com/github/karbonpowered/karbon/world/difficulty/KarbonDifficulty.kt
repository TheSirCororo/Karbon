package com.github.karbonpowered.karbon.world.difficulty

import com.github.karbonpowered.api.catalog.NamespacedKey
import com.github.karbonpowered.api.world.difficulty.Difficulty

data class KarbonDifficulty(
        override val key: NamespacedKey
) : Difficulty {
    companion object {
        fun generate() = sequenceOf(
                create("EASY"),
                create("HARD"),
                create("NORMAL"),
                create("PEACEFUL"),
        )

        private fun create(key: String) =
                key.toUpperCase() to { KarbonDifficulty(NamespacedKey.minecraft(key.toLowerCase())) }
    }
}