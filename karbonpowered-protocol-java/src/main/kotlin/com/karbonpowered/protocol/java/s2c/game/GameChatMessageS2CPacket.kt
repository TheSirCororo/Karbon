package com.karbonpowered.protocol.java.s2c.game

import com.karbonpowered.api.chat.MessagePosition
import com.karbonpowered.api.chat.MessagePositions
import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.util.readText
import com.karbonpowered.protocol.java.util.writeText
import com.karbonpowered.text.Text
import java.util.*

data class GameChatMessageS2CPacket(
    val message: Text,
    val position: MessagePosition = MessagePositions.SYSTEM,
    val sender: UUID = UUID(0, 0)
) : MinecraftPacket() {
    object Codec_1_16 : Codec<GameChatMessageS2CPacket> {
        override fun decode(buffer: MessageBuf): GameChatMessageS2CPacket {
            val message = buffer.readText()
            val position = MagicValues.key(MessagePosition::class, buffer.readByte())
            val sender = buffer.readUniqueId()
            return GameChatMessageS2CPacket(message, position, sender)
        }

        override fun encode(buffer: MessageBuf, message: GameChatMessageS2CPacket): MessageBuf {
            buffer.writeText(message.message)
            buffer.writeByte(MagicValues.value(message.position))
            buffer.writeUniqueId(message.sender)
            return buffer
        }
    }
}