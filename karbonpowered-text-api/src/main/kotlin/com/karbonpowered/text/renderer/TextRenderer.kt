package com.karbonpowered.text.renderer

import com.karbonpowered.text.Text

fun interface TextRenderer<C> {
    fun render(text: Text, context: C): Text
}