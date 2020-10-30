package com.karbonpowered.adapter.velocity

import com.karbonpowered.api.chat.MessagePosition
import com.karbonpowered.api.chat.MessagePositions
import com.karbonpowered.text.Text
import com.karbonpowered.text.serializer.adventure.asAdventure
import com.velocitypowered.api.command.CommandSource
import com.velocitypowered.api.proxy.Player
import net.kyori.adventure.audience.MessageType
import net.kyori.adventure.title.Title
import java.time.Duration

class VelocityAdapter

fun MessagePosition.asAdventure(): MessageType = when (this) {
    MessagePositions.CHAT -> MessageType.CHAT
    MessagePositions.SYSTEM -> MessageType.SYSTEM
    MessagePositions.ACTION_BAR -> MessageType.SYSTEM
    else -> throw IllegalArgumentException(toString())
}

fun CommandSource.sendMessage(text: Text) = Unit// sendMessage(text.asAdventure())

fun Player.sendMessage(text: Text, messagePosition: MessagePosition = MessagePositions.SYSTEM) = Unit
//sendMessage(text.asAdventure(), messagePosition.asAdventure())

fun Player.sendActionBar(text: Text) = sendActionBar(text.asAdventure())

fun Player.showTitle(
    title: Text = Text.empty(),
    subtitle: Text = Text.empty(),
    fadeInt: Int = 50,
    stay: Int = 70,
    fadeOut: Int = 20
) = showTitle(
    title,
    subtitle,
    Duration.ofMillis(fadeInt * 50L),
    Duration.ofMillis(stay * 50L),
    Duration.ofMillis(fadeOut * 50L)
)

fun Player.showTitle(
    title: Text = Text.empty(),
    subtitle: Text = Text.empty(),
    fadeIn: Duration = Duration.ofMillis(10 * 50),
    stay: Duration = Duration.ofMillis(70 * 50),
    fadeOut: Duration = Duration.ofMillis(20 * 50)
) = showTitle(Title.title(title.asAdventure(), subtitle.asAdventure(), Title.Times.of(fadeIn, stay, fadeOut)))