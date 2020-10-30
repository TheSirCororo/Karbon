package com.karbonpowered.text.serializer.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.karbonpowered.text.format.NamedTextColor
import com.karbonpowered.text.format.TextColor

class TextColorSerializer(
    val downscaleColor: Boolean
) : TypeAdapter<TextColor>() {
    override fun write(out: JsonWriter, value: TextColor) {
        when {
            value is NamedTextColor -> out.value(value.name)
            downscaleColor -> out.value(NamedTextColor.nearestTo(value).name)
            else -> out.value(value.asHexString())
        }
    }

    override fun read(reader: JsonReader): TextColor {
        val value = reader.nextString()
        val color = requireNotNull(TextColor.of(value))
        return if (downscaleColor) NamedTextColor.nearestTo(color) else color
    }

    companion object {
        private val INSTANCE = TextColorSerializer(false).nullSafe()
        private val DOWNSCALE_COLOR = TextColorSerializer(true).nullSafe()

        operator fun get(downscaleColor: Boolean = false): TypeAdapter<TextColor> =
            if (downscaleColor) DOWNSCALE_COLOR else INSTANCE
    }
}