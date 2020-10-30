package com.karbonpowered.text.karbon

import com.karbonpowered.commons.Tristate
import com.karbonpowered.text.Text
import com.karbonpowered.text.TextRepresentable
import com.karbonpowered.text.TranslatableText
import com.karbonpowered.text.action.ClickAction
import com.karbonpowered.text.action.HoverAction
import com.karbonpowered.text.action.ShiftClickAction
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.TextStyle

data class KarbonTranslatableText(
    override val key: String,
    override val args: Collection<Text> = emptyList(),
    override val children: Collection<Text> = emptyList(),
    override val style: TextStyle = TextStyle.empty(),
) : KarbonAbstractText(children, style), TranslatableText {
    override fun children(vararg children: Text): TranslatableText = copy(children = children.toList())

    override fun children(children: Iterable<Text>): TranslatableText = copy(children = children.toList())

    override fun style(style: TextStyle): TranslatableText = copy(style = style)

    class Factory : TranslatableText.Factory {
        override fun builder(): TranslatableText.Builder = Builder()
    }

    data class Builder(
        override var key: String? = null,
        override var args: MutableCollection<TextRepresentable> = ArrayList(),
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
    ) : TranslatableText.Builder {
        override fun from(value: Text): TranslatableText.Builder =
            if (value is TranslatableText) from(value) else super.from(value)

        override fun build(): TranslatableText {
            val style = toStyleBuilder().build()
            return KarbonTranslatableText(key ?: "", args.map { it.toText() }, children.toList(), style)
        }
    }
}