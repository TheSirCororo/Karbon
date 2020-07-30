package com.github.karbonpowered.karbon.text.chat

import com.github.karbonpowered.api.catalog.CatalogKey
import com.github.karbonpowered.api.text.chat.MessagePosition

class KarbonMessagePosition(
        override val key: CatalogKey
) : MessagePosition {
    private val hashCode: Int by lazy {
        key.hashCode()
    }

    override fun hashCode(): Int = hashCode

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonMessagePosition

        if (key != other.key) return false

        return true
    }

    override fun toString(): String = "MessagePosition(key=$key)"

    companion object {
        fun generate() = sequenceOf(
                "ACTION_BAR" to { KarbonMessagePosition(CatalogKey.minecraft("action_bar")) },
                "CHAT" to { KarbonMessagePosition(CatalogKey.minecraft("chat")) },
                "SYSTEM" to { KarbonMessagePosition(CatalogKey.minecraft("system")) },
        )
    }
}