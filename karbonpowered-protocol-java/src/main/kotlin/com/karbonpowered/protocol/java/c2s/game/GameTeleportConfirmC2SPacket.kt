package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameTeleportConfirmC2SPacket
@JvmOverloads
constructor(
    val teleportId: Int = 0
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameTeleportConfirmC2SPacket> {
        override fun decode(buffer: MessageBuf): GameTeleportConfirmC2SPacket {
            val teleportId = buffer.readVarInt()
            return GameTeleportConfirmC2SPacket(teleportId)
        }

        override fun encode(buffer: MessageBuf, message: GameTeleportConfirmC2SPacket): MessageBuf {
            buffer.writeVarInt(message.teleportId)
            return buffer
        }
    }
}