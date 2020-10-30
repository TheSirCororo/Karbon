package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GamePlayerChangeHeldItemC2SPacket
@JvmOverloads
constructor(
    val slot: Int = 0
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GamePlayerChangeHeldItemC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerChangeHeldItemC2SPacket {
            val slot = buffer.readVarInt()
            return GamePlayerChangeHeldItemC2SPacket(slot)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerChangeHeldItemC2SPacket): MessageBuf {
            buffer.writeVarInt(message.slot)
            return buffer
        }
    }
}