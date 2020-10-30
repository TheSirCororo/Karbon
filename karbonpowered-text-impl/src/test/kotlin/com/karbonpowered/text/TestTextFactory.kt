package com.karbonpowered.text

import com.karbonpowered.text.action.TextActions
import com.karbonpowered.text.format.TextColors
import com.karbonpowered.text.format.TextDecorations
import com.karbonpowered.text.format.TextStyle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.net.URL
import java.util.concurrent.Callable
import java.util.function.Supplier

class TestTextFactory {
    @Test
    fun `Empty text`() {
        val t0 = Text.empty()
        val t1 = Text.of()
        val t2 = Text.of(TextColors.RED)
        val t3 = Text.of(TextDecorations.UNDERLINED)
        val t4 = Text.of(TextStyle.empty())
        assertEquals(t0, t1)
        assertEquals(t0, t2)
        assertEquals(t0, t3)
        assertEquals(t0, t4)
    }

    @Test
    fun `Single text`() {
        val t0 = LiteralText.of("Cats!")
        val t1 = Text("Cats!")
        assertEquals(t0, t1)
        assertEquals(Text.newLine(), Text("\n"))
        assertEquals(Text.space(), Text(" "))
    }

    @Test
    fun `Simple text`() {
        val t0 = LiteralText.of("Cats!", TextColors.DARK_PURPLE)
        val t1 = Text(TextColors.DARK_PURPLE, "Cats!")
        val t2 = Text(TextStyle(TextColors.DARK_PURPLE), "Cats!")
        assertEquals(t0, t1)
        assertEquals(t0, t2)
    }

    @Test
    fun `Advenced text`() {
        val karbonUrl = URL("https://github.com/KarbonPowered")
        val t0 = LiteralText.builder()
            .append(LiteralText.of("Cats!", TextColors.DARK_RED, TextDecorations.ITALIC))
            .append(
                LiteralText.of(
                    " vs ",
                    TextStyle.of(
                        TextDecorations.UNDERLINED,
                        TextActions.openUrl(karbonUrl),
                        TextActions.showText(LiteralText.of("versus"))
                    )
                )
            )
            .append(LiteralText.of("DOGS?", TextColors.DARK_BLUE, TextDecorations.BOLD))
            .append(TranslatableText.of("foo.bar"))
            .build()

        val t1 = Text(
            TextColors.DARK_RED, TextDecorations.ITALIC, "Cats!",
            TextStyle.of(
                TextDecorations.UNDERLINED,
                TextActions.openUrl(karbonUrl),
                TextActions.showText(LiteralText.of("versus"))
            ), " vs ",
            LiteralText.of("DOGS?", TextColors.DARK_BLUE, TextDecorations.BOLD), TranslatableText.of("foo.bar")
        )

        val t2 = Text(
            TextColors.DARK_RED,
            TextDecorations.ITALIC,
            { "Cats!" },
            karbonUrl,
            TextActions.showText(Text("versus")),
            TextDecorations.UNDERLINED,
            Callable { TextRepresentable { Text(" vs ") } },
            TextColors.DARK_BLUE,
            TextDecorations.BOLD,
            Supplier { "DOGS?" },
            TranslatableText.of("foo.bar")
        )

        assertEquals(t0, t1)
        assertEquals(t0, t2)
    }
}