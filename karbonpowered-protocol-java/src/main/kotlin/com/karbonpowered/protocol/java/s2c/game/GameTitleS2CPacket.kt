package com.karbonpowered.protocol.java.s2c.game

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.util.readText
import com.karbonpowered.protocol.java.util.writeText
import com.karbonpowered.text.Text

data class GameTitleS2CPacket(
    val action: Action = Action.TITLE,
    val text: Text = Text.empty(),
    val fadeInt: Int = 10,
    val stay: Int = 70,
    val fadeOut: Int = 20
) : MinecraftPacket() {
    constructor(fadeInt: Int, stay: Int, fadeOut: Int) : this(Action.TIMES, Text.empty(), fadeInt, stay, fadeOut)

    enum class Action : CatalogType {
        TITLE, SUBTITLE, ACTION_BAR, TIMES, CLEAR, RESET;

        override val key: NamespacedKey = NamespacedKey.karbon(name.toLowerCase())
    }

    object Codec_1_16 : Codec<GameTitleS2CPacket> {
        override fun decode(buffer: MessageBuf): GameTitleS2CPacket {
            return when (val action = MagicValues.key(Action::class, buffer.readVarInt())) {
                Action.TITLE, Action.SUBTITLE, Action.ACTION_BAR -> {
                    val text = buffer.readText()
                    return GameTitleS2CPacket(action, text)
                }
                Action.TIMES -> {
                    val fadeInt = buffer.readInt()
                    val stay = buffer.readInt()
                    val fadeOut = buffer.readInt()
                    return GameTitleS2CPacket(fadeInt, stay, fadeOut)
                }
                Action.CLEAR, Action.RESET -> GameTitleS2CPacket(action)
            }
        }

        override fun encode(buffer: MessageBuf, message: GameTitleS2CPacket): MessageBuf {
            buffer.writeVarInt(MagicValues.value(message.action))
            when (message.action) {
                Action.TITLE,
                Action.SUBTITLE,
                Action.ACTION_BAR -> {
                    buffer.writeText(message.text)
                }
                Action.TIMES -> {
                    buffer.writeInt(message.fadeInt)
                    buffer.writeInt(message.stay)
                    buffer.writeInt(message.fadeOut)
                }
                Action.CLEAR, Action.RESET -> {
                    // nothing
                }
            }
            return buffer
        }
    }
}