package com.karbonpowered.text.format

object TextDecorations {
    @JvmField
    val OBFUSCATED = TextDecoration.of("obfuscated")!!

    @JvmField
    val BOLD = TextDecoration.of("bold")!!

    @JvmField
    val STRIKETHROUGH = TextDecoration.of("strikethrough")!!

    @JvmField
    val UNDERLINED = TextDecoration.of("underlined")!!

    @JvmField
    val ITALIC = TextDecoration.of("italic")!!

    @JvmStatic
    @get:JvmName("values")
    val values
        get() = TextDecoration.values
}