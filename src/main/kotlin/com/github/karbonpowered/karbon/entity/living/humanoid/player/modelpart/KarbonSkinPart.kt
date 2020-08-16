package com.github.karbonpowered.karbon.entity.living.humanoid.player.modelpart

import com.github.karbonpowered.api.NamespacedKey
import com.github.karbonpowered.api.entity.living.humanoid.player.modelpart.SkinPart
import com.github.karbonpowered.api.text.translation.Translation
import com.github.karbonpowered.api.text.translation.TranslationRegistry

class KarbonSkinPart(
        override val key: NamespacedKey,
        override val translation: Translation
) : SkinPart {
    override fun hashCode(): Int = key.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonSkinPart

        if (key != other.key) return false

        return true
    }

    override fun toString(): String = "SkinPart(key=$key)"

    companion object {
        fun generate() = sequenceOf(
                "CAPE" to { KarbonSkinPart(NamespacedKey.minecraft("cape"), TranslationRegistry.translate("options.modelPart.cape")) },
                "HAT" to { KarbonSkinPart(NamespacedKey.minecraft("hat"), TranslationRegistry.translate("options.modelPart.hat")) },
                "JACKET" to { KarbonSkinPart(NamespacedKey.minecraft("jacket"), TranslationRegistry.translate("options.modelPart.jacket")) },
                "LEFT_SLEEVE" to { KarbonSkinPart(NamespacedKey.minecraft("left_sleeve"), TranslationRegistry.translate("options.modelPart.left_sleeve")) },
                "RIGHT_SLEEVE" to { KarbonSkinPart(NamespacedKey.minecraft("right_sleeve"), TranslationRegistry.translate("options.modelPart.right_sleeve")) },
                "LEFT_PANTS_LEG" to { KarbonSkinPart(NamespacedKey.minecraft("left_pants_leg"), TranslationRegistry.translate("options.modelPart.left_pants_leg")) },
                "RIGHT_PANTS_LEG" to { KarbonSkinPart(NamespacedKey.minecraft("right_pants_leg"), TranslationRegistry.translate("options.modelPart.right_pants_leg")) },
        )
    }
}