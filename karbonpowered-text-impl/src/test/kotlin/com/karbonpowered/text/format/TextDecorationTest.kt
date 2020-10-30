package com.karbonpowered.text.format

import com.karbonpowered.commons.Tristate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TextDecorationTest {
    @Test
    fun `test by boolean`() {
        assertEquals(Tristate.UNDEFINED, Tristate.fromNullableBoolean(null))
        assertEquals(Tristate.TRUE, Tristate.fromNullableBoolean(true))
        assertEquals(Tristate.FALSE, Tristate.fromNullableBoolean(false))

        assertEquals(Tristate.TRUE, Tristate.fromBoolean(true))
        assertEquals(Tristate.FALSE, Tristate.fromBoolean(false))
    }
}

fun assertDecoration(
    style: TextStyle,
    trues: Iterable<TextDecoration> = emptyList(),
    falses: Iterable<TextDecoration> = emptyList()
) {
    assertDecoration(style, trues, Tristate.TRUE)
    assertDecoration(style, falses, Tristate.FALSE)
    val used = trues.union(falses)
    val unset = TextDecorations.values.filterNot { used.contains(it) }
    assertDecoration(style, unset, Tristate.UNDEFINED)
}

fun assertDecoration(style: TextStyle, decorations: Iterable<TextDecoration>, state: Tristate) {
    for (decoration in decorations) {
        assertEquals(state, style.decoration(decoration))
    }
}