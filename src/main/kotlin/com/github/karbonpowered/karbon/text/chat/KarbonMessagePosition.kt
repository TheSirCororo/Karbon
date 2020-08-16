package com.github.karbonpowered.karbon.text.chat

import com.github.karbonpowered.api.NamespacedKey
import com.github.karbonpowered.api.text.chat.MessagePosition

class KarbonMessagePosition(
        override val key: NamespacedKey
) : MessagePosition {
    override fun hashCode(): Int = key.hashCode()

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
                "ACTION_BAR" to { KarbonMessagePosition(NamespacedKey.minecraft("action_bar")) },
                "CHAT" to { KarbonMessagePosition(NamespacedKey.minecraft("chat")) },
                "SYSTEM" to { KarbonMessagePosition(NamespacedKey.minecraft("system")) },
        )
    }
}