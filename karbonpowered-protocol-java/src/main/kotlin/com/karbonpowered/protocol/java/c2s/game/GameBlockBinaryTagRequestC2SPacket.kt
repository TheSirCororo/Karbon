package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.data.Position

data class GameBlockBinaryTagRequestC2SPacket
@JvmOverloads
constructor(
    val transactionId: Int = 0,
    val position: Position = Position()
) : MinecraftPacket() {
    object Codec_1_14 : Codec<GameBlockBinaryTagRequestC2SPacket> {
        override fun decode(buffer: MessageBuf): GameBlockBinaryTagRequestC2SPacket {
            val transactionId = buffer.readVarInt()
            val position = Position.Codec1_14.decode(buffer)
            return GameBlockBinaryTagRequestC2SPacket(transactionId, position)
        }

        override fun encode(buffer: MessageBuf, message: GameBlockBinaryTagRequestC2SPacket): MessageBuf {
            buffer.writeVarInt(message.transactionId)
            Position.Codec1_14.encode(buffer, message.position)
            return buffer
        }
    }

    object Codec_1_7 : Codec<GameBlockBinaryTagRequestC2SPacket> {
        override fun decode(buffer: MessageBuf): GameBlockBinaryTagRequestC2SPacket {
            val transactionId = buffer.readVarInt()
            val position = Position.Codec1_7.decode(buffer)
            return GameBlockBinaryTagRequestC2SPacket(transactionId, position)
        }

        override fun encode(buffer: MessageBuf, message: GameBlockBinaryTagRequestC2SPacket): MessageBuf {
            buffer.writeVarInt(message.transactionId)
            Position.Codec1_7.encode(buffer, message.position)
            return buffer
        }
    }
}