package com.karbonpowered.text.karbon.format

import com.karbonpowered.commons.store.MemoryStore
import com.karbonpowered.text.format.TextDecoration

data class KarbonTextDecoration(
    override val name: String
) : TextDecoration {
    override fun toString(): String = name

    class Provider : TextDecoration.Provider {
        override val values = listOf(OBFUSCATED, BOLD, STRIKETHROUGH, UNDERLINED, ITALIC)
        private val store = MemoryStore.of<String, KarbonTextDecoration>({ it.name }, values)

        override fun provide(name: String): TextDecoration? = store[name]

        companion object {
            val OBFUSCATED = KarbonTextDecoration("obfuscated")
            val BOLD = KarbonTextDecoration("bold")
            val STRIKETHROUGH = KarbonTextDecoration("strikethrough")
            val UNDERLINED = KarbonTextDecoration("underlined")
            val ITALIC = KarbonTextDecoration("italic")
        }
    }
}