package com.karbonpowered.adapter.bukkit

import com.karbonpowered.adapter.bukkit.data.provider.item.ItemStackDataProviders
import com.karbonpowered.adapter.bukkit.item.BukkitItemType
import com.karbonpowered.adapter.bukkit.item.inventory.asBukkit
import com.karbonpowered.api.chat.MessagePosition
import com.karbonpowered.api.chat.MessagePositions
import com.karbonpowered.api.constant.Keys
import com.karbonpowered.api.item.ItemTypes
import com.karbonpowered.api.item.inventory.ItemStack
import com.karbonpowered.api.profile.GameProfile
import com.karbonpowered.catalog.CatalogRegistry
import com.karbonpowered.text.Text
import com.karbonpowered.text.format.TextColors
import com.karbonpowered.text.format.TextDecorations
import com.karbonpowered.text.serializer.TextSerializers
import net.md_5.bungee.api.ChatMessageType
import net.minecraft.server.v1_16_R2.IChatBaseComponent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class BukkitAdapter : JavaPlugin(), Listener {
    val isDebug = System.getProperty("karbon.debug") == "true"

    override fun onLoad() {
        ItemStackDataProviders.register()
        BukkitItemType.register()
        CatalogRegistry
    }

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        if (!isDebug) return

        val item = ItemStack(ItemTypes.PLAYER_HEAD, 3) {
            displayName = Text(TextColors.RED, "Green Head?")
            lore = listOf(
                Text(TextDecorations.OBFUSCATED, TextColors.RED, "ALlsaaklask2dwqpsdkpwsqd"),
                Text(TextColors.WHITE, "yaaaa")
            )
            this[Keys.GAME_PROFILE] = GameProfile(event.player.uniqueId, event.player.name)
        }

        event.player.inventory.addItem(item.asBukkit())
    }
}

val nmsVersion: String
    get() {
        val craftServerPackage = Bukkit.getServer().javaClass.getPackage().name
        return craftServerPackage.substring(craftServerPackage.lastIndexOf('.') + 1)
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

