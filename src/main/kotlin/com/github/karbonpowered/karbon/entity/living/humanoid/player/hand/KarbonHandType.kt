package com.github.karbonpowered.karbon.entity.living.humanoid.player.hand

import com.github.karbonpowered.api.NamespacedKey
import com.github.karbonpowered.api.entity.living.humanoid.player.hand.HandType
import com.github.karbonpowered.translation.Translation
import com.github.karbonpowered.translation.TranslationRegistry

data class KarbonHandType(
        override val key: NamespacedKey,
        override val translation: Translation
) : HandType {
    override fun hashCode(): Int = key.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonHandType

        if (key != other.key) return false

        return true
    }

    override fun toString(): String = "HandType(key=$key)"

    companion object {
        fun generate() = sequenceOf(
                "MAIN_HAND" to { KarbonHandType(NamespacedKey.minecraft("main_hand"), TranslationRegistry.translate("options.mainHand.right")) },
                "OFF_HAND" to { KarbonHandType(NamespacedKey.minecraft("off_hand"), TranslationRegistry.translate("options.mainHand.left")) }
        )
    }
}