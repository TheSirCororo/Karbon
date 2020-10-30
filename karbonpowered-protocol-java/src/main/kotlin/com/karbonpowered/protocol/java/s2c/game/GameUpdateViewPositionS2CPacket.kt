package com.karbonpowered.protocol.java.s2c.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameUpdateViewPositionS2CPacket(
    val chunkX: Int,
    val chunkZ: Int
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameUpdateViewPositionS2CPacket> {
        override fun decode(buffer: MessageBuf): GameUpdateViewPositionS2CPacket {
            val chunkX = buffer.readVarInt()
            val chunkZ = buffer.readVarInt()
            return GameUpdateViewPositionS2CPacket(chunkX, chunkZ)
        }

        override fun encode(buffer: MessageBuf, message: GameUpdateViewPositionS2CPacket): MessageBuf {
            buffer.writeVarInt(message.chunkX)
            buffer.writeVarInt(message.chunkZ)
            return buffer
        }
    }
}