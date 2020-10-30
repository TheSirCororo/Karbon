package com.karbonpowered.text.format

import com.karbonpowered.commons.Nameable
import com.karbonpowered.commons.lang.loadService

interface NamedTextColor : TextColor, Nameable {
    interface Provider {
        val values: Collection<NamedTextColor>

        fun of(value: Int): NamedTextColor?

        fun of(name: String): NamedTextColor?

        fun nearestTo(color: TextColor): NamedTextColor
    }

    companion object {
        private val provider by lazy { loadService<Provider>() }

        @JvmStatic
        @get:JvmName("values")
        val values = provider.values

        @JvmStatic
        fun of(value: Int): NamedTextColor? = provider.of(value)

        @JvmStatic
        fun of(name: String): NamedTextColor? = provider.of(name)

        @JvmStatic
        fun nearestTo(color: TextColor): NamedTextColor = provider.nearestTo(color)
    }
}