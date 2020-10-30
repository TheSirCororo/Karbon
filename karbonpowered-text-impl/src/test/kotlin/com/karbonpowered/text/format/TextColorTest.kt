package com.karbonpowered.text.format

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.awt.Color

class TextColorTest {
    @Test
    fun `Hex test`() {
        assertEquals(TextColor(0xaa00aa), TextColor("#aa00aa"))
        assertEquals(TextColor.of(0xbb00bb), TextColor.of("#bb00bb"))
        assertEquals(TextColor(0xaa00aa).asHexString(), "#aa00aa")
    }

    @Test
    fun `Test from Java color`() {
        assertEquals(TextColor(0xaa00aa), TextColor(Color(0xaa, 0x00, 0xaa, 0xcc)))
        assertEquals(Color(0, 255, 128), TextColor(0, 255, 128).asColor())
    }

    @Test
    fun `Test malformed Hex string`() {
        assertNull(TextColor.of("Not valid hex"))
        assertNull(TextColor.of("#xxyyzz"))
        assertNull(TextColor.of("aa00aa"))
        assertNull(TextColor.of("baba"))
    }

    @Test
    fun `Test pure colors`() {
        val redInt = TextColor(0xff0000)
        val greenInt = TextColor(0x00ff00)
        val blueInt = TextColor(0x0000ff)

        val red = TextColor(0xff, 0x00, 0x00)
        val green = TextColor(0x00, 0xff, 0x00)
        val blue = TextColor(0x00, 0x00, 0xff)

        assertEquals(redInt, red)
        assertEquals(greenInt, green)
        assertEquals(blueInt, blue)
    }

    @Test
    fun `Test extract colors`() {
        val purple = TextColor(0xff00ff)
        assertEquals(0xff, purple.red)
        assertEquals(0x00, purple.green)
        assertEquals(0xff, purple.blue)

        val color = TextColor.of(0xcafe04)
        assertEquals(0xca, color.red)
        assertEquals(0xfe, color.green)
        assertEquals(0x04, color.blue)
    }

    @Test
    fun `Test equality`() {
        assertEquals(TextColor(0xff00ff), TextColor(0xff, 0x00, 0xff))
        assertEquals(TextColor(0xaabbcc), TextColor(0xaa, 0xbb, 0xcc))
    }

    @Test
    fun `Test CSS Hex color`() {
        assertEquals(TextColor(0x77ff11), TextColor("#7f1"))
    }
}