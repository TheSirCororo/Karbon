@file:JvmName("AdventureTextAdapter")

package com.karbonpowered.text.serializer.adventure

import com.karbonpowered.commons.Tristate
import com.karbonpowered.text.Text
import com.karbonpowered.text.action.ClickAction
import com.karbonpowered.text.action.HoverAction
import com.karbonpowered.text.action.TextActions
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.TextDecoration
import com.karbonpowered.text.format.TextDecorations
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import java.net.URL
import java.nio.file.FileSystems
import net.kyori.adventure.text.format.TextColor as AdventureTextColor
import net.kyori.adventure.text.format.TextDecoration as AdventureTextDecoration

fun Component.asKarbon(): Text = AdventureTextSerializer.deserialize(this)
fun Text.asAdventure(): Component = AdventureTextSerializer.serialize(this)

fun AdventureTextColor.asKarbon(): TextColor = TextColor(value())
fun TextColor.asAdventure(): AdventureTextColor = AdventureTextColor.color(value)

fun ClickEvent.asKarbon(): ClickAction<*> = when (action()) {
    ClickEvent.Action.OPEN_URL -> TextActions.openUrl(URL(value()))
    ClickEvent.Action.OPEN_FILE -> TextActions.openFile(FileSystems.getDefault().getPath(value()))
    ClickEvent.Action.RUN_COMMAND -> TextActions.runCommand(value())
    ClickEvent.Action.SUGGEST_COMMAND -> TextActions.suggestCommand(value())
    ClickEvent.Action.CHANGE_PAGE -> TextActions.changePage(value().toInt())
    ClickEvent.Action.COPY_TO_CLIPBOARD -> TextActions.copyToClipboard(value())
}

fun ClickAction<*>.asAdventure(): ClickEvent = when (this) {
    is ClickAction.OpenUrl -> ClickEvent.openUrl(result.toString())
    is ClickAction.OpenFile -> ClickEvent.openFile(result.toString())
    is ClickAction.RunCommand -> ClickEvent.runCommand(result)
    is ClickAction.SuggestCommand -> ClickEvent.suggestCommand(result)
    is ClickAction.ChangePage -> ClickEvent.changePage(result)
    is ClickAction.CopyToClipboard -> ClickEvent.copyToClipboard(result)
    else -> throw IllegalArgumentException(toString())
}

fun HoverEvent<*>.asKarbon(): HoverAction<*> = when (action()) {
    HoverEvent.Action.SHOW_TEXT -> TextActions.showText((value() as Component).asKarbon())
    else -> TODO()
}

fun HoverAction<*>.asAdventure(): HoverEvent<*> = when (this) {
    is HoverAction.ShowText -> HoverEvent.showText(result.asAdventure())
    else -> throw IllegalArgumentException(toString())
}

fun AdventureTextDecoration.asKarbon(): TextDecoration = when (this) {
    AdventureTextDecoration.OBFUSCATED -> TextDecorations.OBFUSCATED
    AdventureTextDecoration.BOLD -> TextDecorations.BOLD
    AdventureTextDecoration.STRIKETHROUGH -> TextDecorations.STRIKETHROUGH
    AdventureTextDecoration.UNDERLINED -> TextDecorations.UNDERLINED
    AdventureTextDecoration.ITALIC -> TextDecorations.ITALIC
}

fun TextDecoration.asAdventure(): AdventureTextDecoration = when (this) {
    TextDecorations.OBFUSCATED -> AdventureTextDecoration.OBFUSCATED
    TextDecorations.BOLD -> AdventureTextDecoration.BOLD
    TextDecorations.STRIKETHROUGH -> AdventureTextDecoration.STRIKETHROUGH
    TextDecorations.UNDERLINED -> AdventureTextDecoration.UNDERLINED
    TextDecorations.ITALIC -> AdventureTextDecoration.ITALIC
    else -> throw IllegalArgumentException(toString())
}

fun AdventureTextDecoration.State.asKarbon(): Tristate = when (this) {
    AdventureTextDecoration.State.NOT_SET -> Tristate.UNDEFINED
    AdventureTextDecoration.State.FALSE -> Tristate.FALSE
    AdventureTextDecoration.State.TRUE -> Tristate.TRUE
}

fun Tristate.asAdventureTextDecorationState(): AdventureTextDecoration.State = when (this) {
    Tristate.TRUE -> AdventureTextDecoration.State.TRUE
    Tristate.FALSE -> AdventureTextDecoration.State.FALSE
    Tristate.UNDEFINED -> AdventureTextDecoration.State.NOT_SET
}