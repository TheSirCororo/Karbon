package com.github.karbonpowered.karbon.world.difficulty

import com.github.karbonpowered.api.world.difficulty.Difficulty
import com.karbonpowered.catalog.NamespacedKey

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