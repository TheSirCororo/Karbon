package com.karbonpowered.text.builder

import com.karbonpowered.commons.Tristate
import com.karbonpowered.commons.builder.CopyableBuilder
import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.TextRepresentable
import com.karbonpowered.text.action.ClickAction
import com.karbonpowered.text.action.HoverAction
import com.karbonpowered.text.action.ShiftClickAction
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.TextDecoration
import com.karbonpowered.text.format.TextDecorations
import com.karbonpowered.text.format.TextStyle
import java.util.function.Consumer

@Suppress("UNCHECKED_CAST")
interface TextBuilder<T : Text, B : CopyableBuilder<T, B>> : CopyableBuilder<T, B>, TextRepresentable,
    TextStyle.BuilderApplicable {
    var children: MutableCollection<Text>
    var color: TextColor?
    var obfuscated: Tristate
    var bold: Tristate
    var strikethrough: Tristate
    var underlined: Tristate
    var italic: Tristate
    var clickAction: ClickAction<*>?
    var hoverAction: HoverAction<*>?
    var shiftClickAction: ShiftClickAction<*>?

    fun append(text: Text): B = apply {
        children.add(text)
    } as B

    fun append(vararg texts: Text): B = append(texts.asIterable())

    fun append(texts: Iterable<Text>): B = apply {
        texts.forEach { append(it) }
    } as B

    fun append(content: String): B = append(LiteralText.of(content))

    fun append(content: String, style: TextStyle): B = append(LiteralText.of(content, style))

    fun append(content: String, color: TextColor): B = append(LiteralText.of(content, color))

    fun append(content: String, color: TextColor, vararg decorations: TextDecoration): B =
        append(content, color, decorations.asIterable())

    fun append(content: String, color: TextColor, decorations: Iterable<TextDecoration>): B =
        append(LiteralText.of(content, color, decorations))

    override fun apply(builder: TextStyle.Builder) {
        color = builder.color
        italic = builder.italic
        bold = builder.bold
        strikethrough = builder.strikethrough
        obfuscated = builder.obfuscated
        underlined = builder.underlined
        hoverAction = builder.hoverAction
        clickAction = builder.clickAction
        shiftClickAction = builder.shiftClickAction
    }

    fun mergeStyle(text: Text): B = apply {
        mergeStyle(text, TextStyle.Merge.ALL)
    } as B

    fun mergeStyle(text: Text, merges: Iterable<TextStyle.Merge>): B = apply {
        val style = toStyleBuilder().merge(text.style, merges)
        style.apply(this)
    } as B

    fun toStyleBuilder(): TextStyle.Builder = TextStyle.builder()
        .color(color)
        .decoration(TextDecorations.OBFUSCATED, obfuscated)
        .decoration(TextDecorations.BOLD, bold)
        .decoration(TextDecorations.STRIKETHROUGH, strikethrough)
        .decoration(TextDecorations.UNDERLINED, underlined)
        .decoration(TextDecorations.ITALIC, italic)
        .clickAction(clickAction)
        .hoverAction(hoverAction)
        .shiftClickAction(shiftClickAction)

    fun style(builder: Consumer<TextStyle.Builder>) {
        builder.accept(TextStyle.builder())
    }

    fun style(style: TextStyle): B = apply {
        color = style.color
        italic = style.italic
        bold = style.bold
        underlined = style.underlined
        strikethrough = style.strikethrough
        obfuscated = style.obfuscated
        clickAction = style.clickAction
        hoverAction = style.hoverAction
        shiftClickAction = style.shiftClickAction
    } as B

    fun color(color: TextColor?): B = apply {
        this.color = color
    } as B

    fun decorations(decorations: Iterable<TextDecoration>, flag: Boolean): B = apply {
        val state = Tristate.fromBoolean(flag)
        decorations.forEach { decoration(it, state) }
    } as B

    fun decoration(decoration: TextDecoration, state: Tristate): B = apply {
        when (decoration) {
            TextDecorations.ITALIC -> italic = state
            TextDecorations.BOLD -> bold = state
            TextDecorations.OBFUSCATED -> obfuscated = state
            TextDecorations.UNDERLINED -> underlined = state
            TextDecorations.STRIKETHROUGH -> strikethrough = state
        }
    } as B

    fun clickAction(action: ClickAction<*>): B = apply {
        clickAction = action
    } as B

    fun hoverAction(action: HoverAction<*>): B = apply {
        hoverAction = action
    } as B

    fun shiftClickAction(action: ShiftClickAction<*>): B = apply {
        shiftClickAction = action
    } as B

    fun from(value: Text): B = apply {
        children = ArrayList(value.children)
        color = value.color
        italic = value.style.italic
        bold = value.style.bold
        obfuscated = value.style.obfuscated
        underlined = value.style.underlined
        strikethrough = value.style.strikethrough
        clickAction = value.clickAction
        hoverAction = value.hoverAction
        shiftClickAction = value.shiftClickAction
    } as B

//    override fun from(value: T): B = apply {
//        children = ArrayList(value.children)
//        color = value.color
//        clickAction = value.clickAction
//        hoverAction = value.hoverAction
//        shiftClickAction = value.shiftClickAction
//    } as B

    override fun reset(): B = apply {
        children = ArrayList()
        color = null
        clickAction = null
        hoverAction = null
        shiftClickAction = null
    } as B

    override fun apply(builder: TextBuilder<*, *>) {
        builder.apply(this)
    }

    override fun toText(): Text = build()
}