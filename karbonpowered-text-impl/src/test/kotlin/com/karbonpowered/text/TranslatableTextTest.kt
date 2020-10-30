package com.karbonpowered.text

import com.karbonpowered.text.format.TextColors
import com.karbonpowered.text.format.assertDecoration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class TranslatableTextTest {
    @Test
    fun `Of Builder`() {
        val text = TranslatableText("multiplayer.player.left")
        assertEquals("multiplayer.player.left", text.key)
        assertNull(text.color)
        assertDecoration(text.style)
    }

    @Test
    fun `Of color`() {
        val text = TranslatableText("multiplayer.player.left", TextColors.GREEN)
        assertEquals(TextColors.GREEN, text.color)
        assertDecoration(text.style)
    }

    @Test
    fun `Test builder`() {
        val t1 = TranslatableText("multiplayer.player.left") {
            color(TextColors.GREEN)
        }
        val t2 = TranslatableText("multiplayer.player.left") {
            color = TextColors.GREEN
        }
        assertEquals("multiplayer.player.left", t1.key)
        assertEquals(TextColors.GREEN, t1.color)
        assertEquals(t1, t2)
    }
}