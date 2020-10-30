package com.karbonpowered.api.chat

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry


object ChatVisibilities : Catalog<ChatVisibility> {
    override val type: Class<ChatVisibility>
        get() = ChatVisibility::class.java

    @JvmField
    val FULL = CatalogRegistry.getProvider(ChatVisibility::class, "FULL").get()

    @JvmField
    val HIDDEN = CatalogRegistry.getProvider(ChatVisibility::class, "HIDDEN").get()

    @JvmField
    val SYSTEM = CatalogRegistry.getProvider(ChatVisibility::class, "SYSTEM").get()
}