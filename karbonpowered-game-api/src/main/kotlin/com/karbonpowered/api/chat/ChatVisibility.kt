package com.karbonpowered.api.chat

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.annotation.CatalogedBy
import com.karbonpowered.translation.Translatable

@CatalogedBy(ChatVisibilities::class)
interface ChatVisibility : CatalogType, Translatable {
    fun isVisible(messagePosition: MessagePosition): Boolean
}