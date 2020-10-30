package com.karbonpowered.text

import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.builder.BuildableText
import com.karbonpowered.text.builder.TextBuilder
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.TextDecoration
import com.karbonpowered.text.format.TextStyle
import java.util.function.BiFunction
import java.util.function.UnaryOperator
import java.util.regex.Pattern

interface LiteralText : BuildableText<LiteralText, LiteralText.Builder>, ScopedText<LiteralText> {
    val content: String

    fun content(content: String): LiteralText

    fun replace(pattern: Pattern, replacement: UnaryOperator<Builder>): LiteralText =
        replace(pattern, replacement, { _, _ -> PatternReplacementResult.REPLACE })

    fun replaceFirst(pattern: Pattern, replacement: UnaryOperator<Builder>): LiteralText =
        replace(pattern, replacement, 1)

    fun replace(pattern: Pattern, replacement: UnaryOperator<Builder>, numberOfReplacements: Int) =
        replace(
            pattern,
            replacement,
            { _, replaced -> if (replaced < numberOfReplacements) PatternReplacementResult.REPLACE else PatternReplacementResult.STOP })

    fun replace(
        pattern: Pattern,
        replacement: UnaryOperator<Builder>,
        function: BiFunction<Int, Int, PatternReplacementResult>
    ): LiteralText

    override fun toBuilder(): Builder = builder().from(this)

    interface Factory {
        fun empty(): LiteralText

        fun newLine(): LiteralText

        fun space(): LiteralText

        fun builder(): Builder

        fun of(content: String, style: TextStyle): LiteralText
    }

    interface Builder : TextBuilder<LiteralText, Builder> {
        var content: String?

        fun content(content: String): Builder = apply {
            this.content = content
        }

        override fun from(value: LiteralText): Builder = apply {
            content = value.content
            super.from(value)
        }

        override fun reset(): Builder = apply {
            content = null
            super.reset()
        }
    }

    enum class PatternReplacementResult {
        REPLACE, CONTINUE, STOP
    }

    companion object {
        private val factory = loadService<Factory>()

        @JvmStatic
        fun empty(): LiteralText = factory.empty()

        @JvmStatic
        fun newLine(): LiteralText = factory.newLine()

        @JvmStatic
        fun space(): LiteralText = factory.space()

        @JvmStatic
        fun builder(): Builder = factory.builder()

        @JvmStatic
        fun of(vararg texts: Text): LiteralText = of(texts.asIterable())

        @JvmStatic
        fun of(texts: Iterable<Text>): LiteralText {
            val iterator = texts.iterator()
            return if (!iterator.hasNext()) {
                empty()
            } else {
                val first = iterator.next()
                if (first is LiteralText && !iterator.hasNext()) {
                    first
                } else {
                    factory.empty().children(texts)
                }
            }
        }

        @JvmStatic
        fun of(content: String, color: TextColor? = null): LiteralText = factory.of(content, TextStyle {
            color(color)
        })

        @JvmStatic
        fun of(content: String, color: TextColor? = null, vararg decorations: TextDecoration): LiteralText =
            of(content, color, decorations.asIterable())

        @JvmStatic
        fun of(content: String, color: TextColor? = null, decorations: Iterable<TextDecoration>): LiteralText =
            factory.of(content, TextStyle {
                this.color = color
                decorate(decorations)
            })

        @JvmStatic
        fun of(content: String, style: TextStyle): LiteralText = factory.of(content, style)
    }
}

fun LiteralText(builder: LiteralText.Builder.() -> Unit): LiteralText = LiteralText.builder().apply(builder).build()