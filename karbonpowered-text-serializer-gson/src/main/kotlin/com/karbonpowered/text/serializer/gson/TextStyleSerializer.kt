package com.karbonpowered.text.serializer.gson

import com.google.gson.*
import com.karbonpowered.commons.Tristate
import com.karbonpowered.text.Text
import com.karbonpowered.text.action.ShiftClickAction
import com.karbonpowered.text.action.TextActions
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.TextDecorations
import com.karbonpowered.text.format.TextStyle
import java.lang.reflect.Type
import java.net.URL
import java.nio.file.Paths

object TextStyleSerializer : JsonDeserializer<TextStyle>, JsonSerializer<TextStyle> {
    private val DECORATIONS = TextDecorations.values

    const val FONT = "font"
    const val COLOR = "color"
    const val INSERTION = "insertion"
    const val CLICK_EVENT = "clickEvent"
    const val CLICK_EVENT_ACTION = "action"
    const val CLICK_EVENT_VALUE = "value"
    const val HOVER_EVENT = "hoverEvent"
    const val HOVER_EVENT_ACTION = "action"
    const val HOVER_EVENT_CONTENTS = "contents"

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): TextStyle {
        return deserialize(json.asJsonObject, context)
    }

    private fun deserialize(json: JsonObject, context: JsonDeserializationContext): TextStyle {
        val style = TextStyle.builder()

        if (json.has(FONT)) {
            //TODO
        }

        if (json.has(COLOR)) {
            val color = context.deserialize<TextColor>(json.get(COLOR), TextColor::class.java)
            style.color(color)
        }

        for (decoration in DECORATIONS) {
            val name = decoration.name
            if (json.has(name)) {
                style.decoration(decoration, json.get(name).asBoolean)
            }
        }

        if (json.has(INSERTION)) {
            val value = json.get(INSERTION).asString
            style.shiftClickAction(TextActions.insertText(value))
        }

        if (json.has(CLICK_EVENT)) {
            val clickEvent = json.getAsJsonObject(CLICK_EVENT)
            if (clickEvent != null) {
                val rawAction = clickEvent.getAsJsonPrimitive(CLICK_EVENT_ACTION)
                val rawValue = clickEvent.getAsJsonPrimitive(CLICK_EVENT_VALUE)
                if (rawAction != null && rawValue != null) {
                    val action = when (rawAction.asString) {
                        "open_url" -> TextActions.openUrl(URL(rawValue.asString))
                        "open_file" -> TextActions.openFile(Paths.get(rawValue.asString))
                        "run_command" -> TextActions.runCommand(rawValue.asString)
                        "suggest_command" -> TextActions.suggestCommand(rawValue.asString)
                        "change_page" -> TextActions.changePage(rawValue.asString.toIntOrNull() ?: 0)
                        "copy_to_clipboard" -> TextActions.copyToClipboard(rawValue.asString)
                        else -> null
                    }
                    style.clickAction(action)
                }
            }
        }

        if (json.has(HOVER_EVENT)) {
            val hoverEvent = json.getAsJsonObject(HOVER_EVENT)
            if (hoverEvent != null) {
                val rawAction = hoverEvent.getAsJsonPrimitive(HOVER_EVENT_ACTION)
                val rawContents = hoverEvent.get(HOVER_EVENT_CONTENTS)
                if (rawAction != null && rawContents != null) {
                    val action = when (rawAction.asString) {
                        "show_text" -> TextActions.showText(context.deserialize(rawContents, Text::class.java))
                        else -> null
                    }
                    style.hoverAction(action)
                }
            }
        }

        return style.build()
    }

    override fun serialize(src: TextStyle, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val json = JsonObject()

        val color = src.color
        if (color != null) {
            json.add(COLOR, context.serialize(color))
        }

        for (decoration in DECORATIONS) {
            val state = src.decoration(decoration)
            if (state != Tristate.UNDEFINED) {
                val name = decoration.name
                json.addProperty(name, state == Tristate.TRUE)
            }
        }

        val shiftClickAction = src.shiftClickAction
        if (shiftClickAction != null) {
            if (shiftClickAction is ShiftClickAction.InsertText) {
                json.addProperty(INSERTION, shiftClickAction.result)
            }
        }

        val clickAction = src.clickAction
        if (clickAction != null) {
            val eventJson = JsonObject()
            eventJson.addProperty(CLICK_EVENT_ACTION, clickAction.name)
            eventJson.addProperty(CLICK_EVENT_VALUE, clickAction.result.toString())
            json.add(CLICK_EVENT, eventJson)
        }

        val hoverAction = src.hoverAction
        if (hoverAction != null) {
            val eventJson = JsonObject()
            eventJson.addProperty(HOVER_EVENT_ACTION, hoverAction.name)
            val contents = context.serialize(hoverAction.result)
            eventJson.add(HOVER_EVENT_CONTENTS, contents)
            json.add(HOVER_EVENT, eventJson)
        }

        return json
    }
}