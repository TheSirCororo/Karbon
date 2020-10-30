package com.karbonpowered.text.format

object TextColors {
    @JvmField
    val BLACK = NamedTextColor.of("black")!!

    @JvmField
    val DARK_BLUE = NamedTextColor.of("dark_blue")!!

    @JvmField
    val DARK_GREEN = NamedTextColor.of("dark_green")!!

    @JvmField
    val DARK_AQUA = NamedTextColor.of("dark_aqua")!!

    @JvmField
    val DARK_RED = NamedTextColor.of("dark_red")!!

    @JvmField
    val DARK_PURPLE = NamedTextColor.of("dark_purple")!!

    @JvmField
    val GOLD = NamedTextColor.of("gold")!!

    @JvmField
    val GRAY = NamedTextColor.of("gray")!!

    @JvmField
    val DARK_GRAY = NamedTextColor.of("dark_gray")!!

    @JvmField
    val BLUE = NamedTextColor.of("blue")!!

    @JvmField
    val GREEN = NamedTextColor.of("green")!!

    @JvmField
    val AQUA = NamedTextColor.of("aqua")!!

    @JvmField
    val RED = NamedTextColor.of("red")!!

    @JvmField
    val LIGHT_PURPLE = NamedTextColor.of("light_purple")!!

    @JvmField
    val YELLOW = NamedTextColor.of("yellow")!!

    @JvmField
    val WHITE = NamedTextColor.of("white")!!

    @JvmStatic
    @get:JvmName("values")
    val values
        get() = NamedTextColor.values
}