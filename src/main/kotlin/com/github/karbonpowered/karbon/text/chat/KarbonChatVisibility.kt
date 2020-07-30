package com.github.karbonpowered.karbon.text.chat

import com.github.karbonpowered.api.catalog.CatalogKey
import com.github.karbonpowered.api.text.chat.ChatVisibility
import com.github.karbonpowered.api.text.chat.MessagePosition
import com.github.karbonpowered.api.text.chat.MessagePositions
import com.github.karbonpowered.api.text.translation.Translation
import com.github.karbonpowered.api.text.translation.TranslationRegistry
import java.util.function.Function

data class KarbonChatVisibility(
        override val key: CatalogKey,
        override val translation: Translation,
        val function: Function<MessagePosition, Boolean>
) : ChatVisibility {
    private val hashCode: Int by lazy {
        key.hashCode()
    }

    override fun isVisible(messagePosition: MessagePosition): Boolean = function.apply(messagePosition)

    override fun hashCode(): Int = hashCode

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonChatVisibility

        if (key != other.key) return false

        return true
    }

    companion object {
        fun generate() = sequenceOf(
                "FULL" to { KarbonChatVisibility(CatalogKey.minecraft("full"), TranslationRegistry.translate("options.chat.visibility.full")) { true } },
                "SYSTEM" to {
                    KarbonChatVisibility(CatalogKey.minecraft("system"), TranslationRegistry.translate("options.chat.visibility.system")) {
                        when (it) {
                            MessagePositions.SYSTEM, MessagePositions.ACTION_BAR -> true
                            else -> false
                        }
                    }
                },
                "HIDDEN" to {
                    KarbonChatVisibility(CatalogKey.minecraft("hidden"), TranslationRegistry.translate("options.chat.visibility.hidden")) {
                        when (it) {
                            MessagePositions.ACTION_BAR -> true
                            else -> false
                        }
                    }
                },
        )
    }
}