package com.karbonpowered.text.format

import com.karbonpowered.commons.Tristate
import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.action.TextActions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TextStyleTest {

    @Test
    fun `Empty TextStyle actually Empty`() {
        val s0 = TextStyle.empty()
        val s1 = TextStyle { }
        val s2 = TextStyle.of()
        val s3 = TextStyle.builder().build()
        val s4 = TextStyle.builder().color(TextColors.WHITE).reset().build()

        assert(s0 == s1)
        assert(s1 == s2)
        assert(s2 == s3)
        assert(s3 == s4)

        assert(s0.isEmpty())
        assert(s1.isEmpty())
        assert(s2.isEmpty())
        assert(s3.isEmpty())
        assert(s4.isEmpty())

        assertNull(s0.color)
        assertDecoration(s0, emptyList(), emptyList())
        assertNull(s0.clickAction)
        assertNull(s0.hoverAction)
        assertNull(s0.shiftClickAction)
    }

    @Test
    fun `Test applicable builder`() {
        assertEquals(TextStyle.empty(), TextStyle.of())
        val s0 = TextStyle.of(
            TextColor.of(0x00aa00),
            TextDecorations.BOLD,
            TextActions.showText(Text.empty())
        )
        val s1 = TextStyle {
            color(TextColor(0x00aa00))
            bold(true)
            textAction(TextActions.showText(Text()))
        }
        val s2 = TextStyle {
            color = TextColor(0x00aa00)
            bold = Tristate.TRUE
            hoverAction = TextActions.showText(LiteralText { })
        }
        val s3 = TextStyle.builder()
            .color(TextColor(0x00aa00))
            .bold(true)
            .hoverAction(Text())
            .build()
        assertEquals(s0, s1)
        assertEquals(s1, s2)
        assertEquals(s2, s3)
    }

    @Test
    fun `Color test`() {
        assertEquals(TextColors.GREEN, TextStyle { color(TextColors.GREEN) }.color)
        assertEquals(TextColors.RED, TextStyle { color = TextColors.RED }.color)
        assertEquals(TextColors.BLUE, TextStyle.builder().color(TextColors.BLUE).build().color)
        assertEquals(TextColors.YELLOW, TextStyle.of(TextColors.YELLOW).color)
        assertEquals(TextColors.LIGHT_PURPLE, TextStyle(TextColors.LIGHT_PURPLE).color)
    }

    @Test
    fun `Decorate test`() {
        val s0 = TextStyle.empty()
        assertDecoration(s0, emptyList(), emptyList())
        for (decoration in TextDecorations.values) {
            assertDecoration(TextStyle(decoration), listOf(decoration), emptyList())
            assertDecoration(TextStyle.of(decoration), listOf(decoration), emptyList())
            assertDecoration(TextStyle { decorate(decoration) }, listOf(decoration), emptyList())
            assertDecoration(TextStyle.builder().decorate(decoration).build(), listOf(decoration), emptyList())
            assertDecoration(TextStyle.builder().decoration(decoration, true).build(), listOf(decoration), emptyList())
            assertDecoration(
                TextStyle.builder().decoration(decoration, Tristate.TRUE).build(),
                listOf(decoration),
                emptyList()
            )
            assertDecoration(TextStyle.builder().decorate(listOf(decoration)).build(), listOf(decoration), emptyList())
            assertDecoration(
                TextStyle.builder().decoration(mapOf(decoration to Tristate.TRUE)).build(),
                listOf(decoration),
                emptyList()
            )
            assertDecoration(
                TextStyle.builder().decoration(decoration to true).build(),
                listOf(decoration),
                emptyList()
            )
        }
    }

    private fun decorationsTestArguments() = Stream.of(
        Arguments.of(TextDecorations.OBFUSCATED, TextDecorations.BOLD),
        Arguments.of(TextDecorations.BOLD, TextDecorations.STRIKETHROUGH),
        Arguments.of(TextDecorations.STRIKETHROUGH, TextDecorations.UNDERLINED),
        Arguments.of(TextDecorations.UNDERLINED, TextDecorations.ITALIC),
        Arguments.of(TextDecorations.ITALIC, TextDecorations.OBFUSCATED),
    )

    @ParameterizedTest
    @MethodSource("decorationsTestArguments")
    fun `Decorations test`(firstDecoration: TextDecoration, secondDecoration: TextDecoration) {
        val s0 = TextStyle(firstDecoration, secondDecoration)
        val s1 = TextStyle.of(firstDecoration, secondDecoration)
        val s2 = TextStyle.of(listOf(firstDecoration, secondDecoration))
        val s3 = TextStyle {
            if (firstDecoration == TextDecorations.OBFUSCATED || secondDecoration == TextDecorations.OBFUSCATED) obfuscated(
                true
            )
            if (firstDecoration == TextDecorations.BOLD || secondDecoration == TextDecorations.BOLD) bold(true)
            if (firstDecoration == TextDecorations.STRIKETHROUGH || secondDecoration == TextDecorations.STRIKETHROUGH) strikethrough(
                true
            )
            if (firstDecoration == TextDecorations.UNDERLINED || secondDecoration == TextDecorations.UNDERLINED) underlined(
                true
            )
            if (firstDecoration == TextDecorations.ITALIC || secondDecoration == TextDecorations.ITALIC) italic(true)
        }
        val s4 = TextStyle {
            if (firstDecoration == TextDecorations.OBFUSCATED || secondDecoration == TextDecorations.OBFUSCATED) obfuscated(
                Tristate.TRUE
            )
            if (firstDecoration == TextDecorations.BOLD || secondDecoration == TextDecorations.BOLD) bold(Tristate.TRUE)
            if (firstDecoration == TextDecorations.STRIKETHROUGH || secondDecoration == TextDecorations.STRIKETHROUGH) strikethrough(
                Tristate.TRUE
            )
            if (firstDecoration == TextDecorations.UNDERLINED || secondDecoration == TextDecorations.UNDERLINED) underlined(
                Tristate.TRUE
            )
            if (firstDecoration == TextDecorations.ITALIC || secondDecoration == TextDecorations.ITALIC) italic(Tristate.TRUE)
        }
        val s5 = TextStyle {
            decorate(firstDecoration)
            decorate(secondDecoration)
        }
        val s6 = TextStyle {
            decorate(firstDecoration, secondDecoration)
        }
        val s7 = TextStyle {
            decorate(listOf(firstDecoration, secondDecoration))
        }
        val s8 = TextStyle {
            decoration(firstDecoration, true)
            decoration(secondDecoration, true)
        }
        val s9 = TextStyle {
            decoration(firstDecoration, Tristate.TRUE)
            decoration(secondDecoration, Tristate.TRUE)
        }
        val s10 = TextStyle {
            decoration(firstDecoration to true, secondDecoration to true)
        }
        val s11 = TextStyle {
            decoration(mapOf(firstDecoration to Tristate.TRUE, secondDecoration to Tristate.TRUE))
        }
        assertEquals(s0, s1)
        assertEquals(s1, s2)
        assertEquals(s2, s3)
        assertEquals(s3, s4)
        assertEquals(s4, s5)
        assertEquals(s5, s6)
        assertEquals(s6, s7)
        assertEquals(s7, s8)
        assertEquals(s8, s9)
        assertEquals(s10, s11)
    }

    @Test
    fun `Has decorations`() {
        val s0 = TextStyle()
        TextDecorations.values.forEach {
            assertFalse(s0.hasDecoration(it))
        }
        val s1 = TextStyle {
            bold(true)
        }
        assertTrue(s1.hasDecoration(TextDecorations.BOLD))
    }

    @Test
    fun `Color and decorations`() {
        TextColors.values.forEach {
            val s0 = TextStyle(TextDecorations.BOLD, it, TextDecorations.ITALIC)
            assertEquals(it, s0.color)
            assertDecoration(s0, listOf(TextDecorations.ITALIC, TextDecorations.BOLD), emptyList())
        }
    }

    @Test
    fun `Color if absent`() {
        assertEquals(
            TextColors.DARK_PURPLE,
            TextStyle(TextColors.DARK_PURPLE).colorIfAbsent(TextColors.DARK_AQUA).color
        )
        assertEquals(TextColors.GRAY, TextStyle {
            colorIfAbsent(TextColors.GRAY)
            TextColors.values.forEach {
                colorIfAbsent(it)
            }
        }.color)
    }

    @Test
    fun `Merge none`() {
        val s0 = TextStyle()
        val s1 = s0.merge(TextStyle(TextColors.WHITE), TextStyle.Merge.of())
        assertNull(s1.color)
        assertDecoration(s1)
        assertNull(s1.clickAction)
        assertNull(s1.hoverAction)
        assertNull(s1.shiftClickAction)
        assertEquals(s0, s1.color(null))
    }

    @Test
    fun `Merge color`() {
        val s0 = TextStyle(TextColors.RED, TextDecorations.ITALIC, TextActions.changePage(47))
        val s1 = TextStyle { merge(s0, TextStyle.Merge.COLOR) }
        assertEquals(TextColors.RED, s1.color)
        assertDecoration(s1)
        assertNull(s1.clickAction)
    }

    @Test
    fun `Merge actions`() {
        val s0 = TextStyle(
            TextColors.BLACK,
            TextActions.insertText("Yaya"),
            TextActions.showText(Text("Hello")),
            TextActions.runCommand("/me hello")
        )
        val s1 = TextStyle { merge(s0, TextStyle.Merge.ACTIONS) }
        assertEquals(s1.hoverAction, TextActions.showText(Text.of("Hello")))
        assertEquals(s1.shiftClickAction?.result, "Yaya")
        assertEquals(s1.clickAction?.result, "/me hello")
        assertDecoration(s1)
        assertNull(s1.color)
    }

    @Test
    fun `Merge Strategy`() {
        val s0 = TextStyle(TextColors.BLUE)
        val s1 = s0.merge(TextStyle(TextColors.RED), TextStyle.Merge.Strategy.ALWAYS)
        assertEquals(TextColors.RED, s1.color)
        val s2 = s0.merge(TextStyle(TextColors.RED), TextStyle.Merge.Strategy.IF_ABSENT_ON_TARGET)
        assertEquals(TextColors.BLUE, s2.color)
        val s3 = TextStyle().merge(s0, TextStyle.Merge.Strategy.NEVER)
        assertNull(s3.color)
    }
}