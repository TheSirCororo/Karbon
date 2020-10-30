package com.karbonpowered.protocol.java.s2c.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameKeepAliveS2CPacket
@JvmOverloads
constructor(
    val id: Long = 0
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameKeepAliveS2CPacket> {
        override fun decode(buffer: MessageBuf): GameKeepAliveS2CPacket {
            val id = buffer.readLong()
            return GameKeepAliveS2CPacket(id)
        }

        override fun encode(buffer: MessageBuf, message: GameKeepAliveS2CPacket): MessageBuf {
            buffer.writeLong(message.id)
            return buffer
        }
    }
}