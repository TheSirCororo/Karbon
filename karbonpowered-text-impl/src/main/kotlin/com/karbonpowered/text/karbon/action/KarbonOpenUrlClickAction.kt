package com.karbonpowered.text.karbon.action

import com.karbonpowered.text.action.ClickAction
import java.net.URL

data class KarbonOpenUrlClickAction(override val result: URL) : ClickAction.OpenUrl {
    class Factory : ClickAction.OpenUrl.Factory {
        override fun create(url: URL): ClickAction.OpenUrl = KarbonOpenUrlClickAction(url)
    }
}
