package com.karbonpowered.text.karbon

import com.karbonpowered.text.Text
import com.karbonpowered.text.format.TextStyle

abstract class KarbonAbstractText(
    override val children: Collection<Text>,
    override val style: TextStyle
) : Text {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonAbstractText

        if (children != other.children) return false
        if (style != other.style) return false

        return true
    }

    override fun hashCode(): Int {
        var result = children.hashCode()
        result = 31 * result + style.hashCode()
        return result
    }
}