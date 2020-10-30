package com.karbonpowered.text.karbon.format.fancy

import com.karbonpowered.text.Text
import com.karbonpowered.text.builder.TextBuilder
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.fancy.RainbowFormat
import kotlin.math.sin

class KarbonRainbowFormat(
    override val phase: Int,
    override val width: Int,
    override val center: Int
) : AbstractFancyFormat(), RainbowFormat {
    var frequency = 1.0
    var colorIndex = 0

    override fun init(size: Int) {
        frequency = Math.PI * 2 / size
    }

    override fun apply(t: TextBuilder<out Text, *>): Text {
        val nextColor = nextColor()
        t.color(nextColor)
        return t.build()
    }

    private fun nextColor(): TextColor {
        val index = colorIndex++
        val red = (sin(frequency * index + 2 + phase) * width + center).toInt()
        val green = (sin(frequency * index + 0 + phase) * width + center).toInt()
        val blue = (sin(frequency * index + 4 + phase) * width + center).toInt()
        return TextColor(red, green, blue)
    }

    class Factory : RainbowFormat.Factory {
        override fun create(phase: Int, width: Int, center: Int): RainbowFormat =
            KarbonRainbowFormat(phase, width, center)
    }
}