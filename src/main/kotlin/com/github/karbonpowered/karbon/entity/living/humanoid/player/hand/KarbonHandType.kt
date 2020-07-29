package com.github.karbonpowered.karbon.entity.living.humanoid.player.hand

import com.github.karbonpowered.api.catalog.CatalogKey
import com.github.karbonpowered.api.entity.living.humanoid.player.hand.HandType
import com.github.karbonpowered.api.text.translation.Translation
import com.github.karbonpowered.api.text.translation.TranslationRegistry

data class KarbonHandType(
        override val key: CatalogKey,
        override val translation: Translation
) : HandType {
    companion object {
        fun generate() = sequenceOf(
                "MAIN_HAND" to { KarbonHandType(CatalogKey.minecraft("main_hand"), TranslationRegistry.translate("options.mainHand.right")) },
                "OFF_HAND" to { KarbonHandType(CatalogKey.minecraft("off_hand"), TranslationRegistry.translate("options.mainHand.left")) }
        )
    }
}