package com.karbonpowered.text.karbon.action

import com.karbonpowered.text.action.ClickAction

data class KarbonChangePageClickAction(override val result: Int) : ClickAction.ChangePage {
    class Factory : ClickAction.ChangePage.Factory {
        override fun create(page: Int): ClickAction.ChangePage = KarbonChangePageClickAction(page)
    }
}