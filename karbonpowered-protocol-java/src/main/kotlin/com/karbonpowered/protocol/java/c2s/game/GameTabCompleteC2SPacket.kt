package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.data.Position

data class GameTabCompleteC2SPacket
@JvmOverloads
constructor(
    val text: String = "",
    val transactionId: Int = 0,
    val assumeCommand: Boolean = true,
    val lookedAtBlock: Position? = null
) : MinecraftPacket() {
    object Codec_1_14 : Codec<GameTabCompleteC2SPacket> {
        override fun decode(buffer: MessageBuf): GameTabCompleteC2SPacket {
            val transactionId = buffer.readVarInt()
            val text = buffer.readString()
            return GameTabCompleteC2SPacket(text, transactionId)
        }

        override fun encode(buffer: MessageBuf, message: GameTabCompleteC2SPacket): MessageBuf {
            buffer.writeVarInt(message.transactionId)
            buffer.writeString(message.text)
            return buffer
        }
    }

    object Codec_1_9 : Codec<GameTabCompleteC2SPacket> {
        override fun decode(buffer: MessageBuf): GameTabCompleteC2SPacket {
            val text = buffer.readString()
            val assumeCommand = buffer.readBoolean()
            val lookedAtBlock = if (buffer.readBoolean()) {
                Position.Codec1_7.decode(buffer)
            } else {
                null
            }
            return GameTabCompleteC2SPacket(text, assumeCommand = assumeCommand, lookedAtBlock = lookedAtBlock)
        }

        override fun encode(buffer: MessageBuf, message: GameTabCompleteC2SPacket): MessageBuf {
            buffer.writeString(message.text)
            buffer.writeBoolean(message.assumeCommand)
            buffer.writeBoolean(message.lookedAtBlock != null)
            if (message.lookedAtBlock != null) {
                Position.Codec1_7.encode(buffer, message.lookedAtBlock)
            }
            return buffer
        }
    }

    object Codec_1_7 : Codec<GameTabCompleteC2SPacket> {
        override fun decode(buffer: MessageBuf): GameTabCompleteC2SPacket {
            val text = buffer.readString()
            return GameTabCompleteC2SPacket(text)
        }

        override fun encode(buffer: MessageBuf, message: GameTabCompleteC2SPacket): MessageBuf {
            buffer.writeString(message.text)
            return buffer
        }
    }
}