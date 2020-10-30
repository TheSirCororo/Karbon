package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GamePlayerMovementC2SPacket
@JvmOverloads
constructor(
    val onGround: Boolean = true
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GamePlayerMovementC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerMovementC2SPacket {
            val onGround = buffer.readBoolean()
            return GamePlayerMovementC2SPacket(onGround)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerMovementC2SPacket): MessageBuf {
            buffer.writeBoolean(message.onGround)
            return buffer
        }
    }
}