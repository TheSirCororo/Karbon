package com.karbonpowered.text.action

import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.format.TextStyle

interface ShiftClickAction<R> : TextAction<R> {
    override fun apply(builder: TextStyle.Builder) {
        builder.shiftClickAction(this)
    }

    interface InsertText : ShiftClickAction<String> {
        override val name: String get() = "insertion"

        interface Factory {
            fun create(text: String): InsertText
        }

        companion object {
            private val factory = loadService<Factory>()

            @JvmStatic
            fun of(text: String): InsertText = factory.create(text)
        }
    }
}