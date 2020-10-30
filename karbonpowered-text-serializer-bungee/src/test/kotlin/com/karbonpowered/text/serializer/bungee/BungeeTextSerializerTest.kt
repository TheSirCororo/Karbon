package com.karbonpowered.text.serializer.bungee

import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.action.TextActions
import com.karbonpowered.text.format.TextColors
import com.karbonpowered.text.format.TextDecorations
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BungeeTextSerializerTest {
    fun createBungeeText() = arrayOf(TextComponent("Test").apply {
        color = ChatColor.AQUA
        hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent("Hello!")))
        extra = listOf(
            TextComponent(" "),
            TextComponent("with").apply {
                color = ChatColor.RED
            },
            TextComponent(" "),
            TextComponent("Multiple").apply {
                isStrikethrough = true
                isBold = true
                isItalic = true
                isUnderlined = true
            },
            TextComponent(" "),
            TextComponent("components").apply {
                clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/me Yaya")
            },
            TextComponent("!").apply {
                isObfuscated = true
            }
        )
    })

    fun createKarbonText() = Text(
        TextColors.AQUA, TextActions.showText(Text("Hello!")), "Test",
        Text.space(),
        Text(TextColors.RED, "with"),
        Text.space(),
        LiteralText.of(
            "Multiple",
            null,
            TextDecorations.ITALIC,
            TextDecorations.UNDERLINED,
            TextDecorations.BOLD,
            TextDecorations.STRIKETHROUGH
        ),
        " ",
        Text(TextActions.runCommand("/me Yaya"), "components"),
        TextDecorations.OBFUSCATED, "!"
    )

    @Test
    fun serialize() {
        val expected = createBungeeText()
        val text = createKarbonText()

        val serialized = text.asBungee()

        assertComponents(expected, serialized)
    }

    @Test
    fun deserialize() {
        val expected = createKarbonText()
        val component = createBungeeText()

        val deserialized = component.asKarbon()

        assertEquals(expected, deserialized)
    }

    @Test
    fun `serialize and deserialize`() {
        val text = createKarbonText()
        assertEquals(text, text.asBungee().asKarbon())
        val component = createBungeeText()
        assertComponents(component, component.asKarbon().asBungee())
    }

    fun assertComponents(expected: Array<out BaseComponent>, actual: Array<out BaseComponent>) {
        if (expected.size == 1 && actual.size == 1) {
            assertEquals(expected.first(), actual.first())
        } else {
            assertArrayEquals(expected, actual)
        }
    }
}