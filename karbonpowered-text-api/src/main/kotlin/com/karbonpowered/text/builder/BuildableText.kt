package com.karbonpowered.text.builder

import com.karbonpowered.commons.builder.Buildable
import com.karbonpowered.text.Text

interface BuildableText<T : BuildableText<T, B>, B : TextBuilder<T, B>> : Buildable<T, B>, Text {
    override fun toBuilder(): B
}