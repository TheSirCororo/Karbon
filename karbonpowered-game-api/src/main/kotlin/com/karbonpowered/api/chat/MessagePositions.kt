package com.karbonpowered.api.chat

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry

object MessagePositions : Catalog<MessagePosition> {
    override val type: Class<MessagePosition>
        get() = MessagePosition::class.java

    @JvmField
    val ACTION_BAR = CatalogRegistry.getProvider(MessagePosition::class, "ACTION_BAR").get()

    @JvmField
    val CHAT = CatalogRegistry.getProvider(MessagePosition::class, "CHAT").get()

    @JvmField
    val SYSTEM = CatalogRegistry.getProvider(MessagePosition::class, "SYSTEM").get()
}