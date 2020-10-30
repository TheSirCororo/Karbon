package com.karbonpowered.text.format.fancy

import com.karbonpowered.text.format.TextColor

object FancyFormats {
    fun gradient(vararg colors: TextColor = arrayOf(TextColor(0xffffff), TextColor(0x000000))): GradientFormat =
        GradientFormat.of(colors = colors)

    fun gradient(
        phase: Float = 0.0f,
        vararg colors: TextColor = arrayOf(TextColor(0xffffff), TextColor(0x000000))
    ): GradientFormat =
        GradientFormat.of(phase, *colors)

    fun rainbow(phase: Int = 0): RainbowFormat = RainbowFormat.of(phase)
}