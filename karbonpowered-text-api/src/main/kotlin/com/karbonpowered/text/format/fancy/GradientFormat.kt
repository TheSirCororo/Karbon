package com.karbonpowered.text.format.fancy

import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.format.TextColor

interface GradientFormat : FancyFormat {
    val phase: Float
    val colors: Array<out TextColor>

    interface Factory {
        fun create(phase: Float, vararg colors: TextColor): GradientFormat
    }

    companion object {
        private val factory = loadService<Factory>()

        @JvmStatic
        fun of(
            phase: Float = 1.0f,
            vararg colors: TextColor = arrayOf(TextColor(0xffffff), TextColor(0x000000))
        ): GradientFormat =
            factory.create(phase, *colors)
    }
}