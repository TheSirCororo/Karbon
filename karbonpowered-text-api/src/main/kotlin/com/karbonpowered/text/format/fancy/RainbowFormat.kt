package com.karbonpowered.text.format.fancy

import com.karbonpowered.commons.lang.loadService

interface RainbowFormat : FancyFormat {
    val phase: Int
    val width: Int
    val center: Int

    interface Factory {
        fun create(phase: Int, width: Int, center: Int): RainbowFormat
    }

    companion object {
        private val factory = loadService<Factory>()

        @JvmStatic
        fun of(phase: Int = 0, width: Int = 127, center: Int = 128): RainbowFormat =
            factory.create(phase, width, center)
    }
}