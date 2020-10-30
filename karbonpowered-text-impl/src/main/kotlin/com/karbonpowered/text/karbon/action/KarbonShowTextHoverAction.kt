package com.karbonpowered.text.karbon.action

import com.karbonpowered.text.Text
import com.karbonpowered.text.action.HoverAction

data class KarbonShowTextHoverAction(override val result: Text) : HoverAction.ShowText {
    class Factory : HoverAction.ShowText.Factory {
        override fun create(text: Text): HoverAction.ShowText = KarbonShowTextHoverAction(text)
    }
}