package com.github.karbonpowered.karbon.entity.living.humanoid.player.modelpart

import com.github.karbonpowered.api.catalog.CatalogKey
import com.github.karbonpowered.api.entity.living.humanoid.player.modelpart.ModelPart
import com.github.karbonpowered.api.text.translation.Translation
import com.github.karbonpowered.api.text.translation.TranslationRegistry

class KarbonModelPart(
        override val key: CatalogKey,
        override val translation: Translation
) : ModelPart {
    private val hashCode by lazy {
        key.hashCode()
    }

    override fun hashCode(): Int = hashCode

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonModelPart

        if (key != other.key) return false

        return true
    }

    override fun toString(): String = "ModelPart(key=$key)"

    companion object {
        fun generate() = sequenceOf(
                "CAPE" to { KarbonModelPart(CatalogKey.minecraft("cape"), TranslationRegistry.translate("options.modelPart.cape")) },
                "HAT" to { KarbonModelPart(CatalogKey.minecraft("hat"), TranslationRegistry.translate("options.modelPart.hat")) },
                "JACKET" to { KarbonModelPart(CatalogKey.minecraft("jacket"), TranslationRegistry.translate("options.modelPart.jacket")) },
                "LEFT_SLEEVE" to { KarbonModelPart(CatalogKey.minecraft("left_sleeve"), TranslationRegistry.translate("options.modelPart.left_sleeve")) },
                "RIGHT_SLEEVE" to { KarbonModelPart(CatalogKey.minecraft("right_sleeve"), TranslationRegistry.translate("options.modelPart.right_sleeve")) },
                "LEFT_PANTS_LEG" to { KarbonModelPart(CatalogKey.minecraft("left_pants_leg"), TranslationRegistry.translate("options.modelPart.left_pants_leg")) },
                "RIGHT_PANTS_LEG" to { KarbonModelPart(CatalogKey.minecraft("right_pants_leg"), TranslationRegistry.translate("options.modelPart.right_pants_leg")) },
        )
    }
}