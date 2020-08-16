package com.github.karbonpowered.karbon.entity.living.humanoid.player.gamemode

import com.github.karbonpowered.api.NamespacedKey
import com.github.karbonpowered.api.entity.living.humanoid.player.gamemode.GameMode
import com.github.karbonpowered.api.text.translation.Translation
import com.github.karbonpowered.api.text.translation.TranslationRegistry

data class KarbonGameMode(
        override val key: NamespacedKey,
        override val translation: Translation
) : GameMode {
    override fun hashCode(): Int = key.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonGameMode

        if (key != other.key) return false

        return true
    }

    override fun toString(): String = "GameMode(key=$key)"

    companion object {
        fun generate() = sequenceOf(
                "SURVIVAL" to { KarbonGameMode(NamespacedKey.minecraft("survival"), TranslationRegistry.translate("gameMode.survival")) },
                "CREATIVE" to { KarbonGameMode(NamespacedKey.minecraft("creative"), TranslationRegistry.translate("gameMode.creative")) },
                "ADVENTURE" to { KarbonGameMode(NamespacedKey.minecraft("adventure"), TranslationRegistry.translate("gameMode.adventure")) },
                "SPECTATOR" to { KarbonGameMode(NamespacedKey.minecraft("spectator"), TranslationRegistry.translate("gameMode.spectator")) },
                "NOT_SET" to { KarbonGameMode(NamespacedKey.karbon("not_set"), TranslationRegistry.translate("karbon.gameMode.not_set")) },
        )
    }
}