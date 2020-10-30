package com.karbonpowered.text.karbon.format

import com.karbonpowered.text.format.TextColor

data class KarbonTextColor(override val value: Int) : TextColor {
    override fun toString(): String = asHexString()

    class Factory : TextColor.Factory {
        override fun create(value: Int): TextColor = KarbonTextColor(value)
    }
}