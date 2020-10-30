package com.karbonpowered.text.karbon.action

import com.karbonpowered.text.action.ShiftClickAction

data class KarbonInsertTextShiftClickAction(override val result: String) : ShiftClickAction.InsertText {
    class Factory : ShiftClickAction.InsertText.Factory {
        override fun create(text: String): ShiftClickAction.InsertText = KarbonInsertTextShiftClickAction(text)
    }
}