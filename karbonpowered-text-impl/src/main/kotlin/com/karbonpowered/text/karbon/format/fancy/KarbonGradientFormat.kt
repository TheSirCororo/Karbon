package com.karbonpowered.text.karbon.format.fancy

import com.karbonpowered.text.Text
import com.karbonpowered.text.builder.TextBuilder
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.fancy.GradientFormat
import kotlin.math.roundToInt

class KarbonGradientFormat(
    phase: Float,
    override val colors: Array<out TextColor>
) : AbstractFancyFormat(), GradientFormat {
    val negativePhase = phase < 0
    override val phase: Float = phase + 1
    private var index = 0
    private var colorIndex = 0

    var factorStep = 0.0f
    var currentPhase = phase

    override fun init(size: Int) {
        val sectorLength = size / (colors.size - 1)
        factorStep = 1.0f / (sectorLength + index)
        currentPhase = phase * sectorLength
        index = 0
    }

    override fun apply(t: TextBuilder<out Text, *>): Text {
        val nextColor = nextColor()
        t.color(nextColor)
        return t.build()
    }

    private fun nextColor(): TextColor {
        if (factorStep * index > 1) {
            colorIndex++
            index = 0
        }

        var factor = factorStep * (index++ + currentPhase)
        if (factor > 1) {
            factor = 1 - (factor - 1)
        }

        return colors[colorIndex].interpolate(colors[colorIndex + 1], factor)
//
//        return if (colors.size % 2 != 0) {
//            colors[colorIndex+1].interpolate(colors[colorIndex], factor)
//        } else {
//            colors[colorIndex].interpolate(colors[colorIndex + 1], factor)
//        }
    }

    private fun TextColor.interpolate(color: TextColor, factor: Float): TextColor =
        TextColor(
            (red + factor * (color.red - red)).roundToInt(),
            (green + factor * (color.green - green)).roundToInt(),
            (blue + factor * (color.blue - blue)).roundToInt()
        )

    class Factory : GradientFormat.Factory {
        override fun create(phase: Float, vararg colors: TextColor): GradientFormat =
            KarbonGradientFormat(phase, colors)
    }
}