package com.karbonpowered.text.karbon.action

import com.karbonpowered.text.action.ClickAction

data class KarbonSuggestCommandClickAction(override val result: String) : ClickAction.SuggestCommand {
    class Factory : ClickAction.SuggestCommand.Factory {
        override fun create(command: String): ClickAction.SuggestCommand = KarbonSuggestCommandClickAction(command)
    }
}