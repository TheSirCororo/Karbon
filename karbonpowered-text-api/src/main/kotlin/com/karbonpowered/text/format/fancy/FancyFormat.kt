package com.karbonpowered.text.format.fancy

import com.karbonpowered.text.Text
import com.karbonpowered.text.builder.TextBuilder
import java.util.function.Function

interface FancyFormat : Function<TextBuilder<out Text, *>, Text> {
    fun format(text: Text): Text
}