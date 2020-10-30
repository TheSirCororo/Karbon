package com.karbonpowered.text.serializer.gson

import com.google.gson.*
import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.TranslatableText
import com.karbonpowered.text.builder.BuildableText
import com.karbonpowered.text.builder.TextBuilder
import com.karbonpowered.text.format.TextStyle
import java.lang.reflect.Type

object TextSerializer : JsonDeserializer<Text>, JsonSerializer<Text> {
    const val TEXT = "text"
    const val TRANSLATE = "translate"
    const val TRANSLATE_WITH = "with"
    const val SCORE = "score"
    const val SCORE_NAME = "name"
    const val SCORE_OBJECTIVE = "objective"
    const val SCORE_VALUE = "value"
    const val SELECTOR = "selector"
    const val KEYBIND = "keybind"
    const val EXTRA = "extra"
    const val NBT = "nbt"
    const val NBT_INTERPRET = "interpret"
    const val NBT_BLOCK = "block"
    const val NBT_ENTITY = "entity"
    const val NBT_STORAGE = "storage"

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Text {
        return deserialize0(json, context)
    }

    private fun deserialize0(element: JsonElement, context: JsonDeserializationContext): BuildableText<*, *> {
        if (element.isJsonPrimitive) {
            return LiteralText.of(element.asString)
        } else if (element.isJsonArray) {
            var parent: TextBuilder<*, *>? = null
            for (childElement in element.asJsonArray) {
                val child = deserialize0(childElement, context)
                if (parent == null) {
                    parent = child.toBuilder()
                } else {
                    parent.append(child)
                }
            }
            if (parent == null) {
                deserializeError(element)
            }
        } else if (!element.isJsonObject) {
            deserializeError(element)
        }

        val json = element.asJsonObject
        val textBuilder = when {
            json.has(TEXT) -> LiteralText.builder().content(json.get(TEXT).asString)
            json.has(TRANSLATE) -> TranslatableText.builder().key(json.get(TRANSLATE).asString).apply {
                val withArray = json.getAsJsonArray(TRANSLATE_WITH)
                if (withArray != null) {
                    args(withArray.map { element ->
                        deserialize0(element, context)
                    })
                }
            }
            json.has(SCORE) -> TODO()
            json.has(SELECTOR) -> TODO()
            json.has(KEYBIND) -> TODO()
            json.has(NBT) -> TODO()
            else -> deserializeError(json)
        }

        if (json.has(EXTRA)) {
            val extra = json.getAsJsonArray(EXTRA)
            for (extraElement in extra) {
                val extraText = deserialize0(extraElement, context)
                textBuilder.append(extraText)
            }
        }

        val style = context.deserialize<TextStyle>(element, TextStyle::class.java)
        if (!style.isEmpty()) {
            textBuilder.style(style)
        }

        return textBuilder.build()
    }

    override fun serialize(src: Text, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val json = JsonObject()
        when (src) {
            is LiteralText -> json.addProperty(TEXT, src.content)
            is TranslatableText -> {
                json.addProperty(TRANSLATE, src.key)
                if (src.args.isNotEmpty()) {
                    val withArray = JsonArray()
                    src.args.forEach { arg ->
                        withArray.add(context.serialize(arg))
                    }
                    json.add(TRANSLATE_WITH, withArray)
                }
            }
            else -> TODO()
        }

        val children = src.children
        if (!children.isEmpty()) {
            val extra = JsonArray()
            for (child in children) {
                extra.add(context.serialize(child))
            }
            json.add(EXTRA, extra)
        }

        if (src.hasStyling()) {
            val style = context.serialize(src.style)
            if (style is JsonObject) {
                style.entrySet().forEach { (key, value) ->
                    json.add(key, value)
                }
            }
        }

        return json
    }

    private fun deserializeError(element: JsonElement): Nothing =
        throw JsonParseException("Can't deserialize '$element' as Text")
}