package com.karbonpowered.text

import com.karbonpowered.commons.Tristate
import com.karbonpowered.text.action.ClickAction
import com.karbonpowered.text.action.HoverAction
import com.karbonpowered.text.action.HoverActionSource
import com.karbonpowered.text.action.ShiftClickAction
import com.karbonpowered.text.builder.TextBuilder
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.TextDecoration
import com.karbonpowered.text.format.TextStyle
import java.util.function.Consumer

@Suppress("UNCHECKED_CAST")
interface ScopedText<T : Text> : Text {
    override fun children(vararg children: Text): T = super.children(*children) as T

    override fun children(children: Iterable<Text>): T

    override fun append(builder: TextBuilder<*, *>): T = super.append(builder) as T
    override fun append(textRepresentable: TextRepresentable): T = super.append(textRepresentable) as T

    override fun style(style: TextStyle): T

    override fun style(builder: Consumer<TextStyle.Builder>): T = super.style(builder) as T

    override fun mergeStyle(text: Text): T = super.mergeStyle(text) as T
    override fun mergeStyle(text: Text, merges: Iterable<TextStyle.Merge>): T = super.mergeStyle(text, merges) as T
    override fun mergeStyle(text: Text, vararg merges: TextStyle.Merge): T = super.mergeStyle(text, *merges) as T

    override fun append(text: Text): T {
        if (text == Text.empty()) return this as T
        checkCycle(text)
        val oldChildren = children
        val newChildren = ArrayList<Text>(oldChildren.size + 1)
        newChildren.addAll(oldChildren)
        newChildren.add(text)
        return children(newChildren)
    }

    override fun color(color: TextColor?): T = super.color(color) as T
    override fun colorIfAbsent(color: TextColor?): T = super.colorIfAbsent(color) as T

    override fun obfuscated(flag: Boolean?): T = super.obfuscated(flag) as T
    override fun bold(flag: Boolean?): T = super.bold(flag) as T
    override fun strikethrough(flag: Boolean?): T = super.strikethrough(flag) as T
    override fun underlined(flag: Boolean?): T = super.underlined(flag) as T
    override fun italic(flag: Boolean?): T = super.italic(flag) as T

    override fun decorate(decoration: TextDecoration): T = super.decorate(decoration) as T

    override fun decoration(decoration: TextDecoration, flag: Boolean): T = super.decoration(decoration, flag) as T
    override fun decoration(decoration: TextDecoration, flag: Boolean?): T = super.decoration(decoration, flag) as T
    override fun decoration(decoration: TextDecoration, state: Tristate): T = super.decoration(decoration, state) as T
    override fun decorations(decorations: Map<TextDecoration, Tristate>): T = super.decorations(decorations) as T
    override fun decorations(vararg decorations: Pair<TextDecoration, Boolean?>): T =
        super.decorations(*decorations) as T

    override fun clickAction(action: ClickAction<*>?): T = super.clickAction(action) as T

    override fun hoverAction(action: HoverAction<*>?): T = super.hoverAction(action) as T

    override fun hoverAction(source: HoverActionSource<*>): T = super.hoverAction(source) as T

    override fun shiftClickAction(action: ShiftClickAction<*>): T = super.shiftClickAction(action) as T
}