package com.karbonpowered.text.karbon.format

import com.karbonpowered.commons.store.MemoryStore
import com.karbonpowered.text.format.NamedTextColor
import com.karbonpowered.text.format.TextColor

data class KarbonNamedTextColor(
    override val name: String,
    override val value: Int
) : NamedTextColor {
    override fun toString(): String = name

    class Provider : NamedTextColor.Provider {
        override val values = listOf(
            BLACK,
            DARK_BLUE,
            DARK_GREEN,
            DARK_AQUA,
            DARK_RED,
            DARK_PURPLE,
            GOLD,
            GRAY,
            DARK_GRAY,
            BLUE,
            GREEN,
            AQUA,
            RED,
            LIGHT_PURPLE,
            YELLOW,
            WHITE
        )
        private val store = MemoryStore.of<String, KarbonNamedTextColor>({ it.name }, values)

        override fun of(value: Int): NamedTextColor? = when (value) {
            BLACK_VALUE -> BLACK
            DARK_BLUE_VALUE -> DARK_BLUE
            DARK_GREEN_VALUE -> DARK_GREEN
            DARK_AQUA_VALUE -> DARK_AQUA
            DARK_RED_VALUE -> DARK_RED
            DARK_PURPLE_VALUE -> DARK_PURPLE
            GOLD_VALUE -> GOLD
            GRAY_VALUE -> GRAY
            DARK_GRAY_VALUE -> DARK_GRAY
            BLUE_VALUE -> BLUE
            GREEN_VALUE -> GREEN
            AQUA_VALUE -> AQUA
            RED_VALUE -> RED
            LIGHT_PURPLE_VALUE -> LIGHT_PURPLE
            YELLOW_VALUE -> YELLOW
            WHITE_VALUE -> WHITE
            else -> null
        }

        override fun of(name: String): NamedTextColor? = store[name]

        override fun nearestTo(color: TextColor): NamedTextColor {
            if (color is NamedTextColor) return color

            var matchedDistance = Int.MAX_VALUE
            var match = values.first()
            for (potential in values) {
                val distance = color.distanceSquared(potential)
                if (distance < matchedDistance) {
                    match = potential
                    matchedDistance = distance
                }
                if (distance == 0) {
                    break
                }
            }

            return match
        }

        companion object {
            private const val BLACK_VALUE = 0x000000
            private const val DARK_BLUE_VALUE = 0x0000aa
            private const val DARK_GREEN_VALUE = 0x00aa00
            private const val DARK_AQUA_VALUE = 0x00aaaa
            private const val DARK_RED_VALUE = 0xaa0000
            private const val DARK_PURPLE_VALUE = 0xaa00aa
            private const val GOLD_VALUE = 0xffaa00
            private const val GRAY_VALUE = 0xaaaaaa
            private const val DARK_GRAY_VALUE = 0x555555
            private const val BLUE_VALUE = 0x5555ff
            private const val GREEN_VALUE = 0x55ff55
            private const val AQUA_VALUE = 0x55ffff
            private const val RED_VALUE = 0xff5555
            private const val LIGHT_PURPLE_VALUE = 0xff55ff
            private const val YELLOW_VALUE = 0xffff55
            private const val WHITE_VALUE = 0xffffff

            val BLACK = KarbonNamedTextColor("black", BLACK_VALUE)
            val DARK_BLUE = KarbonNamedTextColor("dark_blue", DARK_BLUE_VALUE)
            val DARK_GREEN = KarbonNamedTextColor("dark_green", DARK_GREEN_VALUE)
            val DARK_AQUA = KarbonNamedTextColor("dark_aqua", DARK_AQUA_VALUE)
            val DARK_RED = KarbonNamedTextColor("dark_red", DARK_RED_VALUE)
            val DARK_PURPLE = KarbonNamedTextColor("dark_purple", DARK_PURPLE_VALUE)
            val GOLD = KarbonNamedTextColor("gold", GOLD_VALUE)
            val GRAY = KarbonNamedTextColor("gray", GRAY_VALUE)
            val DARK_GRAY = KarbonNamedTextColor("dark_gray", DARK_GRAY_VALUE)
            val BLUE = KarbonNamedTextColor("blue", BLUE_VALUE)
            val GREEN = KarbonNamedTextColor("green", GREEN_VALUE)
            val AQUA = KarbonNamedTextColor("aqua", AQUA_VALUE)
            val RED = KarbonNamedTextColor("red", RED_VALUE)
            val LIGHT_PURPLE = KarbonNamedTextColor("light_purple", LIGHT_PURPLE_VALUE)
            val YELLOW = KarbonNamedTextColor("yellow", YELLOW_VALUE)
            val WHITE = KarbonNamedTextColor("white", WHITE_VALUE)

            private fun TextColor.distanceSquared(other: TextColor): Int {
                val rAvg = (red + other.red) / 2
                val dR = red - other.red
                val dG = green - other.green
                val dB = blue - other.blue
                return (2 + rAvg / 256) * (dR * dR) + 4 * (dG * dG) + (2 + (255 - rAvg) / 256) * (dB * dB)
            }
        }
    }
}