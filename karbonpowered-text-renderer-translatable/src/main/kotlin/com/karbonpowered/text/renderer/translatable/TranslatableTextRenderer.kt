package com.karbonpowered.text.renderer.translatable

import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.TranslatableText
import com.karbonpowered.text.builder.BuildableText
import com.karbonpowered.text.builder.TextBuilder
import com.karbonpowered.text.format.TextStyle
import com.karbonpowered.text.karbon.renderer.AbstractTextRenderer
import com.karbonpowered.translation.TranslationRegistry
import java.text.MessageFormat
import java.util.*

object TranslatableTextRenderer : AbstractTextRenderer<Locale>() {
    override fun renderLiteral(text: LiteralText, context: Locale): Text {
        val builder = LiteralText.builder().content(text.content)
        return deepRender(text, builder, context)
    }

    override fun renderTranslatable(text: TranslatableText, context: Locale): Text {
        val format = TranslationRegistry.translate(text.key, context)
        if (format.toPattern() == text.key) {
            val builder = LiteralText.builder().content(text.key)
            return deepRender(text, builder, context)
        }

        val args = text.args.toList()
        val builder = LiteralText.builder()
        merge(text, builder, context)

        if (args.isEmpty()) {
            return builder.content(format.format(null, StringBuffer(), null).toString()).build()
        }

        val nulls = arrayOfNulls<Any>(args.size)
        val sb = format.format(nulls, StringBuffer(), null)
        val it = format.formatToCharacterIterator(nulls)

        while (it.index < it.endIndex) {
            val end = it.runLimit
            val index = it.getAttribute(MessageFormat.Field.ARGUMENT) as? Int
            if (index != null) {
                val renderedArgument = render(args[index], context)
                builder.append(renderedArgument)
            } else {
                val translatableComponent =
                    LiteralText.of(sb.substring(it.index, end), builder.toStyleBuilder().build())
                builder.append(translatableComponent)
            }
            it.index = end
        }

        return deepRender(text, builder, context)
    }

    private fun <R : BuildableText<R, B>, B : TextBuilder<R, B>> deepRender(text: Text, builder: B, locale: Locale): R {
        merge(text, builder, locale)
        return childrenRender(text.children, builder, locale)
    }

    private fun <R : BuildableText<R, B>, B : TextBuilder<R, B>> childrenRender(
        children: Collection<Text>,
        builder: B,
        locale: Locale
    ): R {
        children.forEach {
            val rendered = render(it, locale)
            builder.append(rendered)
        }
        return builder.build()
    }

    private fun <B : TextBuilder<*, *>> merge(text: Text, builder: B, locale: Locale) {
        builder.mergeStyle(text, TextStyle.Merge.COLOR_AND_DECORATIONS)
        builder.clickAction = text.clickAction
        builder.hoverAction = text.hoverAction?.render(this, locale)
        builder.shiftClickAction = text.shiftClickAction
    }
}