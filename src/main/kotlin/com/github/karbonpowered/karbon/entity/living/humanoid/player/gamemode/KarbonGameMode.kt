package com.github.karbonpowered.karbon.entity.living.humanoid.player.gamemode

import com.github.karbonpowered.api.catalog.CatalogKey
import com.github.karbonpowered.api.entity.living.humanoid.player.gamemode.GameMode
import com.github.karbonpowered.api.text.translation.Translation
import com.github.karbonpowered.api.text.translation.TranslationRegistry

data class KarbonGameMode(
        override val key: CatalogKey,
        override val translation: Translation
) : GameMode {
    private val hashCode: Int by lazy {
        key.hashCode()
    }

    override fun hashCode(): Int = hashCode

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
                "SURVIVAL" to { KarbonGameMode(CatalogKey.minecraft("survival"), TranslationRegistry.translate("gameMode.survival")) },
                "CREATIVE" to { KarbonGameMode(CatalogKey.minecraft("creative"), TranslationRegistry.translate("gameMode.creative")) },
                "ADVENTURE" to { KarbonGameMode(CatalogKey.minecraft("adventure"), TranslationRegistry.translate("gameMode.adventure")) },
                "SPECTATOR" to { KarbonGameMode(CatalogKey.minecraft("spectator"), TranslationRegistry.translate("gameMode.spectator")) },
                "NOT_SET" to { KarbonGameMode(CatalogKey.karbon("not_set"), TranslationRegistry.translate("karbon.gameMode.not_set")) },
        )
    }
}