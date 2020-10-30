@file:Suppress("unused", "ConflictingExtensionProperty")

package com.karbonpowered.adapter.bukkit

import com.karbonpowered.api.chat.MessagePosition
import com.karbonpowered.api.chat.MessagePositions
import com.karbonpowered.text.Text
import com.karbonpowered.text.serializer.bungee.asBungee
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerInfo
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerInfo.EnumPlayerInfoAction
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer
import org.bukkit.entity.Player

inline class BukkitPlayer(
    val player: Player
) {
    val bukkitEntity get() = player as CraftPlayer
    val handleEntity get() = bukkitEntity.handle

    var playerListName: Text
        get() = handleEntity.listName.asKarbon()
        set(value) {
            handleEntity.listName = value.asNms()
            val packet = PacketPlayOutPlayerInfo(EnumPlayerInfoAction.UPDATE_DISPLAY_NAME, handleEntity)
            Bukkit.getOnlinePlayers().forEach {
                if (it.canSee(player)) {
                    it.karbon.handleEntity.playerConnection.sendPacket(packet)
                }
            }
        }

    @Suppress("DEPRECATION")
    fun sendMessage(text: Text, messagePosition: MessagePosition = MessagePositions.SYSTEM) {
        player.spigot().sendMessage(messagePosition.asBukkit(), *text.asBungee())
    }

    fun sendActionBar(text: Text) {
        player.sendActionBar(text.asBungee().first())
    }

    @Suppress("DEPRECATION")
    fun sendTitle(
        title: Text = Text.empty(),
        subtitle: Text = Text.empty(),
        fadeIn: Int = 10,
        stay: Int = 70,
        fadeOut: Int = 20
    ) {
        player.showTitle(title.asBungee(), subtitle.asBungee(), fadeIn, stay, fadeOut)
    }

    fun setPlayerListHeaderFooter(header: Text? = null, footer: Text? = null) {
        player.setPlayerListHeaderFooter(header?.asBungee(), footer?.asBungee())
    }
}

val Player.karbon: BukkitPlayer
    get() = BukkitPlayer(this)

@Suppress("DEPRECATION")
@JvmOverloads
fun Player.sendMessage(text: Text, messagePosition: MessagePosition = MessagePositions.SYSTEM) =
    karbon.sendMessage(text, messagePosition)

fun Player.sendActionBar(text: Text) = BukkitPlayer(this).sendActionBar(text)

@Suppress("DEPRECATION")
@JvmOverloads
fun Player.sendTitle(
    title: Text = Text.empty(),
    subtitle: Text = Text.empty(),
    fadeIn: Int = 10,
    stay: Int = 70,
    fadeOut: Int = 20
) =
    karbon.sendTitle(title, subtitle, fadeIn, stay, fadeOut)

fun Player.setPlayerListHeaderFooter(header: Text? = null, footer: Text? = null) =
    karbon.setPlayerListHeaderFooter(header, footer)

var Player.playerListName
    get() = karbon.playerListName
    set(value) {
        karbon.playerListName = value
    }