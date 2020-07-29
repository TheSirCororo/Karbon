package com.github.karbonpowered.karbon.text.chat

import com.github.karbonpowered.api.catalog.CatalogKey
import com.github.karbonpowered.api.text.chat.MessagePosition

class KarbonMessagePosition(
        override val key: CatalogKey
) : MessagePosition {
    companion object {
        fun generate() = sequenceOf(
                "ACTION_BAR" to { KarbonMessagePosition(CatalogKey.minecraft("action_bar")) },
                "CHAT" to { KarbonMessagePosition(CatalogKey.minecraft("chat")) },
                "SYSTEM" to { KarbonMessagePosition(CatalogKey.minecraft("system")) },
        )
    }
}