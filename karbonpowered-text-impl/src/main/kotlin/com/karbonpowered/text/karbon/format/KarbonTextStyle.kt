package com.karbonpowered.text.karbon.format

import com.karbonpowered.commons.Tristate
import com.karbonpowered.text.action.ClickAction
import com.karbonpowered.text.action.HoverAction
import com.karbonpowered.text.action.ShiftClickAction
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.TextStyle

data class KarbonTextStyle(
    override val color: TextColor?,
    override val obfuscated: Tristate,
    override val bold: Tristate,
    override val strikethrough: Tristate,
    override val underlined: Tristate,
    override val italic: Tristate,
    override val clickAction: ClickAction<*>?,
    override val hoverAction: HoverAction<*>?,
    override val shiftClickAction: ShiftClickAction<*>?
) : TextStyle {
    override fun toString(): String {
        val sb = StringBuilder("TextStyle(")
        val list = ArrayList<String>()
        if (color != null) list.add(color.toString())
        if (obfuscated != Tristate.UNDEFINED) list.add("obfuscated=${obfuscated.asBoolean()}")
        if (bold != Tristate.UNDEFINED) list.add("bold=${bold.asBoolean()}")
        if (strikethrough != Tristate.UNDEFINED) list.add("strikethrough=${strikethrough.asBoolean()}")
        if (underlined != Tristate.UNDEFINED) list.add("underlined=${underlined.asBoolean()}")
        if (italic != Tristate.UNDEFINED) list.add("italic=${italic.asBoolean()}")
        if (clickAction != null) list.add("clickAction=$clickAction")
        if (hoverAction != null) list.add("hoverAction=$hoverAction")
        if (shiftClickAction != null) list.add("shiftClickAction=$shiftClickAction")
        sb.append(list.joinToString(", "))
        sb.append(")")
        return sb.toString()
    }

    data class Builder(
        override var color: TextColor? = null,
        override var obfuscated: Tristate = Tristate.UNDEFINED,
        override var bold: Tristate = Tristate.UNDEFINED,
        override var strikethrough: Tristate = Tristate.UNDEFINED,
        override var underlined: Tristate = Tristate.UNDEFINED,
        override var italic: Tristate = Tristate.UNDEFINED,
        override var clickAction: ClickAction<*>? = null,
        override var hoverAction: HoverAction<*>? = null,
        override var shiftClickAction: ShiftClickAction<*>? = null
    ) : TextStyle.Builder {
        override fun build(): TextStyle = KarbonTextStyle(
            color = color,
            obfuscated = obfuscated,
            bold = bold,
            strikethrough = strikethrough,
            underlined = underlined,
            italic = italic,
            clickAction = clickAction,
            hoverAction = hoverAction,
            shiftClickAction = shiftClickAction
        )

        class Provider : TextStyle.Builder.Provider {
            override fun provide(): TextStyle.Builder = Builder()
        }
    }
}