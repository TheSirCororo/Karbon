package com.karbonpowered.protocol.java.s2c.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameCloseContainerS2CPacket(
    val syncId: Int
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameCloseContainerS2CPacket> {
        override fun decode(buffer: MessageBuf): GameCloseContainerS2CPacket {
            val containerId = buffer.readVarInt()
            return GameCloseContainerS2CPacket(containerId)
        }

        override fun encode(buffer: MessageBuf, message: GameCloseContainerS2CPacket): MessageBuf {
            buffer.writeVarInt(message.syncId)
            return buffer
        }
    }
}