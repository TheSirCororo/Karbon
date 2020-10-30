@file:Suppress("INAPPLICABLE_JVM_NAME") // https://youtrack.jetbrains.com/issue/KT-31420
package com.karbonpowered.text.format

import com.karbonpowered.commons.lang.loadService
import java.awt.Color

interface TextColor : Comparable<TextColor>, TextFormat, TextStyle.BuilderApplicable {
    val value: Int

    val red: Int
        @JvmName("red")
        get() = (value shr 16) and 0xff

    val green: Int
        @JvmName("green")
        get() = (value shr 8) and 0xff

    val blue: Int
        @JvmName("blue")
        get() = value and 0xff

    fun asHexString(): String = "#%06x".format(value)

    fun asColor(): Color = Color(value)

    override fun apply(builder: TextStyle.Builder) {
        builder.color(this)
    }

    override fun compareTo(other: TextColor): Int = value.compareTo(other.value)

    interface Factory {
        fun create(value: Int): TextColor
    }

    companion object {
        private val factory by lazy { loadService<Factory>() }

        @JvmStatic
        fun of(value: Int): TextColor = NamedTextColor.of(value) ?: factory.create(value)

        @JvmStatic
        fun of(color: Color): TextColor = of(color.red, color.green, color.blue)

        @JvmStatic
        fun of(red: Int, green: Int, blue: Int): TextColor =
            of(((red and 0xff) shl 16) or ((green and 0xff) shl 8) or (blue and 0xff))

        @JvmStatic
        fun of(red: Float, green: Float, blue: Float): TextColor =
            of((red * 0xff).toInt(), (green * 0xff).toInt(), (blue * 0xff).toInt())

        @JvmStatic
        fun fromHexString(value: String): TextColor? {
            if (value.length != 4 && value.length != 7) return null
            if (value.startsWith("#")) {
                val hexString = value.substring(1)
                val hex = try {
                    Integer.parseInt(hexString, 16)
                } catch (e: NumberFormatException) {
                    null
                } ?: return null
                return if (hexString.length == 6) {
                    of(hex)
                } else {
                    val red = ((hex and 0xf00) shr 8) or ((hex and 0xf00) shr 4)
                    val green = ((hex and 0x0f0) shr 4) or ((hex and 0x0f0))
                    val blue = ((hex and 0x00f) shl 4) or ((hex and 0x00f))
                    of(red, green, blue)
                }
            } else return null
        }

        @JvmStatic
        fun of(value: String): TextColor? = fromHexString(value) ?: NamedTextColor.of(value)
    }
}

fun TextColor(value: Int): TextColor = TextColor.of(value)
fun TextColor(color: Color): TextColor = TextColor.of(color)
fun TextColor(red: Int, green: Int, blue: Int): TextColor = TextColor.of(red, green, blue)
fun TextColor(red: Float, green: Float, blue: Float): TextColor = TextColor.of(red, green, blue)
fun TextColor(value: String): TextColor? = TextColor.of(value)
