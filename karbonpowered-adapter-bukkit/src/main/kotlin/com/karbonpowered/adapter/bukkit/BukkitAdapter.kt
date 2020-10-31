package com.karbonpowered.adapter.bukkit

import com.karbonpowered.adapter.bukkit.data.provider.item.ItemStackDataProviders
import com.karbonpowered.adapter.bukkit.item.BukkitItemType
import com.karbonpowered.api.chat.MessagePosition
import com.karbonpowered.api.chat.MessagePositions
import com.karbonpowered.text.Text
import com.karbonpowered.text.serializer.TextSerializers
import net.md_5.bungee.api.ChatMessageType
import net.minecraft.server.v1_16_R2.IChatBaseComponent

object BukkitAdapter {
    private var isInitialized = false

    fun initialize() {
        if (isInitialized) return
        isInitialized = true

        ItemStackDataProviders.register()
        BukkitItemType.register()
    }
}

fun MessagePosition.asBukkit(): ChatMessageType = when (this) {
    MessagePositions.SYSTEM -> ChatMessageType.SYSTEM
    MessagePositions.ACTION_BAR -> ChatMessageType.ACTION_BAR
    MessagePositions.CHAT -> ChatMessageType.CHAT
    else -> throw IllegalArgumentException(this.toString())
}

fun Text.asNms(): IChatBaseComponent =
    IChatBaseComponent.ChatSerializer.jsonToComponent(TextSerializers.JSON.serialize(this))!!

fun IChatBaseComponent.asKarbon(): Text =
    TextSerializers.JSON.deserialize(IChatBaseComponent.ChatSerializer.componentToJson(this))

