package com.karbonpowered.text.karbon.renderer

import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.TranslatableText
import com.karbonpowered.text.renderer.TextRenderer

abstract class AbstractTextRenderer<C> : TextRenderer<C> {
    override fun render(text: Text, context: C): Text = when (text) {
        is LiteralText -> renderLiteral(text, context)
        is TranslatableText -> renderTranslatable(text, context)
        else -> text
    }

    abstract fun renderLiteral(text: LiteralText, context: C): Text

    abstract fun renderTranslatable(text: TranslatableText, context: C): Text
}