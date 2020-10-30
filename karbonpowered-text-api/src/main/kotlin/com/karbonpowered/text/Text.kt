@file:Suppress("INAPPLICABLE_JVM_NAME") // https://youtrack.jetbrains.com/issue/KT-31420
package com.karbonpowered.text

import com.karbonpowered.commons.Tristate
import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.action.*
import com.karbonpowered.text.builder.TextBuilder
import com.karbonpowered.text.builder.TextBuilderApplicable
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.TextDecoration
import com.karbonpowered.text.format.TextDecorations
import com.karbonpowered.text.format.TextStyle
import java.util.function.Consumer
import java.util.function.UnaryOperator

interface Text : TextElement, TextRepresentable, HoverActionSource<Text>, TextBuilderApplicable {
    @get:JvmName("children")
    val children: Collection<Text>

    @get:JvmName("style")
    val style: TextStyle

    @get:JvmName("color")
    val color: TextColor?
        get() = style.color

    @get:JvmName("decorations")
    val decorations: Map<TextDecoration, Tristate>
        get() = style.decorations

    @get:JvmName("obfuscated")
    val obfuscated: Tristate
        get() = style.obfuscated

    @get:JvmName("bold")
    val bold: Tristate
        get() = style.bold

    @get:JvmName("strikethrough")
    val strikethrough: Tristate
        get() = style.strikethrough

    @get:JvmName("underlined")
    val underlined: Tristate
        get() = style.underlined

    @get:JvmName("italic")
    val italic: Tristate
        get() = style.italic

    @get:JvmName("clickAction")
    val clickAction: ClickAction<*>?
        get() = style.clickAction

    @get:JvmName("hoverAction")
    val hoverAction: HoverAction<*>?
        get() = style.hoverAction

    @get:JvmName("shiftClickAction")
    val shiftClickAction: ShiftClickAction<*>?
        get() = style.shiftClickAction

    fun children(vararg children: Text): Text = children(children.asIterable())
    fun children(children: Iterable<Text>): Text

    operator fun contains(other: Text): Boolean {
        if (this == other) return true
        for (child in children) {
            if (child.contains(other)) return true
        }
        val hoverAction = hoverAction
        if (hoverAction is TextRepresentable) {
            val hoverText = hoverAction.toText()
            if (other == hoverText) return true
            for (child in hoverText.children) {
                if (child.contains(other)) return true
            }
        }
        return false
    }

    fun checkCycle(other: Text) = check(!other.contains(this)) { "Text cycle detected between $this and $other" }

    fun append(text: Text): Text
    fun append(textRepresentable: TextRepresentable): Text = append(textRepresentable.toText())
    fun append(builder: TextBuilder<*, *>): Text = append(builder.build())

    fun style(style: TextStyle): Text
    fun style(builder: Consumer<TextStyle.Builder>): Text = style(style.edit(builder))
    fun style(builder: Consumer<TextStyle.Builder>, strategy: TextStyle.Merge.Strategy): Text =
        style(style.edit(builder, strategy))

    fun style(builder: TextStyle.Builder): Text = style(builder.build())

    fun mergeStyle(text: Text): Text = mergeStyle(text, TextStyle.Merge.ALL)
    fun mergeStyle(text: Text, vararg merges: TextStyle.Merge): Text = mergeStyle(text, merges.asIterable())
    fun mergeStyle(text: Text, merges: Iterable<TextStyle.Merge>): Text = style(style.merge(text.style, merges))

    fun color(color: TextColor?): Text = style(style.color(color))
    fun colorIfAbsent(color: TextColor?): Text = if (this.color == null) color(color) else this

    fun obfuscated(flag: Boolean? = true) = decoration(TextDecorations.OBFUSCATED, flag)
    fun bold(flag: Boolean? = true) = decoration(TextDecorations.BOLD, flag)
    fun strikethrough(flag: Boolean? = true) = decoration(TextDecorations.STRIKETHROUGH, flag)
    fun underlined(flag: Boolean? = true) = decoration(TextDecorations.UNDERLINED, flag)
    fun italic(flag: Boolean? = true) = decoration(TextDecorations.ITALIC, flag)

    fun hasDecoration(decoration: TextDecoration): Boolean = decoration(decoration) == Tristate.TRUE

    fun decorate(decoration: TextDecoration): Text = decoration(decoration, Tristate.TRUE)

    fun decoration(decoration: TextDecoration): Tristate = style.decoration(decoration)
    fun decoration(decoration: TextDecoration, flag: Boolean): Text = decoration(decoration, Tristate.fromBoolean(flag))
    fun decoration(decoration: TextDecoration, flag: Boolean?): Text =
        decoration(decoration, Tristate.fromNullableBoolean(flag))

    fun decoration(decoration: TextDecoration, state: Tristate): Text = style(style.decoration(decoration, state))

    fun decorations(vararg decorations: Pair<TextDecoration, Boolean?>): Text = style(style.decorations(*decorations))
    fun decorations(decorations: Map<TextDecoration, Tristate>): Text = style(style.decorations(decorations))

    fun clickAction(action: ClickAction<*>?): Text = style(style.clickAction(action))

    fun hoverAction(action: HoverAction<*>?): Text {
        if (action != null) {
            if (action is TextRepresentable) {
                checkCycle(action.toText())
            }
        }
        return style(this.style.hoverAction(action))
    }

    fun hoverAction(source: HoverActionSource<*>): Text = hoverAction(source.asHoverAction() as HoverAction<*>?)

    fun shiftClickAction(action: ShiftClickAction<*>): Text = style(style.shiftClickAction(action))

    fun hasStyling(): Boolean = !style.isEmpty()

    override fun toText(): Text = this

    override fun asHoverAction(op: UnaryOperator<Text>): HoverAction<Text> = TextActions.showText(op.apply(this))

    override fun apply(builder: TextBuilder<*, *>) {
        builder.append(this)
    }

    interface Factory {
        fun create(vararg any: Any?): LiteralText
    }

    companion object {
        private val factory = loadService<Factory>()

        @JvmStatic
        fun empty(): LiteralText = LiteralText.empty()

        @JvmStatic
        fun newLine(): LiteralText = LiteralText.newLine()

        @JvmStatic
        fun space(): LiteralText = LiteralText.space()

        @JvmStatic
        fun of(vararg any: Any?): LiteralText = factory.create(*any)

        @JvmStatic
        fun of(content: String, color: TextColor? = null, vararg decorations: TextDecoration): LiteralText =
            LiteralText.of(content, color, *decorations)

        @JvmStatic
        fun of(content: String, color: TextColor? = null, decorations: Iterable<TextDecoration>): LiteralText =
            LiteralText.of(content, color, decorations)

        @JvmStatic
        fun of(content: String, style: TextStyle): LiteralText = LiteralText.of(content, style)
    }
}

fun Text(vararg any: Any?): LiteralText = Text.of(*any)