package com.karbonpowered.text.karbon.action

import com.karbonpowered.text.action.ClickAction

data class KarbonRunCommandClickAction(override val result: String) : ClickAction.RunCommand {
    class Factory : ClickAction.RunCommand.Factory {
        override fun create(command: String): ClickAction.RunCommand = KarbonRunCommandClickAction(command)
    }
}