package com.karbonpowered.text.karbon.action

import com.karbonpowered.text.action.ClickAction
import java.nio.file.Path

data class KarbonOpenFileClickAction(override val result: Path) : ClickAction.OpenFile {
    class Factory : ClickAction.OpenFile.Factory {
        override fun create(path: Path): ClickAction.OpenFile = KarbonOpenFileClickAction(path)
    }
}