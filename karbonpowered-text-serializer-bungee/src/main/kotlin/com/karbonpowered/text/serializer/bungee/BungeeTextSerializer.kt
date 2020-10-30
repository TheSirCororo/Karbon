package com.karbonpowered.text.serializer.bungee

import com.karbonpowered.commons.Tristate
import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.TranslatableText
import com.karbonpowered.text.action.ClickAction
import com.karbonpowered.text.action.HoverAction
import com.karbonpowered.text.action.ShiftClickAction
import com.karbonpowered.text.action.TextActions
import com.karbonpowered.text.builder.TextBuilder
import com.karbonpowered.text.serializer.TextSerializer
import net.md_5.bungee.api.chat.*
import java.net.URL
import java.nio.file.FileSystems

object BungeeTextSerializer : TextSerializer<Text, Text, Array<out BaseComponent>> {
    override fun deserialize(input: Array<out BaseComponent>): Text {
        val list = input.asSequence().filterNotNull().map { it.asKarbon() }.toList()
        return when (list.size) {
            0 -> Text.empty()
            1 -> list.first()
            else -> {
                val children = list.subList(1, list.size)
                list.first().children(children)
            }
        }
    }

    fun deserialize(component: BaseComponent): Text {
        val builder: TextBuilder<*, *> = when (component) {
            is TextComponent -> LiteralText.builder().content(component.text)
            is TranslatableComponent -> TranslatableText.builder().key(component.translate)
                .args(component.with.map { it.asKarbon() })
            else -> TODO()
        }
        builder.append(component.extra?.map { it.asKarbon() } ?: emptyList())
        builder.color = component.colorRaw?.asKarbon()
        builder.bold = Tristate.fromNullableBoolean(component.isBoldRaw)
        builder.italic = Tristate.fromNullableBoolean(component.isItalicRaw)
        builder.underlined = Tristate.fromNullableBoolean(component.isUnderlinedRaw)
        builder.strikethrough = Tristate.fromNullableBoolean(component.isStrikethroughRaw)
        builder.obfuscated = Tristate.fromNullableBoolean(component.isObfuscatedRaw)
        builder.clickAction = component.clickEvent?.let { clickEvent ->
            val value = clickEvent.value
            when (clickEvent.action!!) {
                ClickEvent.Action.OPEN_URL -> TextActions.openUrl(URL(value))
                ClickEvent.Action.OPEN_FILE -> TextActions.openFile(FileSystems.getDefault().getPath(value))
                ClickEvent.Action.RUN_COMMAND -> TextActions.runCommand(value)
                ClickEvent.Action.SUGGEST_COMMAND -> TextActions.suggestCommand(value)
                ClickEvent.Action.CHANGE_PAGE -> TextActions.changePage(value.toInt())
                ClickEvent.Action.COPY_TO_CLIPBOARD -> TextActions.copyToClipboard(value)
            }
        }
        builder.hoverAction = component.hoverEvent?.let { hoverEvent ->
            when (hoverEvent.action!!) {
                HoverEvent.Action.SHOW_TEXT -> TextActions.showText(deserialize(hoverEvent.value))
                HoverEvent.Action.SHOW_ACHIEVEMENT -> TextActions.showText(deserialize(hoverEvent.value))
                HoverEvent.Action.SHOW_ITEM -> TODO()
                HoverEvent.Action.SHOW_ENTITY -> TODO()
            }
        }
        builder.shiftClickAction = component.insertion?.let { TextActions.insertText(it) }
        return builder.build()
    }

    override fun serialize(text: Text): Array<BaseComponent> {
        val builder: BaseComponent = when (text) {
            is LiteralText -> TextComponent(text.content)
            is TranslatableText -> TranslatableComponent(text.key).apply {
                with = text.args.map { it.asBungee().first() }
            }
            else -> TODO()
        }
        builder.color = text.color?.asBungee()
        if (text.children.isNotEmpty()) builder.extra = text.children.map { it.asBungee().first() }
        if (text.obfuscated != Tristate.UNDEFINED) builder.isObfuscated = text.obfuscated.asBoolean()
        if (text.bold != Tristate.UNDEFINED) builder.isBold = text.bold.asBoolean()
        if (text.strikethrough != Tristate.UNDEFINED) builder.isStrikethrough = text.strikethrough.asBoolean()
        if (text.underlined != Tristate.UNDEFINED) builder.isUnderlined = text.underlined.asBoolean()
        if (text.italic != Tristate.UNDEFINED) builder.isItalic = text.italic.asBoolean()
        builder.clickEvent = text.clickAction?.let { clickAction ->
            val value = clickAction.result.toString()
            when (clickAction) {
                is ClickAction.OpenUrl -> ClickEvent(ClickEvent.Action.OPEN_URL, value)
                is ClickAction.OpenFile -> ClickEvent(ClickEvent.Action.OPEN_FILE, value)
                is ClickAction.RunCommand -> ClickEvent(ClickEvent.Action.RUN_COMMAND, value)
                is ClickAction.SuggestCommand -> ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, value)
                is ClickAction.ChangePage -> ClickEvent(ClickEvent.Action.CHANGE_PAGE, value)
                is ClickAction.CopyToClipboard -> ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, value)
                else -> throw IllegalArgumentException(clickAction.toString())
            }
        }
        builder.hoverEvent = text.hoverAction?.let { hoverAction ->
            when (hoverAction) {
                is HoverAction.ShowText -> HoverEvent(HoverEvent.Action.SHOW_TEXT, serialize(hoverAction.result))
                else -> TODO()
            }
        }
        builder.insertion = text.shiftClickAction?.let { shiftClickAction ->
            when (shiftClickAction) {
                is ShiftClickAction.InsertText -> shiftClickAction.result
                else -> throw IllegalArgumentException(shiftClickAction.toString())
            }
        }
        return arrayOf(builder)
    }
}