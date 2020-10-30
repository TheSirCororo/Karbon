package com.karbonpowered.text

import com.karbonpowered.text.format.TextColors
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LiteralTextTest {
    @Test
    fun `Replace test`() {
        val expected = Text("cat ", TextColors.DARK_PURPLE, "says", " ", Text(TextColors.AQUA, "meow"))
        val text = Text("cat says ", TextColors.AQUA, "meow")
        val replaced = text.replace("says".toPattern()) { it.color(TextColors.DARK_PURPLE) }
        assertEquals(expected, replaced)
    }

    @Test
    fun `Replace first`() {
        val expected = LiteralText.builder()
            .content("Cat ")
            .append("Dog", TextColors.DARK_PURPLE)
            .append(" Cat Dog Cat Dog")
            .build()
        val text = LiteralText.builder().content("Cat Dog Cat Dog Cat Dog").build()
        val replaced = text.replaceFirst("Dog".toPattern()) { it.color(TextColors.DARK_PURPLE) }
        assertEquals(expected, replaced)
    }

    @Test
    fun `Replace N`() {
        val first = Text(TextColors.DARK_PURPLE, "Dog")
        val second = Text(TextColors.DARK_PURPLE, "Dog")
        val expected = Text("Cat ", first, " Cat ", second, " Cat Dog")
        val text = Text("Cat Dog Cat Dog Cat Dog")
        val replaced = text.replace("Dog".toPattern(), { it.color(TextColors.DARK_PURPLE) }, 2)
        assertEquals(expected, replaced)
    }

    @Test
    fun `Multiple replace`() {
        val expected = Text(
            "Test string with ",
            Text(TextColors.GOLD, "Ananas"),
            " replaced with ",
            Text(TextColors.YELLOW, "Banana")
        )
        val text = LiteralText.builder().content("Test string with AAA replaced with BBB").build()
        val replaced = text
            .replace("AAA".toPattern()) { it.reset().append(Text(TextColors.GOLD, "Ananas")) }
            .replace("BBB".toPattern()) { it.reset().append(Text(TextColors.YELLOW, "Banana")) }

        assertEquals(expected, replaced)
    }
}