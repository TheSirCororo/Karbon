package com.karbonpowered.text.karbon

import com.karbonpowered.commons.Tristate
import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.action.ClickAction
import com.karbonpowered.text.action.HoverAction
import com.karbonpowered.text.action.ShiftClickAction
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.TextStyle
import java.util.*
import java.util.function.BiFunction
import java.util.function.UnaryOperator
import java.util.regex.Pattern
import kotlin.collections.ArrayList

data class KarbonLiteralText(
    override val content: String,
    override val children: Collection<Text> = emptyList(),
    override val style: TextStyle = TextStyle.empty(),
) : KarbonAbstractText(children, style), LiteralText {
    override fun toString(): String {
        val sb = StringBuilder("LiteralText(")
        val list = ArrayList<String>()
        if (style.isNotEmpty()) list.add(style.toString())
        if (content.isNotEmpty()) list.add("'$content'")
        if (children.isNotEmpty()) list.addAll(children.map { it.toString() })
        sb.append(list.joinToString(", "))
        sb.append(")")
        return sb.toString()
    }

    override fun content(content: String): LiteralText = copy(content = content)

    override fun replace(
        pattern: Pattern,
        replacement: UnaryOperator<LiteralText.Builder>,
        function: BiFunction<Int, Int, LiteralText.PatternReplacementResult>
    ): LiteralText {
        val produced = ArrayList<Text>()
        val queue = ArrayDeque<LiteralText>()
        queue.add(this)

        var index = 0
        var replaced = 0

        while (!queue.isEmpty()) {
            val current = queue.remove()
            val content = current.content
            val matcher = pattern.matcher(content)
            val withoutChildren = current.children(emptyList())

            if (matcher.find()) {
                var lastEnd = 0
                var running = true
                do {
                    index++

                    val result = function.apply(index, replaced)
                    if (result == LiteralText.PatternReplacementResult.REPLACE) {
                        replaced++
                        val start = matcher.start()
                        val end = matcher.end()
                        val matched = matcher.group()

                        val prefix = content.substring(lastEnd, start)
                        if (prefix.isNotEmpty()) {
                            produced.add(withoutChildren.content(prefix))
                        }

                        produced.add(replacement.apply(withoutChildren.toBuilder().content(matched)).build())
                        lastEnd = end
                    } else if (result == LiteralText.PatternReplacementResult.STOP) {
                        running = false
                    }
                } while (running && matcher.find())

                if (content.length - lastEnd > 0) {
                    produced.add(withoutChildren.content(content.substring(lastEnd)))
                }
            } else {
                produced.add(withoutChildren)
            }

            for (child in current.children) {
                if (child is LiteralText) {
                    queue.add(child)
                } else {
                    produced.add(child)
                }
            }
        }

        return if (produced.size == 1) {
            produced.first()
        } else {
            val children = produced.subList(1, produced.size)
            produced.first().children(children)
        } as LiteralText
    }

    override fun children(vararg children: Text): LiteralText = copy(children = children.toList())

    override fun children(children: Iterable<Text>): LiteralText = copy(children = children.toList())

    override fun style(style: TextStyle): LiteralText = copy(style = style)

    class Factory : LiteralText.Factory {
        val EMPTY = KarbonLiteralText("")
        val NEWLINE = KarbonLiteralText("\n")
        val SPACE = KarbonLiteralText(" ")

        override fun empty(): LiteralText = EMPTY

        override fun newLine(): LiteralText = NEWLINE

        override fun space(): LiteralText = SPACE

        override fun builder(): LiteralText.Builder = Builder()

        override fun of(content: String, style: TextStyle): LiteralText =
            KarbonLiteralText(content = content, style = style)
    }

    data class Builder(
        override var content: String? = null,
        override var children: MutableCollection<Text> = ArrayList(),
        override var color: TextColor? = null,
        override var obfuscated: Tristate = Tristate.UNDEFINED,
        override var bold: Tristate = Tristate.UNDEFINED,
        override var strikethrough: Tristate = Tristate.UNDEFINED,
        override var underlined: Tristate = Tristate.UNDEFINED,
        override var italic: Tristate = Tristate.UNDEFINED,
        override var clickAction: ClickAction<*>? = null,
        override var hoverAction: HoverAction<*>? = null,
        override var shiftClickAction: ShiftClickAction<*>? = null,
    ) : LiteralText.Builder {
        override fun from(value: Text): LiteralText.Builder =
            if (value is LiteralText) from(value) else super.from(value)

        @Suppress("UsePropertyAccessSyntax", "DEPRECATION")
        override fun build(): LiteralText {
            val style = toStyleBuilder().build()
            val content = content ?: ""
            if (content.isEmpty() && children.isNotEmpty()) {
                val first = children.first()
                if (first is LiteralText) {
                    val subChildren = first.children + children.toList().subList(1, children.size)
                    return first.children(subChildren)
                }
            }
            return KarbonLiteralText(content, children, style)
        }
    }
}